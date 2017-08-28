<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form action="candidate/company/list-advanced.do" method="get">
	<div class="form-group" style="width: 35%;">
		<input placeholder="<spring:message code="offer.date.word"/>" name="q" type="search" class="form-control">
	</div>
	
	<br />
	
	<input class="btn btn-primary" type="submit" value="<spring:message code="acme.search"/>">
</form>

<br />
<acme:list entityUrl="{curriculas:curricula/actor/list.do}" requestURI="candidate/company/list-advanced.do" hidden_fields="applications,folders,activities,userAccount,address" list="${candidate}"></acme:list>

