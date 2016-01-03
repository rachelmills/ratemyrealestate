<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="wrapper">
	<div id="main">
		<form:form method="POST"
			action="${pageContext.request.contextPath}/docreate"
			commandName="rating">
			<div id="box">
				<h3>${agent.agentName}(${agent.suburb})</h3>
				<c:set var="score" value="${rating.rating}"></c:set>
				<table>
					<tr>
						<td><input type="radio" name="rating" value=1
							<c:if test="${score==1}">checked</c:if>></td>
						<td><label for=1>Very Poor</label></td>
					</tr>
					<tr>
						<td><input type="radio" name="rating" value=2
							<c:if test="${score==2}">checked</c:if>></td>
						<td><label for=2>Poor</label></td>
					</tr>
					<tr>
						<td><input type="radio" name="rating" value=3
							<c:if test="${score==3}">checked</c:if>></td>
						<td><label for=3>Average</label></td>
					</tr>
					<tr>
						<td><input type="radio" name="rating" value=4
							<c:if test="${score==4}">checked</c:if>></td>
						<td><label for=4>Good</label></td>
					</tr>
					<tr>
						<td><input type="radio" name="rating" value=5
							<c:if test="${score==5}">checked</c:if>></td>
						<td><label for=5>Excellent</label></td>
					</tr>
				</table>
				<input type="hidden" name="agentID" value="${agent.id}"> 
				<input type="hidden" name="ratingID" value="${rating.id}">
				<input type="hidden" name="userID" value="${user.id}">
				<c:if test="${rating.id eq null}">
					<input type="submit" value="RATE AGENT">
				</c:if>
				<c:if test="${rating.id > 0}">
					<input type="submit" name="edit" value="Confirm edit">
				</c:if>
			</div>
		</form:form>
	</div>
</div>