<%@include file="includes/header_old.jsp"%>
<%@include file="newuserscript.jsp"%>

<div class="panel panel-primary">
	<div class="panel-heading"><h3 class="panel-title">Create new user</h3></div>
	
	<div class="panel-body">
		<form:form id="details" role="form"
			action='${pageContext.request.contextPath}/createuser' modelAttribute="signupForm"
			method='POST'>
			<form:errors/>
			<div class="form-group">
				<form:label path="email">Username</form:label>
				<form:input path="email" type="email" class="form-control" placeholder="Enter your email address" id="username" />
				<div class="error"><form:errors path="email"></form:errors></div>
				<p class="help-block">Enter a unique email address.  It will also be your login.</p>
			</div>

			<div class="form-group">
				<form:label path="password">Password</form:label> 
				<form:input type="password" path="password" id="password" name="password" class="form-control"/>
				<div class="error"><form:errors path="password"></form:errors></div>
				<form:errors cssClass="error" path="password" />
			</div>

			<div class="form-group">
				<label>Confirm password</label> 
				<input type="password" name="confirmpassword" id="confirmpassword" class="form-control"/>
				<div class="error"><form:errors path="password"></form:errors></div>
				<div id="matchpasswords"></div>
				<form:errors cssClass="error" path="password" />
			</div>

			<div class="form-group">
				<label for="checkbox">Remember me?</label> <input class="login"
					type='checkbox' name='_spring_security_remember_me'
					checked="checked" />
			</div>
			<button type="submit" class="btn-btn-primary">Create account</button>
		</form:form>
	</div>
	
</div>
<%@include file="includes/footer_old.jsp"%>