<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/public/css/styles.css">
<link rel="stylesheet" href="/public/css/main.css">
</head>

<body>
	<div class="page-header">
		<img alt="House Logo"
			src="${pageContext.request.contextPath}/public/images/House_Logo.png">
		<h1>
			Rate My Rental Agent <small></small>
		</h1>
	</div>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav nav-tabs">
					<li class="active"><a href="<c:url value="/"/>">Home</a></li>
					<li><a href="/about">About</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">View ratings <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value="/allratings"/>">All ratings</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">Separated link</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">One more separated link</a></li>
						</ul></li>

					<li><a
						href="<c:url value="${pageContext.request.contextPath}/createagent"/>">Add
							agent</a></li>
					<c:if test="${param.logout != null}">
						<li><a
							href="<c:url value="${pageContext.request.contextPath}/createagent"/>">Add
								agent</a></li>
					</c:if>
				</ul>
				<form:form class="navbar-form navbar-left" role="search"
					action="${pageContext.request.contextPath}/search/0" method="GET"
					commandName="agentSearch">
					<div class="form-group">
						<form:input path="agentname" type="text" class="form-control"
							placeholder="Find an agent" />
					</div>
					<button type="submit" value="SEARCH" class="btn btn-default">Submit</button>
				</form:form>
				<ul class="nav navbar-nav navbar-right">
					<sec:authorize access="{isAnonymous()}">
						<li><a
							href="<c:url value="${pageContext.request.contextPath}/login"/>">
								<span class="glyphicon glyphicon-log-in"></span>Sign in
						</a></li>
						<li><a href="<c:url value="/newuser" />"> <span
								class="glyphicon glyphicon-list-alt"></span>Sign Up
						</a></li>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <span
								class="glyphicon glyphicon-user"></span> <sec:authentication
									property="principal.user.username" /> <b class="caret"></b>
						</a>
							<ul class="dropdown-menu">
								<li><a
									href="/users/<sec:authentication property='principal.user.id' />"><span
										class="glyphicon glyphicon-user"></span> Profile</a></li>
								<li><c:url var="logoutUrl" value="/logout" /> <form:form
										id="logoutForm" action="${logoutUrl}" method="post">
									</form:form> <a href="#"
									onclick="document.getElementById('logoutForm').submit()"><span
										class="glyphicon glyphicon-log-out"></span> Sign out</a></li>
							</ul></li>
					</sec:authorize>
				</ul>
			</div>

			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<c:if test="${not empty flashMessage}">
		<div class="alert alert-${flashKind} alert-dismissable">
			<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">&times;</button>
			${flashMessage}
		</div>
	</c:if>
	<!-- jquery needed for Bootstrap's Javascript plugins -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>


	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


	<!-- <script>
		$(".nav a").on("click", function(e) {
			$(".nav").find(".active").last().removeClass("active");
			$(this).parent().addClass("active");
			e.stopPropagation();
			/* e.preventDefault(); */
		});
	</script> -->