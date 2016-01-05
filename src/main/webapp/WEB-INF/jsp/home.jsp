<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@include file="includes/header.jsp"%>

<div class="well">
	<p id="justify">Rate My Rental Agent is a simple website to enable
		tenants to view and create ratings for rental agencies. The more
		ratings entered the more helpful this website will be, so please feel
		free to add ratings for agencies you may have used.</p>
	<p>** Simply search for an agent using the box below. Then follow
		the links to view previous ratings or add your own rating. **</p>
</div>
<div id="main">
	<sec:authorize access="isAuthenticated()">
		<a href="<c:url value="/getRatings"></c:url>">Ratings (<span
			id=numberRatings>0</span>)
		</a>
	</sec:authorize>

	<c:choose>
		<c:when test="${hasRatings}">
			<div id="smallbox">
				<p>
					View and edit your ratings&nbsp;&nbsp;<a
						href="<c:url value="/userratings?userid=${userid}"/>">here</a>
				</p>
			</div>
		</c:when>
	</c:choose>
</div>

<script type="text/javascript">
	/* this function will be called when json data has been downloaded from above url */
	function updateRatingLink(data) {
		/* use key from map */
		$("#numberRatings").text(data.number);
	}

	function onLoad() {
		updatePage();
		/* set a timer */
		window.setInterval(updatePage, 5000);
	}

	function updatePage() {
		$.getJSON("<c:url value="/getRatings"></c:url>", updateRatingLink);
	}

	$(document).ready(onLoad);
</script>
<%@include file="includes/footer.jsp"%>