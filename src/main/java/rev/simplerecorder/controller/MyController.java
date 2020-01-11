package rev.simplerecorder.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

@Autowired
private NamedParameterJdbcTemplate n;

   @GetMapping("/")
   public String index(Model model) {

      model.addAttribute("message", "Hello Spring MVC 5!こんにちは");
      String sql="SELECT * FROM record";
     
      SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", 1);
      String result =  n.queryForObject(
        "SELECT name FROM project WHERE ID = :id", namedParameters, String.class);

      //Map namedParameters = Collections.singletonMap("name", "name");
      //List<String> result = n.queryForList(sql,namedParameters,String.class);
      //List<String> result = n.queryForList(sql,String.class);
      //List<Map<String, Object>> result = n.queryForList(sql);
      
      System.out.println(result);
      return "index";
   }
   
   @GetMapping("/list")
   public String list(Model model) {
	   return "list";
   }
   @GetMapping("/list/project")
   public String project(Model model) {
	   return "project";
   }
   @GetMapping("/list/project/record")
   public String record(Model model) {
	   return "record";
   }
   @GetMapping("/list/project/record/item")
   public String item(Model model) {
	   return "item";
   }
}