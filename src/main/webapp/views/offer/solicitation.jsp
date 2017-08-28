<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<form action="offer/candidate/apply.do">
	<div class="form-group" style="width: 55%;">
		<select class="form-control" name="curricula">
			<jstl:forEach var="e" items="${curricula}">
				<option value="${e.id}">${e.ticker}</option>
			</jstl:forEach>
		</select>
	</div>
	
	<input type="submit" class="btn btn-primary" value="<spring:message code="offer.solicitaion"/>">
</form>

