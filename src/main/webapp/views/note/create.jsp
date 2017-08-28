<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:acme_form areaFields="remark" skip_fields="status" hiddenFields="reply,replyMoment,createdMoment" type="create" entity="${note}" url="note/verifier/save.do" numberSteps="0.25" cancel="curricula//actor/list-all.do">
	<div class="form-group" style="width: 55%;">
		<select class="form-control" name="status">
			<option value="PENDING">PENDING</option>
			<option value="CANCELLED">CANCELLED</option>
			<option value="CORRECTED">CORRECTED</option>
			<option value="REJECTED">REJECTED</option>
		</select>
	</div>
</acme:acme_form>