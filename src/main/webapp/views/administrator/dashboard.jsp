
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMINISTRATOR')">

<!-- LEVEL C -->

	<!-- El listado de candidatos, ordenados en orden descendente por número de currículos. -->
	<b><spring:message code="administrator.orderByNumCurriculas" /></b>
	<jstl:forEach var="candidates" items="${orderByNumCurriculas}"
		varStatus="loop">
		<jstl:out value="${candidates.actorName}" />
		<jstl:if test="${!loop.last}">, </jstl:if>
	</jstl:forEach>
	<br />

	<!-- El listado de compañías, ordenados en orden descendente por
número de ofertas -->
	<b><spring:message code="administrator.orderByNumOffers" /></b>
	<jstl:forEach var="companies" items="${orderByNumOffers}"
		varStatus="loop">
		<jstl:out value="${companies.actorName}" />
		<jstl:if test="${!loop.last}">, </jstl:if>
	</jstl:forEach>
	<br />

	<!-- La media de currículos por candidato. -->
	<b><spring:message code="administrator.avgCurriculasByCandidate" /> </b>
	<jstl:out value="${avgCurriculasByCandidate}" />
	<br />
 
	<!-- La media de ofertas por compañía. -->
	<b><spring:message code="administrator.avgNumberOfferByCompany" /> </b>
	<jstl:out value="${avgNumberOfferByCompany}" />
	<br />

	<!-- Los candidatos que han registrado más currículos. -->
	<b><spring:message code="administrator.CandidateWithMoreCurriculas" /></b>
	<jstl:forEach var="candidate" items="${CandidateWithMoreCurriculas}"
		varStatus="loop">
		<jstl:out value="${candidate.actorName}" />
		<jstl:if test="${!loop.last}">, </jstl:if>
	</jstl:forEach>
	<br />

	<!-- Las compañías que han registrado más ofertas. -->
	<b><spring:message code="administrator.companyMaxOffers" /></b>
	<jstl:forEach var="companies" items="${companyMaxOffers}"
		varStatus="loop">
		<jstl:out value="${companies.actorName}" />
		<jstl:if test="${!loop.last}">, </jstl:if>
	</jstl:forEach>
	<br />
	
	<!-- La media, mínimo y máximo de solicitudes por candidato. -->
	<b><spring:message code="administrator.AvgApplicationsByCandidate" /> </b>
	<jstl:out value="${AvgMaxMinApplicationsByCandidate[0]}" />
	<br />
	<b><spring:message code="administrator.MinApplicationsByCandidate" />
	</b>
	<jstl:out value="${AvgMaxMinApplicationsByCandidate[1]}" />
	<br />
	<b><spring:message code="administrator.MaxApplicationsByCandidate" />
	</b>
	<jstl:out value="${AvgMaxMinApplicationsByCandidate[2]}" />
	<br />

	
	<!-- La media, mínimo y máximo de solicitudes por oferta. -->
	<b><spring:message code="administrator.AvgApplicationsByOffers" /> </b>
	<jstl:out value="${AvgMaxMinApplicationsByOffers[0]}" />
	<br />
	<b><spring:message code="administrator.MinApplicationsByOffers" />
	</b>
	<jstl:out value="${AvgMaxMinApplicationsByOffers[1]}" />
	<br />
	<b><spring:message code="administrator.MaxApplicationsByOffers" />
	</b>
	<jstl:out value="${AvgMaxMinApplicationsByOffers[2]}" />
	<br />

	<!-- La media, mínimo y máximo de solicitudes pendientes por compañía. -->
	<b><spring:message code="administrator.AvgApplicationsPendingByCompany" /> </b>
	<jstl:out value="${AvgMaxMinApplicationsPendingByCompany[0]}" />
	<br />
	<b><spring:message code="administrator.MinApplicationsPendingByCompany" />
	</b>
	<jstl:out value="${AvgMaxMinApplicationsPendingByCompany[1]}" />
	<br />
	<b><spring:message code="administrator.MaxApplicationsPendingByCompany" />
	</b>
	<jstl:out value="${AvgMaxMinApplicationsPendingByCompany[2]}" />
	<br />
	
	<!-- La media, mínimo y máximo de solicitudes aceptadas por compañía. -->
	<b><spring:message code="administrator.AvgApplicationsAcceptedByCompany" /> </b>
	<jstl:out value="${AvgMaxMinApplicationsAcceptedByCompany[0]}" />
	<br />
	<b><spring:message code="administrator.MinApplicationsAcceptedByCompany" />
	</b>
	<jstl:out value="${AvgMaxMinApplicationsAcceptedByCompany[1]}" />
	<br />
	<b><spring:message code="administrator.MaxApplicationsAcceptedByCompany" />
	</b>
	<jstl:out value="${AvgMaxMinApplicationsAcceptedByCompany[2]}" />
	<br />
	
	<!-- La media, mínimo y máximo de solicitudes rechazadas por compañía. -->
	<b><spring:message code="administrator.AvgApplicationsRejectedByCompany" /> </b>
	<jstl:out value="${AvgMaxMinApplicationsRejectedByCompany[0]}" />
	<br />
	<b><spring:message code="administrator.MinApplicationsRejectedByCompany" />
	</b>
	<jstl:out value="${AvgMaxMinApplicationsRejectedByCompany[1]}" />
	<br />
	<b><spring:message code="administrator.MaxApplicationsRejectedByCompany" />
	</b>
	<jstl:out value="${AvgMaxMinApplicationsRejectedByCompany[2]}" />
	<br />

<!-- LEVEL B -->

<!-- El mínimo, máximo y media de registros de actividad por actor. -->
<b><spring:message code="administrator.MinActiviesByActor" /> </b>
	<jstl:out value="${MinMaxAvgActiviesByActor[0]}" />
	<br />
	<b><spring:message code="administrator.MaxActiviesByActor" />
	</b>
	<jstl:out value="${MinMaxAvgActiviesByActor[1]}" />
	<br />
	<b><spring:message code="administrator.AvgActiviesByActor" />
	</b>
	<jstl:out value="${MinMaxAvgActiviesByActor[2]}" />
	<br />

<!-- Los actores que tienen ±10% de la media de registros de actividad por actor. -->
<b><spring:message code="administrator.actorsBetweenTenPercentActivities" /></b>
	<jstl:forEach var="actors" items="${actorsBetweenTenPercentActivities}"
		varStatus="loop">
		<jstl:out value="${actors}" />
		<jstl:if test="${!loop.last}">, </jstl:if>
	</jstl:forEach>
	<br />

<!-- El mínimo, máximo y media de pagos por compañía. -->
<b><spring:message code="administrator.minPaymentsByCompany" /> </b>
	<jstl:out value="${minMaxAvgPaymentsByCompany[0]}" />
	<br />
	<b><spring:message code="administrator.MaxPaymentsByCompany" />
	</b>
	<jstl:out value="${minMaxAvgPaymentsByCompany[1]}" />
	<br />
	<b><spring:message code="administrator.AvgPaymentsByCompany" />
	</b>
	<jstl:out value="${minMaxAvgPaymentsByCompany[2]}" />
	<br />

<!-- El ratio de compañías con pagos no finalizados. -->
<b><spring:message code="administrator.RatioCompanyNoFinish" /> </b>
	<jstl:out value="${RatioCompanyNoFinish}" />
	<br />

<!-- La media de pagos no finalizados por compañía -->
<b><spring:message code="administrator.AvgPaymentsNoFinishByCompany" /> </b>
	<jstl:out value="${AvgPaymentsNoFinishByCompany}" />
	<br />

<!-- La media de notas por verificador. -->
<b><spring:message code="administrator.avgNotesByVerifier" /> </b>
	<jstl:out value="${avgNotesByVerifier}" />
	<br />

<!-- La media de notas, agrupadas por estado. -->
<b><spring:message code="administrator.avgNotesByVerifierGroupByStatusPENDING" /> </b>
	<jstl:out value="${avgNotesByVerifierGroupByStatus[0]}" />
	<br />
	<b><spring:message code="administrator.avgNotesByVerifierGroupByStatusCANCELLED" />
	</b>
	<jstl:out value="${avgNotesByVerifierGroupByStatus[1]}" />
	<br />
	<b><spring:message code="administrator.avgNotesByVerifierGroupByStatusCORRECTED" />
	</b>
	<jstl:out value="${avgNotesByVerifierGroupByStatus[2]}" />
	<br />
	<b><spring:message code="administrator.avgNotesByVerifierGroupByStatusREJECTED" />
	</b>
	<jstl:out value="${avgNotesByVerifierGroupByStatus[2]}" />
	<br />


</security:authorize>
