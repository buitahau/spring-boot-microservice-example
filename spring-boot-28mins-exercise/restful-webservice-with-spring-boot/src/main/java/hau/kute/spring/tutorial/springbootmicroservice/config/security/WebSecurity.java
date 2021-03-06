package hau.kute.spring.tutorial.springbootmicroservice.config.security;

import hau.kute.spring.tutorial.springbootmicroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private Environment environment;

	private UserService userService;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public WebSecurity(Environment environment,  UserService userService,
					BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.environment = environment;
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf()
			.disable()
			.authorizeRequests()
			.antMatchers("/status-checking").permitAll()
			.antMatchers("/users").hasIpAddress(environment.getProperty("gateway.ip"))
			.antMatchers("/login").hasIpAddress(environment.getProperty("gateway.ip"))
			.antMatchers("/h2-console/**").hasIpAddress(environment
						.getProperty("gateway.ip"))
			.anyRequest().authenticated()
			.and()
			.addFilter(getAuthenticationFilter());

		http.headers().frameOptions().disable();
	}

	private AuthenticationFilter getAuthenticationFilter()
					throws Exception {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter
						(environment, userService, authenticationManager());
		authenticationFilter.setFilterProcessesUrl(environment.getProperty
						("login.url.path"));
		return authenticationFilter;
	}

	@Override
	protected void configure(
					AuthenticationManagerBuilder auth)
					throws Exception {

		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}
}
