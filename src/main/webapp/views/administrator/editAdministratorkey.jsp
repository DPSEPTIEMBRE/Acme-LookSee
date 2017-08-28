<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<jstl:if test="${msg != null && (msg eq 'cache.time' || msg eq 'cache.max.results' || msg eq 'cache.last.paid')}">
	<div class="alert alert-warning" style="max-width: 55%;">
		<spring:message code="${msg}" />
	</div>
</jstl:if>

<acme:acme_form url="administrator/administratorKey/edit.do" skip_fields="keyValue" entity="${administratorkey}" type="edit" hiddenFields="keyName">
	<div class="form-group" style="width: 55%;">
		<label for="label"><spring:message code="administratorkey.keyValue" /></label>
		<input value="${administratorkey.keyValue}" name="keyValue" type="number" min="0" max="100" step="1" class="form-control">
	</div>
</acme:acme_form>

