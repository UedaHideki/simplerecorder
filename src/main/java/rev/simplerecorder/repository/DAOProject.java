package rev.simplerecorder.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import rev.simplerecorder.model.MItem;
import rev.simplerecorder.model.MProject;
import rev.simplerecorder.model.MRecord;

@Repository
public class DAOProject {

	@Autowired
	private JdbcTemplate jt;
	
	
	
	public int countProject() {
		String sql = "SELECT COUNT(id) FROM Project";
		return jt.queryForObject(sql, Integer.class);
	}

	public List<MProject> getAllProjects() {
		String sql = "SELECT id, name, no, description FROM Project";
		List<Map<String, Object>> resultList = jt.queryForList(sql);
		List<MProject>  ret = new ArrayList<>();
		for(Map<String, Object> result : resultList) {
			MProject fmproject = new MProject();
			fmproject.setId((int) result.get("id")) ;
			fmproject.setName((String) result.get("name"));
			fmproject.setNo((String) result.get("no")) ;
			fmproject.setDescription((String) result.get("description")) ;

			ret.add(fmproject);
		}
		return ret;
	}
	
	public MProject getProject(int projectID) {
		String sql = "SELECT * FROM Project WHERE id=?";
		Map<String, Object> result = jt.queryForMap(sql, ""+projectID);
		MProject fmproject = new MProject();
		fmproject.setId((int) result.get("id")) ;
		fmproject.setName((String) result.get("name"));
		fmproject.setNo((String) result.get("no")) ;
		fmproject.setDescription((String) result.get("description")) ;
		return fmproject;
	}
	

	public void insertProject(MProject project) {
		
		String sql = "INSERT INTO Project(name, no, description) VALUES(?,?,?)";		
		jt.update(sql, project.getName(), project.getNo(), project.getDescription());
		return;
	}
	
	public void updateProject(MProject project) {
		String sql = "UPDATE Project SET name=?, no=?, description=? WHERE id=?";
		
		jt.update(sql, project.getName(), project.getNo(), project.getDescription(), project.getId());
		
		return;
	}
		
	
	public List<MRecord> getRecords(int projectID) {
		System.out.println("** getRecoreds");
		System.out.println(projectID);
		
		String sql = "SELECT id, projectid, phase, target, start_date, end_date, status FROM Record WHERE projectid=?";
		List<Map<String, Object>> resultList = jt.queryForList(sql, ""+projectID);		
		List<MRecord>  ret = new ArrayList<>();
		for(Map<String, Object> result : resultList) {
			MRecord rec = new MRecord();
			rec.setId((int)result.get("id"));
			rec.setProjectId((int)result.get("projectid"));
			rec.setPhase((String)result.get("phase"));
			rec.setTarget((String)result.get("target"));
			//rec.setStartDate((String)result.get("start_date")); date cant be cast to string
			//rec.setEndDate((String)result.get("end_date"));
			rec.setStatus((String)result.get("status"));
			ret.add(rec);
			System.out.println("***");
			System.out.println(rec.getPhase());	
		}
		return ret;
	}
	
	public List<MItem> getItem(int projectID, int recordID) {
		String sql = "SELECT * FROM Item WHERE projectid=? AND recordid=?";
		Object[] args = new Object[] {""+projectID, ""+recordID};
		List<Map<String, Object>> resultList = jt.queryForList(sql, args);		
		List<MItem>  ret = new ArrayList<>();
		for(Map<String, Object> result : resultList) {
			MItem item = new MItem();
			item.setId((int)result.get("id"));
			// todo
			item.setDescription((String)result.get("description"));
			ret.add(item);
		}
		return ret;
	}
	
	
}
