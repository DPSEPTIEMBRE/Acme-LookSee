<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('CANDIDATE')">
	<div class="btn-group btn-group-xs" role="group" aria-label="label">
		<button onclick="javascript:location.href='offer/candidate/solicitation.do?q=${offer.id}'" type="button" class="btn btn-default"><spring:message code="offer.solicitaion"/></button>
	</div>
	<br />
	<br />
</security:authorize>
<acme:acme_view entity="${offer}" skip_fields="applications"></acme:acme_view>