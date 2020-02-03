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
	   return "redirect:project?projecctID="+edit;
   }
	   
   
   @GetMapping("/project")
   public String project(Model model
		                 ,@RequestParam String projectid) {
	   
	   List<MRecord> listRecord = daoProject.getRecords( Integer.parseInt(projectid));
	   model.addAttribute("projectid", projectid);
	   model.addAttribute("records", listRecord);	   
	   return "project";
   }
   
   @GetMapping("/recordedit")
   public String editRecord(Model model, @RequestParam String edit, @RequestParam String projectid) {
	   System.out.println("**editRecord");
	   System.out.println(edit);
	   
	   int recordId = Integer.parseInt(edit);
	   int projectId = Integer.parseInt(projectid);
	   MRecord record = daoProject.getRecord(projectId, recordId);
	   model.addAttribute("record", record);
	   model.addAttribute("type", "edit");
	   model.addAttribute("projectid", projectid);
	   return "editrecord";
   }
   
   @GetMapping("/newrecord")
   public String newRecord(Model model, @RequestParam String projectid) {
	   MRecord record = new MRecord();
	   model.addAttribute("record", record);
	   model.addAttribute("type", "new");
	   model.addAttribute("projectid", projectid);
	   return "editrecord";
   }
   
   @PostMapping(path="/editrecord")
   public String editcompleteRecord(Model model, MRecord record, @RequestParam String edit, @RequestParam String projectid) {
	   System.out.println("**Post compeleRecord");
	   System.out.println(edit);
	   System.out.println("phase: " + record.getPhase());
	   System.out.println("id: " + record.getId());
	   if(edit.equals("new")) {
		   daoProject.insertRecord(record, Integer.parseInt(projectid));
	   }
	   else {
		   daoProject.updateRecord(record, Integer.parseInt(projectid));
	   }
	   return "redirect:project?projectid="+projectid;
   }
   
   @GetMapping("/item")
   public String item(Model model
		             ,@RequestParam String projectid
		             ,@RequestParam String recordid) {
	   List<MItem> listItem = daoProject.getItems(Integer.parseInt(projectid), Integer.parseInt(recordid));
	   model.addAttribute("items", listItem);
	   model.addAttribute("projectid", projectid);
	   model.addAttribute("recordid", recordid); 
	   return "item";
   }
   
   @GetMapping("/itemedit")
   public String editItem(Model model, @RequestParam String edit, @RequestParam String projectid, @RequestParam String recordid) {
	   System.out.println("**editItem");
	   System.out.println(edit);
	   int itemId = Integer.parseInt(edit);
	   int projectId = Integer.parseInt(projectid);
	   int recordId = Integer.parseInt(recordid);
	   MItem item = daoProject.getItem(projectId, recordId, itemId);
	   model.addAttribute("item", item);
	   model.addAttribute("type", "edit");
	   model.addAttribute("projectid", projectid);
	   model.addAttribute("recordid", recordid);
	   return "edititem";
   }
   
   @GetMapping("/newitem")
   public String newItem(Model model, @RequestParam String projectid, @RequestParam String recordid) {
	   MItem item = new MItem();
	   model.addAttribute("item", item);
	   model.addAttribute("type", "new");
	   model.addAttribute("projectid", projectid);
	   model.addAttribute("recordid", recordid);
	   return "edititem";
   }
   
   @PostMapping(path="/edititem")
   public String editcompleteItem(Model model, MItem item, @RequestParam String edit, @RequestParam String projectid, @RequestParam String recordid) {
	   System.out.println("**Post compeleitem");
	   System.out.println(edit);
	   System.out.println("description: " + item.getDescription());
	   if(edit.equals("new")) {
		   daoProject.insertItem(item, Integer.parseInt(projectid), Integer.parseInt(recordid));
	   }
	   else {
		   daoProject.updateItem(item, Integer.parseInt(projectid), Integer.parseInt(recordid));
	   }
	   return "redirect:item?projectid="+projectid+"&recordid="+recordid;
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