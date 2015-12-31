package com.lenicliu.security.customize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

import com.lenicliu.security.customize.service.UserService;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * spring security configuration
	 * 
	 * @author lenicliu
	 */
	@EnableWebSecurity
	public static class WebSecurity extends WebSecurityConfigurerAdapter {

		@Autowired
		private UserService userService;

		@Override
		protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
			authenticationManagerBuilder.userDetailsService(userService);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// customize login form
			http.formLogin()
					// customize url
					.defaultSuccessUrl("/").failureUrl("/login").loginPage("/login").loginProcessingUrl("/j_spring_security_check")
					// customize parameter
					.passwordParameter("j_password").usernameParameter("j_username");

			// disable http basic
			http.httpBasic().disable();

			// granted matcher
			http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

			http.authorizeRequests().antMatchers("/admin/users").hasAuthority("USER_READ");
			http.authorizeRequests().antMatchers("/admin/users/**").hasAuthority("USER_WRITE");
			http.authorizeRequests().antMatchers("/admin/roles").hasAuthority("ROLE_READ");
			http.authorizeRequests().antMatchers("/admin/roles/**").hasAuthority("ROLE_WRITE");
			http.authorizeRequests().antMatchers("/admin/auths").hasAuthority("AUTH_READ");

			http.authorizeRequests().antMatchers("/messages").hasRole("USER");
			http.authorizeRequests().antMatchers("/messages/view").hasAuthority("MSG_READ");
			http.authorizeRequests().antMatchers("/messages/input").hasAuthority("MSG_WRITE");
			http.authorizeRequests().antMatchers("/messages/submit").hasAuthority("MSG_WRITE");
			http.authorizeRequests().antMatchers("/messages/delete").hasAuthority("MSG_WRITE");

			// resources/static files
			http.authorizeRequests().antMatchers("/login", "/j_spring_security_check", "/webjars/**").permitAll();

			// others authenticated
			http.authorizeRequests().anyRequest().authenticated();

			// disable csrf
			http.csrf().disable();
		}
	}

	/**
	 * Customize Servlet Container
	 * 
	 * @author lenicliu
	 */
	@Component
	public static class ServletContainer implements EmbeddedServletContainerCustomizer {
		@Override
		public void customize(ConfigurableEmbeddedServletContainer container) {
			container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403.jsp"));
			container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.jsp"));
			container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.jsp"));
		}
	}
}