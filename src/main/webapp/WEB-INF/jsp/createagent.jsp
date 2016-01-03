<%@include file="includes/header_old.jsp"%>

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Add estate agent</h3>
	</div>
	<c:if test="${param.error != null}">
		<span class="loginerror"> Sign in failed. Please re-enter your
			username and password. </span>
	</c:if>
	<div class="panel-body">
		<c:if test="${param.error != null}">
			<div class="alert alert-danger">Invalid username and password.
			</div>
		</c:if>

		<c:if test="${param.logout != null}">
			<div class="alert alert-danger">You have been logged out.</div>
		</c:if>

		<form:form role="form"
			action='${pageContext.request.contextPath}/docreateagent'
			method='POST' commandName="agent">
			<div class="form-group">
				<form:label path="agentName">Agent Name</form:label> 
				<form:input path="agentName" id="agentname" type="text" class="form-control" placeholder="Enter agent name"/>
				<p class="help-block">Enter the agent name.</p>
				<form:errors path="agentName" cssClass="error"></form:errors>
			</div>

			<div class="form-group">
				<form:label path="suburb">Suburb</form:label>
				<form:input path="suburb" type="text" id="suburb" class="form-control"/>
				<form:errors cssClass="error" path="suburb" />
			</div>

			<button type="submit" class="btn-btn-primary">Add agent</button>
		</form:form>
	</div>
	
</div>
<script type="text/javascript">
	$(document).ready(function() {
		document.getElementById("agentname").focus();
	});
</script>
<%@include file="includes/footer_old.jsp"%>