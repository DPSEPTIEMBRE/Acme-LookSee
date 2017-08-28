<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h3>
	<spring:message code="payment.paymentsPaid"/>
</h3>
<br />
<acme:list entityUrl="{creditCard:creditcard/company/view.do}" list="${paymentsPaid}" requestURI="payment/company/list.do" pagesize="6">
</acme:list>

<h3>
	<spring:message code="payment.paymentsUnpaid"/>
</h3>
<br />
<acme:list entityUrl="{creditCard:creditcard/company/view.do}" list="${paymentsUnpaid}" requestURI="payment/company/list.do" pagesize="6">
</acme:list>
