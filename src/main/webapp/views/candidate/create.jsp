<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="candidate/create.do" modelAttribute="candidate" method="POST">

		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<form:hidden path="activities"/>
		<form:hidden path="folders"/>
		<form:hidden path="curriculas"/>
		<form:hidden path="applications"/>
		<form:hidden path="userAccount.authorities"/>
		
		
		<acme:textbox code="candidate.username" path="userAccount.username"/>
		<acme:password code="candidate.password" path="userAccount.password"/>
		
		
		<acme:textbox code="candidate.actorname" path="actorName"/>
		<acme:textbox code="candidate.surname" path="surname"/>
		<acme:textbox code="candidate.email" path="email"/>
		<acme:textbox code="candidate.phone" path="phone"/>
		<acme:textbox code="candidate.address" path="address"/>
		
		<spring:message code="candidate.signUp" var="useractorSaveHeader"/>
		<spring:message code="candidate.cancel" var="cancel" />
		
		<input type="submit" name="save" value="${useractorSaveHeader}" />
		<input onclick="window.location='';" type="button" name="cancel" value="${cancel}" />

</form:form>