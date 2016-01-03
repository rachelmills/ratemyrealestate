<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="POST"
	action="${pageContext.request.contextPath}/docreateblog"
	commandName="blogentry">
	
	Log Entry:<br>
	<%
		String data = request.getParameter("data");
	%>

	<textarea name="data" rows="10" cols="60">
<%=data != null ? data : ""%></textarea>
	<br>
Password: <input type="text" name="password" value="" size="30">
	<br>
	<form:input type="submit" value="Submit" path="blogentry"></form:input>


</form:form>