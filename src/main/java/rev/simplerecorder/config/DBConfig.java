package rev.simplerecorder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DBConfig {

	@Bean
	DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.setScriptEncoding("UTF-8")
				.addScript("/sql/schema.sql")
				.build();
	}
	@Bean(name="nt")
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
	  return new NamedParameterJdbcTemplate(dataSource());
	}
	
	@Bean(name="jt")
	public JdbcTemplate jt(DataSource ds) {
		return new JdbcTemplate(ds);
	}
	
}
