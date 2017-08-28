<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<jstl:if test="${creditcard != null}">
	<a href="creditcard/company/edit.do"><spring:message code="creditcard.edit"/></a>
	<br />
	<br />
	<acme:acme_view field_mapping="{brandName:value}" entity="${creditcard}"/>
</jstl:if>
<jstl:if test="${creditcard == null}">
	<a href="creditcard/company/create.do"><spring:message code="creditcard.create"/></a>
</jstl:if>