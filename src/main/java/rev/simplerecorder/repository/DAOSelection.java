package rev.simplerecorder.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import rev.simplerecorder.model.MSelection;

@Repository
public class DAOSelection {
	@Autowired
	private JdbcTemplate jt;

	public List<MSelection> getPhase() {
		String sql = "SELECT * FROM S_Phase";
		List<Map<String, Object>> resultList = jt.queryForList(sql);
		List<MSelection>  ret = new ArrayList<>();
		for(Map<String, Object> result : resultList) {
			MSelection selection = new MSelection();
			selection.setId((int) result.get("id")) ;
			selection.setName((String) result.get("name"));
			selection.setDescription((String) result.get("description")) ;
			selection.setOrder((int) result.get("ord")) ;
			selection.setIsinit((boolean)result.get("initialvalue"));

			ret.add(selection);
		}
		return ret;
	}
}
