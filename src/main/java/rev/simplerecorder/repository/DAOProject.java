package rev.simplerecorder.repository;

import java.sql.Date;
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
			MProject proj = new MProject();
			proj.setId((int) result.get("id")) ;
			proj.setName((String) result.get("name"));
			proj.setNo((String) result.get("no")) ;
			proj.setDescription((String) result.get("description")) ;

			ret.add(proj);
		}
		return ret;
	}
	
	public MProject getProject(int projectID) {
		String sql = "SELECT * FROM Project WHERE id=?";
		Map<String, Object> result = jt.queryForMap(sql, ""+projectID);
		MProject proj = new MProject();
		proj.setId((int) result.get("id")) ;
		proj.setName((String) result.get("name"));
		proj.setNo((String) result.get("no")) ;
		proj.setDescription((String) result.get("description")) ;
		return proj;
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
		
		String sql = "SELECT id, projectid, phase, target, start_date, end_date, status FROM Record WHERE projectid=?";
		List<Map<String, Object>> resultList = jt.queryForList(sql, ""+projectID);		
		List<MRecord>  ret = new ArrayList<>();
		for(Map<String, Object> result : resultList) {
			MRecord rec = new MRecord();
			rec.setId((int)result.get("id"));
			rec.setProjectId((int)result.get("projectid"));
			rec.setPhase((String)result.get("phase"));
			rec.setTarget((String)result.get("target"));
			rec.setStartDate(date2string(result.get("start_date")));
			rec.setEndDate(date2string(result.get("end_date")));
			rec.setStatus((String)result.get("status"));
			ret.add(rec);
		}
		return ret;
	}
	
	public MRecord getRecord(int projectID, int recID) {
		
		String sql = "SELECT * FROM Record WHERE projectid=? AND id=?";
		Map<String, Object> result = jt.queryForMap(sql, ""+projectID, ""+recID);		
		MRecord ret = new MRecord();
		ret.setId((int)result.get("id"));
		ret.setProjectId((int)result.get("projectid"));
		ret.setPhase((String)result.get("phase"));
		ret.setTarget((String)result.get("target"));
		ret.setStartDate(date2string(result.get("start_date")));
		ret.setEndDate(date2string(result.get("end_date")));
		ret.setStatus((String)result.get("status"));
		return ret;
	}
	
	public void insertRecord(MRecord record, int projectID) {
		
		String sql = "INSERT INTO Record(phase, target, status, projectid) VALUES(?,?,?,?)";		
		jt.update(sql, record.getPhase(), record.getTarget(), record.getStatus(), ""+projectID);
		return;
	}
	public void updateRecord(MRecord rec, int projectID) {
				
		String sql = "UPDATE Record SET phase=?, target=?, status=? WHERE projectid=? AND id=?";
		
		jt.update(sql, rec.getPhase()
				     , rec.getTarget()
				     , rec.getStatus()
				     , ""+projectID
				     , rec.getId()
				     );
		
		return;
	}
	

	public List<MItem> getItems(int projectID, int recordID) {
		String sql = "SELECT * FROM Item WHERE projectid=? AND recordid=?";
		Object[] args = new Object[] {""+projectID, ""+recordID};
		List<Map<String, Object>> resultList = jt.queryForList(sql, args);		
		List<MItem>  ret = new ArrayList<>();
		for(Map<String, Object> result : resultList) {
			MItem item = new MItem();
			item.setId((int)result.get("id"));
			item.setDescription((String)result.get("description"));
			ret.add(item);
		}
		return ret;
	}

	public MItem getItem(int projectID, int recordID, int itemID) {
		String sql = "SELECT * FROM Item WHERE projectid=? AND recordid=? AND id=?";
		Object[] args = new Object[] {""+projectID, ""+recordID, ""+itemID};
		Map<String, Object> result = jt.queryForMap(sql, args);		
		MItem  ret = new MItem();
		ret.setId((int)result.get("id"));
		ret.setProjectId((int)result.get("projectid"));
		ret.setRecoredId((int)result.get("recordid"));
		ret.setDescription((String)result.get("description"));
		return ret;
	}
	
	public void insertItem(MItem item, int projectID, int recordID) {
		
		String sql = "INSERT INTO Item(description, projectid, recordid) VALUES(?,?,?)";		
		jt.update(sql, item.getDescription()
				     , ""+projectID
				     , ""+recordID
				     );
		return;
	}
	
	public void updateItem(MItem item, int projectID, int recordID) {
		String sql = "UPDATE Item SET description=? WHERE projectid=? AND recordid =? AND id=?";
		
		jt.update(sql, item.getDescription()
				     , ""+projectID
				     , ""+recordID
				     , item.getId()
				     );
		
		return;
	}
	
	private String date2string(Object d) {
		Date d2 = (Date)d;
		return d2.toString();
	}
		
}
