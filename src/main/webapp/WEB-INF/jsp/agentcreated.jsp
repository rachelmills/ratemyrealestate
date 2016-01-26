<%@include file="includes/header.jsp"%>

<div class="alert alert-success alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
${agent.agentname} has been created</div>

<div class="panel panel-info">
<div class="panel-heading"><a href="${pageContext.request.contextPath}/createrating?agentid=${agent.id}">Add a rating for ${agent.agentname}</a></div>
</div>

<%@include file="includes/footer.jsp"%>