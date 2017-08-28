<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:acme_form hiddenFields="copy" skip_fields="ticker" type="create" entity="${curricula}" url="curricula/candidate/save.do" numberSteps="0.25" cancel="welcome/index.do">
	<input type="hidden" name="ticker" value="${curricula.ticker}">
	<spring:message code="curricula.ticker"/>: <b>${curricula.ticker}</b>
	<br />
	<br />
	<br />
</acme:acme_form>
