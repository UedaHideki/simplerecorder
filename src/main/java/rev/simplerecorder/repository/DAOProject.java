package rev.simplerecorder.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import rev.simplerecorder.model.FMProject;

@Repository
public class DAOProject {

	@Autowired
	private JdbcTemplate jt;
	
	
	
	public int countProject() {
		String sql = "SELECT COUNT(id) FROM Project";
		return jt.queryForObject(sql, Integer.class);
	}

	public List<FMProject> getAllProjects() {
		String sql = "SELECT id, name, no, description FROM Project";
		List<Map<String, Object>> resultList = jt.queryForList(sql);
		List<FMProject>  ret = new ArrayList<>();
		for(Map<String, Object> result : resultList) {
			FMProject fmproject = new FMProject();
			//String name = (String) result.get("name");
			fmproject.setId((int) result.get("id")) ;
			fmproject.setName((String) result.get("name"));
			fmproject.setNo((String) result.get("no")) ;
			fmproject.setDescription((String) result.get("description")) ;

			ret.add(fmproject);
		}
		System.out.println("** getAllProjects");
		System.out.println(ret);
		return ret;
	}
}
