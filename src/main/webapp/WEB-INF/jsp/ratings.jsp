<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="wrapper">
	<div id="main">

		<table id="table">
			<tr>
				<th>Agency Name</th>
				<th>Agency Suburb</th>
				<th>Average Rating (out of 5)</th>
			</tr>
			<c:choose>
				<c:when test="${display eq ('user')}">
					<c:forEach var="rating" items="${ratings}">
						<tr>
							<td id="first"><c:out value="${rating.agent.agentName}"></c:out></td>
							<td id="first"><c:out value="${rating.agent.suburb}"></c:out></td>
							<td id="last"><c:out value="${rating.rating}"></c:out></td>
							<td id="other"><a
								href="${pageContext.request.contextPath}/editrating?agentid=${rating.agent.id}&userid=${id}">Edit</a></td>
							<td id="other"><a
								href="${pageContext.request.contextPath}/deleterating?agentid=${rating.agent.id}&userid=${id}">Delete</a></td>

						</tr>

					</c:forEach>
				</c:when>

				<c:when test="${display eq ('agent')}">
					<tr>
						<td id="first"><c:out value="${agentname}"></c:out></td>
						<td id="first"><c:out value="${agentsuburb}"></c:out></td>
						<%-- <td id="last"><c:out value="${averageRating}"></c:out></td> --%>
						<td id="last"><fmt:formatNumber value="${averageRating}"
								maxFractionDigits="2"></fmt:formatNumber>
						<td id="other"><a
							href="${pageContext.request.contextPath}/createrating?agentid=${id}">Rate
								this agent</a></td>
					</tr>
				</c:when>

				<c:when test="${display eq ('all')}">
					<c:forEach var="rating" items="${ratings}">
						<tr>
							<td id="first"><c:out value="${rating.agent.agentName}"></c:out></td>
							<td id="first"><c:out value="${rating.agent.id}"></c:out></td>
							<td id="first"><c:out value="${rating.agent.suburb}"></c:out></td>
							<td id="last"><c:out value="${rating.rating}"></c:out></td>
							<c:choose>
								<c:when test="${rating.ratedByUser == null}">
									<td id="other"><a
										href="${pageContext.request.contextPath}/createrating?agentid=${rating.agent.id}">Rate
											this agent</a></td>
								</c:when>
								<c:when test="${!rating.ratedByUser}">
									<td id="other"><a
										href="${pageContext.request.contextPath}/createrating?agentid=${rating.agent.id}">Rate
											this agent</a></td>
								</c:when>
								<c:when test="${rating.ratedByUser}">
									<td id="other">Already rated</td>
								</c:when>
							</c:choose>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</table>
	</div>
</div>