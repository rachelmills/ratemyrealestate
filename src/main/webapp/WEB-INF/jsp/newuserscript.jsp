<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	function onLoad() {
		$("#password").keyup(checkPasswordsMatch);
		$("#confirmpassword").keyup(checkPasswordsMatch);

		$("#details").submit(canSubmit);
	}

	function canSubmit() {
		var password = $("#password").val();
		var confirmpassword = $("#confirmpassword").val();

		if (password != confirmpassword) {
			alert("<fmt:message key='UnmatchedPasswords.user.password'/>");
			return false;
		} else {
			return true;
		}
	}

	function checkPasswordsMatch() {
		var password = $("#password").val();
		var confirmpassword = $("#confirmpassword").val();
		if (password.length > 3 || confirmpassword.length > 3) {
			if (password == confirmpassword) {
				$("#matchpasswords").text("<fmt:message key='MatchedPasswords.user.password'/>");
				$("#matchpasswords").addClass("valid");
				$("#matchpasswords").removeClass("error");
			} else {
				$("#matchpasswords").text("<fmt:message key='UnmatchedPasswords.user.password'/>");
				$("#matchpasswords").addClass("error");
				$("#matchpasswords").removeClass("valid");
			}
		}
	}

	$(document).ready(onLoad);
</script>