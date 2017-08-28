<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form action="note/verifier/list-groupby.do">
	<div class="form-group" style="width: 55%;">
		<label class="radio-inline"><input <jstl:if test="${q == 0}"><jstl:out value="checked='checked'"/></jstl:if> type="radio" value="0" name="q"><spring:message code="note.group.by.state"/></label>
		<label class="radio-inline"><input <jstl:if test="${q == 1}"><jstl:out value="checked='checked'"/></jstl:if> type="radio" value="1" name="q"><spring:message code="note.group.by.candidate"/></label>
	</div>
	<script>
		$('input[type=radio]').on('change', function() {
		    $(this).closest("form").submit();
		});
	</script>
</form>

<br />

<acme:list edit_show_null_conditions="replyMoment" field_mapping="{status:value}" hidden_fields="copy,curricula" entityUrl="{}" list="${note}" editUrl="note/actor/edit.do" requestURI="note/candidate/list.do" pagesize="6">

</acme:list>