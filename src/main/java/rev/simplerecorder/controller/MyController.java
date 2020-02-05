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
   
   @GetMapping("/list")
   public String list(Model model) {
	   
	   List<MProject> listProject = daoProject.getAllProjects();
	   model.addAttribute("fmprojects", listProject);
	   return "show_projects";
   }
   
   @GetMapping("/projectedit")
   public String editProject(Model model, @RequestParam String edit) {
	   int projectId = Integer.parseInt(edit);
	   MProject project = daoProject.getProject(projectId);
	   model.addAttribute("project", project);
	   model.addAttribute("type", "edit");
	   return "edit_project";
   }
   
   @GetMapping("/newproject")
   public String newproject(Model model) {
	   MProject project = new MProject();
	   model.addAttribute("project", project);
	   model.addAttribute("type", "new");
	   return "edit_project";
   }
   
   @PostMapping(path="/editproject")
   public String editcompleteProject(Model model, MProject project, @RequestParam String edit) {
	   if(edit.equals("new")) {
		   daoProject.insertProject(project);
	   }
	   else {
		   daoProject.updateProject(project);
	   }
	   return "redirect:list";
   }
	   
   
   @GetMapping("/project")
   public String project(Model model
		                 ,@RequestParam String projectid) {
	   
	   List<MRecord> listRecord = daoProject.getRecords( Integer.parseInt(projectid));
	   model.addAttribute("projectid", projectid);
	   model.addAttribute("records", listRecord);	   
	   return "show_records";
   }
   
   @GetMapping("/recordedit")
   public String editRecord(Model model, @RequestParam String edit, @RequestParam String projectid) {
	   
	   int recordId = Integer.parseInt(edit);
	   int projectId = Integer.parseInt(projectid);
	   MRecord record = daoProject.getRecord(projectId, recordId);
	   model.addAttribute("record", record);
	   model.addAttribute("type", "edit");
	   model.addAttribute("projectid", projectid);
	   return "edit_record";
   }
   
   @GetMapping("/newrecord")
   public String newRecord(Model model, @RequestParam String projectid) {
	   MRecord record = new MRecord();
	   model.addAttribute("record", record);
	   model.addAttribute("type", "new");
	   model.addAttribute("projectid", projectid);
	   return "edit_record";
   }
   
   @PostMapping(path="/editrecord")
   public String editcompleteRecord(Model model, MRecord record, @RequestParam String edit, @RequestParam String projectid) {
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
	   return "show_items";
   }
   
   @GetMapping("/itemedit")
   public String editItem(Model model, @RequestParam String edit, @RequestParam String projectid, @RequestParam String recordid) {
	   int itemId = Integer.parseInt(edit);
	   int projectId = Integer.parseInt(projectid);
	   int recordId = Integer.parseInt(recordid);
	   MItem item = daoProject.getItem(projectId, recordId, itemId);
	   model.addAttribute("item", item);
	   model.addAttribute("type", "edit");
	   model.addAttribute("projectid", projectid);
	   model.addAttribute("recordid", recordid);
	   return "edit_item";
   }
   
   @GetMapping("/newitem")
   public String newItem(Model model, @RequestParam String projectid, @RequestParam String recordid) {
	   MItem item = new MItem();
	   model.addAttribute("item", item);
	   model.addAttribute("type", "new");
	   model.addAttribute("projectid", projectid);
	   model.addAttribute("recordid", recordid);
	   return "edit_item";
   }
   
   @PostMapping(path="/edititem")
   public String editcompleteItem(Model model, MItem item, @RequestParam String edit, @RequestParam String projectid, @RequestParam String recordid) {
	   if(edit.equals("new")) {
		   daoProject.insertItem(item, Integer.parseInt(projectid), Integer.parseInt(recordid));
	   }
	   else {
		   daoProject.updateItem(item, Integer.parseInt(projectid), Integer.parseInt(recordid));
	   }
	   return "redirect:item?projectid="+projectid+"&recordid="+recordid;
   }
   
}