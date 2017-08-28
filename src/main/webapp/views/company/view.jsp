<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('ADMINISTRATOR')">
	<div class="btn-group btn-group-xs" role="group" aria-label="label">
		<button onclick="javascript:location.href='company/administrator/block.do?q=${company.id}'" type="button" class="btn btn-default">
			<jstl:if test="${!company.bloked}">
				<spring:message code="company.admin.block"/>
			</jstl:if>
			<jstl:if test="${company.bloked}">
				<spring:message code="company.admin.unblock"/>
			</jstl:if>
		</button>
	</div>
	<br />
	<br />
</security:authorize>

<acme:acme_view skip_fields="offers,payments,creditCard" entity="${company}">
	<tr>
		<td><h3><spring:message code="company.offers"/></h3></td>
		<td>
			<table class="table">
				<jstl:forEach var="e" items="${company.offers}">
					<tr>
						<td><spring:message code="company.offer.title"/></td>
						<td>${e.title}</td>
					</tr>
					<tr>
						<td><spring:message code="company.offer.description"/></td>
						<td>${e.description}</td>
					</tr>
					<tr>
						<td><spring:message code="company.offer.minRange"/></td>
						<td>${e.minRange} ${e.currency}</td>
					</tr>
					<tr>
						<td><spring:message code="company.offer.maxRange"/></td>
						<td>${e.maxRange} ${e.currency}</td>
					</tr>
					<tr>
						<td><spring:message code="company.offer.deadlineApply"/></td>
						<td><fmt:formatDate value="${e.deadlineApply}" pattern="dd/MM/yyyy"/></td>
					</tr>
					<tr>
						<td colspan="2"><hr /></td>
					</tr>
				</jstl:forEach>
			</table>
		</td>
	</tr>
</acme:acme_view>