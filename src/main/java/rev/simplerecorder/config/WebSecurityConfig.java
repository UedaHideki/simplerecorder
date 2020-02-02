package rev.simplerecorder.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

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
		
		// formからの文字が対応できないために挿入
		 CharacterEncodingFilter filter = new CharacterEncodingFilter();
	        filter.setEncoding("UTF-8");
	        filter.setForceEncoding(true);
	        http.addFilterBefore(filter, CsrfFilter.class);
	        
	}
	
}
