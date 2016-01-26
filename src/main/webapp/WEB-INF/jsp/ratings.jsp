<%@include file="includes/header.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="panel panel-primary">
	<div class="panel-heading">Search results</div>
</div>
<!-- Table -->
<table class="table">
	<tr>
		<th>Agency Name</th>
		<th>Agency Suburb</th>
		<th>Average Rating (out of 5)</th>
	</tr>
	<c:choose>
		<c:when test="${display eq ('user')}">
			<c:forEach var="rating" items="${ratings.getContent()}">
				<tr>
					<td><c:out value="${rating.agent.agentname}"></c:out></td>
					<td><c:out value="${rating.agent.suburb}"></c:out></td>
					<td><c:out value="${rating.rating}"></c:out></td>
					<td><a
						href="${pageContext.request.contextPath}/editrating?agentid=${rating.agent.id}&userid=${id}">Edit</a></td>
					<td><a
						href="${pageContext.request.contextPath}/deleterating?agentid=${rating.agent.id}&userid=${id}">Delete</a></td>
				</tr>
			</c:forEach>
		</c:when>

		<c:when test="${display eq ('agent')}">
			<tr>
				<td><c:out value="${agentname}"></c:out></td>
				<td><c:out value="${agentsuburb}"></c:out></td>
				<%-- 						<td><c:out value="${averageRating}"></c:out></td> --%>
				<td><fmt:formatNumber value="${averageRating}"
						maxFractionDigits="2"></fmt:formatNumber>
				<td><a
					href="${pageContext.request.contextPath}/createrating?agentid=${id}">Rate
						this agent</a></td>
			</tr>
		</c:when>

		<c:when test="${display eq ('all')}">
			<c:forEach var="rating" items="${ratings}">
				<tr>
					<td><c:out value="${rating.agent.agentname}"></c:out></td>
					<td><c:out value="${rating.agent.suburb}"></c:out></td>
					<td><c:out value="${rating.rating}"></c:out></td>
					<c:choose>
						<c:when test="${rating.ratedByUser == null}">
							<td><a
								href="${pageContext.request.contextPath}/createrating?agentid=${rating.agent.id}">Rate
									this agent</a></td>
						</c:when>
						<c:when test="${!rating.ratedByUser}">
							<td><a
								href="${pageContext.request.contextPath}/createrating?agentid=${rating.agent.id}">Rate
									this agent</a></td>
						</c:when>
						<c:when test="${rating.ratedByUser}">
							<td>Already rated</td>
						</c:when>
					</c:choose>
				</tr>
			</c:forEach>
		</c:when>
	</c:choose>
</table>

<nav>
	<ul class="pagination">
		<li><a class="${pageable.hasPrevious() ? 'enabled' : 'disabled'}"
			href="${pageContext.request.contextPath}/userratings/${pageable.previous().pageNumber}?userid=${userid}"
			aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a></li>
		<li><a
			class="${pageable.next().pageNumber >= agentList.totalPages ? 'disabled' : 'enabled'}"
			href="${pageContext.request.contextPath}/userratings/${pageable.next().pageNumber}?userid=${userid}"
			aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
	</ul>
</nav>
</div>
<%@include file="includes/footer.jsp"%>