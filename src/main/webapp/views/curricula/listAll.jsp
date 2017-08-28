<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form action="curricula/actor/list-all.do" method="get">
	<input style="max-width: 55%;" type="search" class="form-control" name="q" placeholder="<spring:message code='curricula.search'/>">
</form>

<br />

<acme:list viewUrl="curricula/actor/view.do" hidden_fields="copy,miscellaneousRecords,professionalRecords,educationRecords,endorserRecords,personalRecord" entityUrl="{notes:note/actor/list.do}" list="${curricula}" requestURI="curricula/actor/list-all.do" pagesize="6">

</acme:list>

