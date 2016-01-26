<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="includes/header.jsp"%>

<div class="container">
	<form:form method="POST"
		action="${pageContext.request.contextPath}/docreate?agentid=${agent.id}"
		commandName="rating">
		<div class="well well-rate">
			<h3>${agent.agentname}(${agent.suburb})</h3>
			<c:set var="score" value="${rating.rating}"></c:set>
			<div class="radio">
				<label for=1><input type="radio" name="rating" value=1
					<c:if test="${score==1}">checked</c:if>>Very Poor</label>
			</div>
			<div class="radio">
				<label for=2><input type="radio" name="rating" value=2
					<c:if test="${score==2}">checked</c:if>>Poor</label>
			</div>
			<div class="radio">
				<label for=3><input type="radio" name="rating" value=3
					<c:if test="${score==3}">checked</c:if>>Average</label>
			</div>
			<div class="radio">
				<label for=4><input type="radio" name="rating" value=4
					<c:if test="${score==4}">checked</c:if>>Good</label>
			</div>
			<div class="radio">
				<label for=5><input type="radio" name="rating" value=5
					<c:if test="${score==5}">checked</c:if>>Excellent</label>
			</div>

			<input type="hidden" name="agentID" value="${agent.id}"> <input
				type="hidden" name="ratingID" value="${rating.id}"> <input
				type="hidden" name="userID" value="${user.id}">

			<c:if test="${rating.id eq null}">
				<input type="submit" class="btn btn-primary" value="RATE AGENT">
			</c:if>
			<c:if test="${rating.id > 0}">
				<input type="submit" class="btn btn-primary" name="edit"
					value="Confirm edit">
			</c:if>
		</div>
	</form:form>
</div>
<%@include file="includes/footer.jsp"%>