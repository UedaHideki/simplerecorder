package rev.simplerecorder.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity(debug=true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public UserDetailsService userDetailsService() {
		System.out.println("WebSecurityConfig::UserDetailsService()");

		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withDefaultPasswordEncoder().username("u").password("p").roles("USER").build());
		return manager;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("WebSecurityConfig::configre()");
		http
			.authorizeRequests()
				.antMatchers("/login*").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
			    //.loginPage("/login")
				.and()
			.httpBasic();
		http.csrf().disable();
	}
	
}
