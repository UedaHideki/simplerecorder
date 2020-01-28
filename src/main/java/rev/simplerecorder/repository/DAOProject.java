package rev.simplerecorder.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DAOProject {

	@Autowired
	private JdbcTemplate jt;
	
	
	
	public int countProject() {
		String sql = "SELECT COUNT(id) FROM Project";
		return jt.queryForObject(sql, Integer.class);
	}

	public List<String> getAllProjects() {
		String sql = "SELECT name FROM Project";
		List<Map<String, Object>> resultList = jt.queryForList(sql);
		List<String>  ret = new ArrayList<>();
		for(Map<String, Object> result : resultList) {
			String name = (String) result.get("name");
			ret.add(name);
		}
		System.out.println("** getAllProjects");
		System.out.println(ret);
		return ret;
	}
}
