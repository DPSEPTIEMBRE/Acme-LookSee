<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('CANDIDATE')">
	<div class="btn-group btn-group-xs" role="group" aria-label="label">
		<button onclick="javascript:location.href='curricula/candidate/create.do'" type="button" class="btn btn-default"><spring:message code="curricula.create"/></button>
	</div>
</security:authorize>

<br />

<acme:list edit_auth="CANDIDATE" viewUrl="curricula/actor/view.do" hidden_fields="copy" entityUrl="{notes:note/actor/list.do, miscellaneousRecords:miscellaneousrecord/candidate/list.do, professionalRecords:professionalrecord/candidate/list.do, educationRecords:educationrecord/candidate/list.do, endorserRecords:endorserrecord/candidate/list.do, personalRecord:personalrecord/candidate/view.do}" list="${curricula}" editUrl="curricula/candidate/edit.do" requestURI="curricula/actor/list.do" pagesize="6">
</acme:list>
