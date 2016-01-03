<%@include file="includes/header_old.jsp"%>

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Please sign in</h3>
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
			action='${pageContext.request.contextPath}/login'
			method='POST'>
			<div class="form-group">
				<label for="username">Username</label> <input id="username"
					name="username" type="email" class="form-control">
				<p class="help-block">Enter your email address.</p>
			</div>

			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					name="password" class="form-control">
				<form:errors cssClass="error" path="password" />
			</div>

			<div class="form-group">
				<label for="checkbox">Remember me?</label> <input class="login"
					type='checkbox' name='_spring_security_remember_me'
					checked="checked" />
			</div>
			<button type="submit" class="btn-btn-primary">Sign in</button>
			
			<h4>No account yet? <span class="label label-default"><a href="<c:url value="/newuser"/>">Create
					account</a></span></h4>
		</form:form>
	</div>
	
</div>
<script type="text/javascript">
	$(document).ready(function() {
		document.f.j_username.focus();
	});
</script>
<%@include file="includes/footer_old.jsp"%>