/*
 *    Copyright 2010-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.lenicliu.shiro.spring.boot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.PropertiesRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * {@link EnableAutoConfiguration Auto-Configuration} for Shiro. Contributes a
 * {@link Realm} and a {@link WebSecurityManager}.
 *
 * @author lenicliu
 */

@Configuration
@ConditionalOnClass({ ShiroFilterFactoryBean.class })
@EnableConfigurationProperties(ShiroProperties.class)
public class ShiroAutoConfiguration {

	public static enum RealmType {
		INI, PROPERTIES, JDBC;
	}

	@Autowired
	private ShiroProperties properties;

	@Bean(name = "shiroFilter")
	@ConditionalOnMissingBean
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setLoginUrl(this.properties.getLoginUrl());
		shiroFilter.setSuccessUrl(this.properties.getSuccessUrl());
		shiroFilter.setUnauthorizedUrl(this.properties.getUnauthorizedUrl());
		shiroFilter.setFilterChainDefinitionMap(this.properties.getFilterChainDefinitionMap());
		shiroFilter.setSecurityManager(securityManager());

		Map<String, Filter> filters = new HashMap<String, Filter>();
		filters.put("anon", new AnonymousFilter());
		filters.put("authc", new FormAuthenticationFilter());
		filters.put("logout", new LogoutFilter());
		filters.put("roles", new RolesAuthorizationFilter());
		filters.put("user", new UserFilter());

		Map<String, Filter> usedFilters = new HashMap<String, Filter>();
		for (String name : this.properties.getFilters()) {
			usedFilters.put(name, filters.get(name));
		}
		shiroFilter.setFilters(usedFilters);
		return shiroFilter;
	}

	@Bean(name = "securityManager")
	@ConditionalOnMissingBean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(realm());
		return securityManager;
	}

	@Bean(name = "realm")
	@ConditionalOnMissingBean
	@DependsOn("lifecycleBeanPostProcessor")
	public Realm realm() {
		Realm realm = null;
		if (RealmType.PROPERTIES.equals(this.properties.getRealmType())) {
			PropertiesRealm propertiesRealm = new PropertiesRealm();
			if (this.properties.getResourcePath() != null) {
				propertiesRealm.setResourcePath(this.properties.getResourcePath());
			}
			propertiesRealm.init();
			realm = propertiesRealm;
		}
		Objects.requireNonNull(realm, "Realm required for shiro");
		return realm;
	}

	@Bean
	@ConditionalOnMissingBean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
}