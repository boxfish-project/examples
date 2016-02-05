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

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.lenicliu.shiro.spring.boot.ShiroAutoConfiguration.RealmType;

/**
 * Configuration Properties for Shiro
 * 
 * @author lenicliu
 */
@ConfigurationProperties(prefix = ShiroProperties.SHIRO_PREFIX)
public class ShiroProperties {

	public static final String SHIRO_PREFIX = "shiro";

	private String loginUrl;
	private String successUrl;
	private String unauthorizedUrl;

	public String getLoginUrl() {
		return loginUrl;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public String getUnauthorizedUrl() {
		return unauthorizedUrl;
	}

	public Map<String, String> getFilterChainDefinitionMap() {
		Map<String, String> filterChainDefinitionMapping = new HashMap<String, String>();
		filterChainDefinitionMapping.put("/", "anon");
		filterChainDefinitionMapping.put("/home", "authc,roles[guest]");
		filterChainDefinitionMapping.put("/admin", "authc,roles[admin]");
		return filterChainDefinitionMapping;
	}

	public Set<String> getFilters() {
		return new HashSet<>(Arrays.asList("anon", "authc", "logout", "roles", "user"));
	}

	public RealmType getRealmType() {
		return RealmType.PROPERTIES;
	}

	public String getResourcePath() {
		return null;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public void setUnauthorizedUrl(String unauthorizedUrl) {
		this.unauthorizedUrl = unauthorizedUrl;
	}
}
