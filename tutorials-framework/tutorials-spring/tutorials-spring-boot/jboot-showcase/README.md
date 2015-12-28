# Feature

一个简单的TODO-List实例，多用户多终端，产品化

# Technology

*	Spring / Spring Boot / Shiro
*	Mybatis / H2Database / JPA
*	Timer / Schedule / Email
*	Freemarker / JSON / XML
*	JQuery / Bootstrap / HTML / CSS

# Architecture

*	Service Discovery
*	Service Registration
*	Load Balance
*	Healh Metrics

# Modules

*	showcase-user-service
	*	showcase-user-service-api(internal)
	*	showcase-user-service-endpoint(public)
	*	showcase-user-service-server(internal)
*	showcase-task-service
	*	showcase-task-service-api(internal)
	*	showcase-task-service-standalone(internal)
*	showcase-security
	*	shiro
*	showcase-open-api(oauth)
	*	showcase-open-api-client(public)
	*	showcase-open-api-auth-server(public)
	*	showcase-open-api-resource-server(public)
*	gateway -- nginx
*	configuration & manager