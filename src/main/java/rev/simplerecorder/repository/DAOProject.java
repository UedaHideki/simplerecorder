package rev.simplerecorder.repository;

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
	
}
