<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<script src="https://www.google.com/recaptcha/api.js">
	
</script>


<div id="container">
	<spring:message code="company.actorname" var="name" />
	<spring:message code="company.surname" var="surname" />
	<spring:message code="company.email" var="email" />
	<spring:message code="company.phone" var="phone" />
	<spring:message code="company.address" var="address" />
	<spring:message code="company.companyName" var="company" />
	<spring:message code="company.VAT" var="vat" />
	<spring:message code="company.username" var="user" />
	<spring:message code="company.password" var="password" />
	<spring:message code="company.signUp" var="signup" />
	<spring:message code="company.cancel" var="cancel" />
	<spring:message code="company.message" var="message" />
	<spring:message code="company.agree" var="agree" />

	<form:form role="form" action="company/signUp.do" modelAttribute="companyRegisterForm">
		
		<!--  
		<jstl:if test="${showError == true}">
			<div class="alert alert-danger alert-dismissible" role="alert">
				<spring:message code="${message}" />
			</div>
			<br />
		</jstl:if>-->
		
			
		

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="activities" />
		<form:hidden path="folders" />
		<form:hidden path="offers" />
		<form:hidden path="creditCard" />
		<form:hidden path="userAccount.authorities" />
		
		
		<form:label path="actorName"> ${name} </form:label>
		<form:input path="actorName"/>
		<form:errors cssClass="error" path="actorName" /> <br />
		
		<form:label path="surname"> ${surname} </form:label>
		<form:input path="surname"/>
		<form:errors cssClass="error" path="surname" /> <br />
		
		<form:label path="email"> ${email} </form:label>
		<form:input path="email"/>
		<form:errors cssClass="error" path="mail" /> <br />
		
		<form:label path="phone"> ${phone} </form:label>
		<form:input path="phone"/>
		<form:errors cssClass="error" path="phone" /> <br />
		
		<form:label path="address"> ${address} </form:label>
		<form:input path="address"/>
		<form:errors cssClass="error" path="address" /> <br />
		
		<form:label path="userAccount.username"> ${user} </form:label>
		<form:input path="userAccount.username"/>
		<form:errors cssClass="error" path="userAccount.username" /> <br />
		
		<form:label path="userAccount.password"> ${password} </form:label>
		<form:password path="userAccount.password"/>
		<form:errors cssClass="error" path="userAccount.password" /> <br />
		
		<form:hidden path="userAccount.authorities"/>
		
		<input type="submit" name="save" value="${signup}" />
		
		<input onclick="window.location='company/cancel.do';" type="button" name="cancel" value="${cancel}" />
	
	</form:form>
</div>
