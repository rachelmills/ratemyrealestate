<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div id="subheader">
	<div id="nav">
		<ul>
			<li><a href="${pageContext.request.contextPath}/">Home</a></li>
			<li><a href="${pageContext.request.contextPath}/about">About</a></li>
			<li><a href="${pageContext.request.contextPath}/">Contact</a></li>

			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="<c:url value='/admin'/>">Admin</a></li>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<li id="log"><a href="${pageContext.request.contextPath}/login">Log
						in</a></li>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<li id="log"><a href="<c:url value='/j_spring_security_logout'/>">Log
						out</a></li>
			</sec:authorize>
		</ul>
	</div>
</div>

