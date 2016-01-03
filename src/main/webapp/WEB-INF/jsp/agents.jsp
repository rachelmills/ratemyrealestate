<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@include file="includes/header_old.jsp"%>

<div class="panel panel-primary">
	<div class="panel-heading">Search results</div>
</div>
<c:choose>
	<c:when test="${agentList.totalPages == 0}">
		<div class="panel panel-default">
			<div class="panel-heading">We have no agents named
				${agentname}. Please add the agent or search again.</div>
		</div>
		<sec:authorize access="isAuthenticated()">
			<a href="${pageContext.request.contextPath}/createagent">Add
				agent</a>
		</sec:authorize>
	</c:when>
	<c:when test="${agentList.size gt 0}">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<c:choose>
				<c:when test="${agentname eq ''}">
					<div class="panel-heading">All agents</div>
				</c:when>
				<c:otherwise>
					<div class="panel-heading">You searched for an agent named
						${agentname}</div>
				</c:otherwise>
			</c:choose>
			<!-- Table -->
			<table class="table">
				<tr>
					<th>Name</th>
					<th>Suburb</th>
				</tr>
				<c:forEach var="agent" items="${agentList.getContent()}">
					<tr>
						<td><c:out value="${agent.agentname}"></c:out></td>
						<td><c:out value="${agent.suburb}"></c:out></td>
						<td id="other"><a
							href="${pageContext.request.contextPath}/ratings?agentid=${agent.id}">View
								ratings</a></td>
						<td id="other"><a
							href="${pageContext.request.contextPath}/createrating?agentid=${agent.id}">Rate
								this agent</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="well well-left">
			<p>Page ${pageable.pageNumber+1} of ${agentList.totalPages}</p>
		</div>

		<nav>
			<ul class="pagination">
				<li><a
					class="${pageable.hasPrevious() ? 'enabled' : 'disabled'}"
					href="${pageContext.request.contextPath}/search/${pageable.previous().pageNumber}?agentname=${agentname}"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a></li>
				<li><a
					class="${pageable.next().pageNumber >= agentList.totalPages ? 'disabled' : 'enabled'}"
					href="${pageContext.request.contextPath}/search/${pageable.next().pageNumber}?agentname=${agentname}"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
				<%--			<li><a href="${pageContext.request.contextPath}/search">1</a></li>
				<li><a href="${pageContext.request.contextPath}/search">2</a></li>
				<li><a href="${pageContext.request.contextPath}/search">3</a></li>
				<li><a href="${pageContext.request.contextPath}/search">4</a></li>
				<li><a href="${pageContext.request.contextPath}/search">5</a></li>
				<li><a href="${pageContext.request.contextPath}/search" aria-label="Next"> <span aria-hidden="true">&raquo;</span>%				</a></li>
 --%>
			</ul>
		</nav>
		<p>Please add an agent if the one you are searching for is not
			listed.</p>
	</c:when>
</c:choose>
<a href="${pageContext.request.contextPath}/createagent">Add agent</a>
<%@include file="includes/footer_old.jsp"%>