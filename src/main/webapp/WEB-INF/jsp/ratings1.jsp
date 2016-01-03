<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div id="ratings"></div>

<script type="text/javascript">
	var timer;

	function showComment(i) {
		stopTimer();
		$("#form" + i).toggle();
	}

	function success(data) {
		/* will receive json data returned from handler */
		startTimer();
		$("#form" + data.target).toggle();
		$("#alert" + data.target).text("Comment sent");
	}

	function error(data) {
		alert('Error sending message');
	}

	function sendComment(i, agent, suburb) {

		var text = $("#textbox" + i).val();
		/* curly brackets denote the hash */
		/* this query sends data and gets data back */
		$.ajax({
			"type" : 'POST',
			"url" : '<c:url value="/sendComment"></c:url>',
			/* turn data into string to be able to supply it */
			"data" : JSON.stringify({
				"target" : i,
				"text" : text,
				"agentname" : agent,
				"suburb" : suburb
			}),
			"success" : success,
			"error" : error,
			"contentType" : "application/json",
			"dataType" : "json"
		});
	}

	/* this function will be called when json data has been downloaded from above url */
	function showRatings(data) {

		$("div#ratings").html("");
		/* use key from map */
		for (var i = 0; i < data.ratings.length; i++) {
			var rating = data.ratings[i];

			var ratingDiv = document.createElement("div");
			ratingDiv.setAttribute("class", "ratingdiv");

			var agentname = document.createElement("span");
			agentname.setAttribute("class", "agent");
			agentname.appendChild(document
					.createTextNode(rating.agent.agentName + " "
							+ rating.agent.suburb));

			var user = document.createElement("span");
			user.setAttribute("class", "user");
			user.appendChild(document.createTextNode(rating.user.username));

			var ratingspan = document.createElement("span");
			ratingspan.setAttribute("class", "ratingvalue");
			ratingspan.appendChild(document.createTextNode(rating.rating));

			var link = document.createElement("a");
			link.setAttribute("class", "commentlink");
			link.setAttribute("href", "#");
			link.setAttribute("onclick", "showComment(" + i + ")");
			link.appendChild(document.createTextNode(rating.rating));

			ratingspan.appendChild(link);
			
			var alertSpan = document.createElement("span");
			alertSpan.setAttribute("class", "alert");
			alertSpan.setAttribute("id", "alert" + i);
			/* alertSpan.appendChild(document.createTextNode("Comment sent")); */

			var commentForm = document.createElement("form");
			commentForm.setAttribute("class", "commentForm");
			commentForm.setAttribute("id", "form" + i);

			var textArea = document.createElement("textarea");
			textArea.setAttribute("class", "commentarea");
			textArea.setAttribute("id", "textbox" + i);

			var replyButton = document.createElement("input");
			replyButton.setAttribute("type", "button");
			replyButton.setAttribute("class", "commentbutton");
			replyButton.setAttribute("value", "Comment");
			/* sets onclick to return value of the outer annonymous function
			we are return a reference to another anonymous function that
			calls sendComment */
			replyButton.onclick = function(j, agent, suburb) {
				return function() {
					sendComment(j, agent, suburb);
				};
				/* the following (i) means that the function will be called 
				immediately (as soon as onclick is executed) */
			}(i, rating.agent.agentName, rating.agent.suburb);

			commentForm.appendChild(textArea);
			commentForm.appendChild(replyButton);

			ratingDiv.appendChild(agentname);
			ratingDiv.appendChild(user);
			ratingDiv.appendChild(ratingspan);
			ratingDiv.appendChild(alertSpan);
			ratingDiv.appendChild(commentForm);

			$("div#ratings").append(ratingDiv);
		}
		$("#numberRatings").text(data.number);
	}

	function onLoad() {
		updatePage();
		/* set a timer */
		startTimer();
	}

	function updatePage() {
		$.getJSON("<c:url value="/getRatings"></c:url>", showRatings);
	}

	function startTimer() {
		/* setInterval returns a value */
		timer = window.setInterval(updatePage, 10000);
	}

	function stopTimer() {
		window.clearInterval(timer);
	}

	$(document).ready(onLoad);
</script>


<!-- use jQuery.post() to send JSON -->