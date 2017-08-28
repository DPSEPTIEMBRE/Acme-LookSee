<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<jstl:if test="${dateError != null}">
	<div class="alert alert-warning" style="max-width: 55%;">
		<spring:message code='offer.date.after' />
	</div>
</jstl:if>

<acme:acme_form date_stamp="deadlineApply" areaFields="description" type="create" entity="${offer}" url="offer/company/save.do" numberSteps="0.25" numberMin="0" cancel="welcome/index.do">

</acme:acme_form>