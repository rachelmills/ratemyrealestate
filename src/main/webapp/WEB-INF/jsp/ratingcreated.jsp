<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${edit != null}">
	<h2>Rating has been updated</h2>
</c:if>
<c:if test="${edit == null}">
	<h2>Rating has been created</h2>
</c:if>
