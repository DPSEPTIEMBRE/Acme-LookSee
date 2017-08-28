<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<div class="btn-group btn-group-xs" role="group" aria-label="label">
	<button onclick="javascript:location.href='educationrecord/candidate/create.do?q=${curricula.id}'" type="button" class="btn btn-default"><spring:message code="curricula.add.educationRecords"/></button>
	<jstl:if test="${curricula.personalRecord != null}">
		<button onclick="javascript:location.href='personalrecord/candidate/edit.do?personalrecord=${curricula.personalRecord.id}&q=${curricula.id}'" type="button" class="btn btn-default"><spring:message code="curricula.edit.personalRecord"/></button>
	</jstl:if>
	<jstl:if test="${curricula.personalRecord == null}">
		<button onclick="javascript:location.href='personalrecord/candidate/create.do?q=${curricula.id}'" type="button" class="btn btn-default"><spring:message code="curricula.add.personalRecord"/></button>
	</jstl:if>
	<button onclick="javascript:location.href='professionalrecord/candidate/create.do?q=${curricula.id}'" type="button" class="btn btn-default"><spring:message code="curricula.add.professionalRecords"/></button>
	<button onclick="javascript:location.href='miscellaneousrecord/candidate/create.do?q=${curricula.id}'" type="button" class="btn btn-default"><spring:message code="curricula.add.miscellaneousRecords"/></button>
	<button onclick="javascript:location.href='endorserrecord/candidate/create.do?q=${curricula.id}'" type="button" class="btn btn-default"><spring:message code="curricula.add.endorserRecords"/></button>
	<!-- <button onclick="javascript:location.href='note/candidate/create.do?q=${curricula.id}'" type="button" class="btn btn-default"><spring:message code="curricula.add.notes"/></button>  -->
</div>

<br />

<acme:acme_form skip_fields="ticker" hiddenFields="copy" type="edit" entity="${curricula}" url="curricula/candidate/edit.do" numberSteps="0.25" cancel="curricula/actor/list.do">
	<br />
	<input type="hidden" name="ticker" value="${curricula.ticker}">
	<spring:message code="curricula.ticker"/>: <b>${curricula.ticker}</b>
	<br />
	<br />
</acme:acme_form>
