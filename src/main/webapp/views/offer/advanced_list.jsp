<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form action="offer/candidate/list-advanced.do" method="get">
	<div class="form-group" style="width: 35%;">
		<input placeholder="<spring:message code="offer.date.word"/>" name="q" type="search" class="form-control">
		<input placeholder="<spring:message code="offer.minRange"/>" name="min" type="number" min="0" step="1" class="form-control">
		<input placeholder="<spring:message code="offer.maxRange"/>" name="max" type="number" min="0" step="1" class="form-control">
	</div>
	
	<br />
	
	<input class="btn btn-primary" type="submit" value="<spring:message code="acme.search"/>">
</form>

<br />

<acme:list viewUrl="offer/actor/view.do" edit_auth="COMPANY" hidden_fields="applications" entityUrl="{company:company/actor/view.do}" list="${offer}" editUrl="offer/company/edit.do" requestURI="offer/list.do" pagesize="6"/>
