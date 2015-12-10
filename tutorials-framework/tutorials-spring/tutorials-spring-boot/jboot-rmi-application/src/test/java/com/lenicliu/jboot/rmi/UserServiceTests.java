package com.lenicliu.jboot.rmi;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpComponentsHttpInvokerRequestExecutor;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

public class UserServiceTests {

	@Test
	@Ignore
	public void testRequest() throws Exception {
		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		UserService userService = ctx.getBean(UserService.class);
		Assert.assertEquals("LenicLiu", userService.find(new Long(1)));
		ctx.close();
	}

	@Configuration
	public static class Config {
		@Bean(name = "userService")
		public HttpInvokerProxyFactoryBean userService() {
			HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
			bean.setServiceInterface(UserService.class);
			bean.setServiceUrl("http://localhost:8080/UserService");
			bean.setHttpInvokerRequestExecutor(new HttpComponentsHttpInvokerRequestExecutor());
			return bean;
		}
	}
}