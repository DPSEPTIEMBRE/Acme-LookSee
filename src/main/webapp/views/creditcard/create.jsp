<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:acme_form skip_fields="brandName" type="create" entity="${creditcard}" url="creditcard/company/save.do" min_of="{CVV:100, number:0}" max_of="{CVV:999, number:99999999999999999999}" numberSteps="1" numberMin="1" numberMax="10" cancel="creditcard/company/view.do">
	<div class="form-group" style="width: 55%;">
		<label for="label"><spring:message code='creditcard.brandName'/></label>
		<select name="brandName">
			<option value="VISA">VISA</option>
			<option value="MASTERCARD">MASTERCARD</option>
			<option value="DISCOVER">DISCOVER</option>
			<option value="DINNERS">DINNERS</option>
			<option value="AMEX">AMEX</option>
		</select>
	</div>
</acme:acme_form>