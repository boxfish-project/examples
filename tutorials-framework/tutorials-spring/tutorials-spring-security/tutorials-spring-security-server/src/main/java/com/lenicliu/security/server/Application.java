package com.lenicliu.security.server;

import java.security.Principal;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
public class Application extends AuthorizationServerConfigurerAdapter {

	@RequestMapping({ "/", "index", "/state" })
	public Object index() {
		return Collections.singletonMap("state", "ok");
	}

	@RequestMapping({ "/api/oauth/user" })
	public Object user(Principal principal) {
		return principal;
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.approvalStoreDisabled();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("ssos")//
		.secret("ssos").scopes("READ")//
		.authorizedGrantTypes("authorization_code", "refresh_token")//
		.redirectUris("http://localhost:8080/c/login");
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
		@Override
		protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
			authenticationManagerBuilder.inMemoryAuthentication()//
					.withUser("admin").password("admin").roles("ADMIN").authorities("READ", "WRITE").accountExpired(false).accountLocked(false);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http//
					.httpBasic().disable()//
					.formLogin()//
					.and().authorizeRequests().antMatchers("/api/oauth/user").authenticated()//
					.and().authorizeRequests().antMatchers("/oauth/**").permitAll();
		}
	}
}