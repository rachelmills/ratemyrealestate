<%@include file="includes/header.jsp"%>
<div class="panel panel-default">
<div class="panel-heading">Panel heading without title</div>
  <h3 class="panel-title">Please sign up</h3>
  
  <div class="panel-body">
  <form:form modelAttribute="signupForm" role="form">
  
  <form:errors/>
  
    <div class="form-group">
    	<form:label path="email">Email address</form:label>
    	<form:input path="email" type="email" class="form-control" placeholder="Enter your email address"/>
		<form:errors cssClass="error" path="email"></form:errors>
    	<p class="help-block">Enter a unique email address.  It will also be your login.</p>
    </div>
    
    <div class="form-group">
    	<form:label path="name">Name</form:label>
    	<form:input path="name" class="form-control" placeholder="Enter name"/>
    	<form:errors cssClass="error" path="name"></form:errors>
    	<p class="help-block">Enter your display name.</p>
    </div>

    <div class="form-group">
    	<form:label path="password">Password</form:label>
    	<form:password path="password" class="form-control" placeholder="Password"/>
    	<form:errors cssClass="error" path="password"></form:errors>
    	<p class="help-block">Enter your display name.</p>
    </div>
    
    <button type="submit" class="btn btn-default">Submit</button>
    
    </form:form>

  </div>
</div>




<p>Sign Up</p>
<p>
Welcome
${name}
</p>

<%@include file="includes/footer.jsp"%>