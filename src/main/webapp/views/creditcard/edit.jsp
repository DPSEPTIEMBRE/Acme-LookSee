<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:acme_form type="edit" entity="${creditcard}" url="creditcard/company/edit.do" numberSteps="0.25" cancel="creditcard/company/view.do">
<div class="form-group" style="width: 55%;">
		<label for="label"><spring:message code='creditcard.brandName'/></label>
		<select name="brandName">
			<option value="VISA" <jstl:if test="${creditcard.brandName.value eq 'VISA'}"><jstl:out value="selected='selected'"/></jstl:if>>VISA</option>
			<option value="MASTERCARD" <jstl:if test="${creditcard.brandName.value eq 'MASTERCARD'}"><jstl:out value="selected='selected'"/></jstl:if>>MASTERCARD</option>
			<option value="DISCOVER" <jstl:if test="${creditcard.brandName.value eq 'DISCOVER'}"><jstl:out value="selected='selected'"/></jstl:if>>DISCOVER</option>
			<option value="DINNERS" <jstl:if test="${creditcard.brandName.value eq 'DINNERS'}"><jstl:out value="selected='selected'"/></jstl:if>>DINNERS</option>
			<option value="AMEX" <jstl:if test="${creditcard.brandName.value eq 'AMEX'}"><jstl:out value="selected='selected'"/></jstl:if>>AMEX</option>
		</select>
	</div>
</acme:acme_form>