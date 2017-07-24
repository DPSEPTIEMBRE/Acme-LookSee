

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="company/create.do" modelAttribute="company" method="POST">

		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<form:hidden path="activities"/>
		<form:hidden path="folders"/>
		<form:hidden path="offers"/>
		<form:hidden path="creditCard"/>
		<form:hidden path="userAccount.authorities"/>
		
		<acme:textbox code="company.username" path="userAccount.username"/>
		<acme:password code="company.password" path="userAccount.password"/>
		
		
		<acme:textbox code="company.actorname" path="actorName"/>
		<acme:textbox code="company.surname" path="surname"/>
		<acme:textbox code="company.email" path="email"/>
		<acme:textbox code="company.phone" path="phone"/>
		<acme:textbox code="company.address" path="address"/>
		<acme:textbox code="company.companyName" path="companyName"/>
		<acme:textbox code="company.VAT" path="VAT"/>
		
		<spring:message code="company.signUp" var="useractorSaveHeader"/>
		<spring:message code="company.cancel" var="cancel" />
		
		<input type="submit" name="save" value="${useractorSaveHeader}" />
		<input onclick="window.location='';" type="button" name="cancel" value="${cancel}" />

</form:form>