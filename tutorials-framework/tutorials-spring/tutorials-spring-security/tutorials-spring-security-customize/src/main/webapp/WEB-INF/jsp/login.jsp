<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:import url="/WEB-INF/jsp/layout/head.jsp" />
<body>
	<div class="container">
		<h1>Spring Boot Security Customize</h1>
		<div class="row">
			<div class="col-md-12">
				<form id="input-form" method="POST" action="${pageContext.request.contextPath}/j_spring_security_check">
					<div class="form-group">
						<label for="inputUsername">Username</label>
						<input type="text" class="form-control" name="j_username" id="inputUsername" required="required">
					</div>
					<div class="form-group">
						<label for="inputPassword">Password</label>
						<input type="password" class="form-control" name="j_password" id="inputPassword" required="required">
					</div>
					<button type="submit" class="btn btn-primary">Login</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>