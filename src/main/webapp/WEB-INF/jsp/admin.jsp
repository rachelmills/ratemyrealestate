<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="formtable">
	<tr>
		<td>Username</td>
		<td>Role</td>
		<td>Enabled</td>
	</tr>

	<c:forEach var="user" items="${users}">
		<tr>
			<td><c:out value="${user.username}" /></td>
			<td><c:out value="${user.authority}" /></td>
			<td><c:out value="${user.enabled}" /></td>
		</tr>
	</c:forEach>
</table>