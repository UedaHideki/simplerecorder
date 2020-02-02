package rev.simplerecorder.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import rev.simplerecorder.model.MItem;
import rev.simplerecorder.model.MProject;
import rev.simplerecorder.model.MRecord;
import rev.simplerecorder.repository.DAOProject;

@Controller
public class MyController {

	@Autowired
	private NamedParameterJdbcTemplate n;

	@Autowired
	private DAOProject daoProject; 

   @GetMapping("/")
   public String index(Model model) {

      model.addAttribute("message", "Hello Spring MVC 5!こんにちは");
      
      int a = daoProject.countProject();
      System.out.println(a);
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
   
   @GetMapping("/a")
   public String a() {
	   System.out.println("**get a");
	   return "index";
   }
   @PostMapping("/a")
   public String b() {
	   System.out.println("**post a");
	   return "index";
   }
   
   
   @GetMapping("/list")
   public String list(Model model) {
	   
	   List<MProject> listProject = daoProject.getAllProjects();
	   model.addAttribute("fmprojects", listProject);
	   return "list";
   }
   
   @GetMapping("/projectedit")
   public String editProject(Model model, @RequestParam String edit) {
	   System.out.println("**editProjedt");
	   System.out.println(edit);
	   
	   int projectId = Integer.parseInt(edit);
	   MProject project = daoProject.getProject(projectId);
	   model.addAttribute("project", project);
	   model.addAttribute("type", "edit");
	   return "editproject";
   }
   
   @GetMapping("/newproject")
   public String newproject(Model model) {
	   MProject project = new MProject();
	   model.addAttribute("project", project);
	   model.addAttribute("type", "new");
	   return "editproject";
   }
   
   @PostMapping(path="/editproject")
   public String editcompleteProject(Model model, MProject project, @RequestParam String edit) {
	   System.out.println("**Post editcompleteProject");
	   System.out.println(edit);
	   System.out.println("name: " + project.getName());
	   System.out.println("id: " + project.getId());
	   if(edit.equals("new")) {
		   daoProject.insertProject(project);
	   }
	   else {
		   daoProject.updateProject(project);
	   }
	   return "redirect:list";
   }
	   
   
   @GetMapping("/project/{projectID}")
   public String project(Model model
		                 ,@PathVariable String projectID) {
	   
	   List<MRecord> listRecord = daoProject.getRecords( Integer.parseInt(projectID));
	   model.addAttribute("projectid", projectID);
	   model.addAttribute("records", listRecord);	   
	   return "project";
   }
   
   @GetMapping("/item/{projectID}/{recordID}")
   public String item(Model model
		             ,@PathVariable String projectID
		             ,@PathVariable String recordID) {
	   List<MItem> listItem = daoProject.getItem(Integer.parseInt(projectID), Integer.parseInt(recordID));
	   model.addAttribute("items", listItem);
	   
	   return "item";
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