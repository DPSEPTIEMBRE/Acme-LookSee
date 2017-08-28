<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page import="org.springframework.context.i18n.LocaleContextHolder"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div>
	<img src="images/logo.png" alt="Acme-LookSee Co., Inc." />
</div>

<div style="width: 50%;">
	<nav class="navbar navbar-default" style="margin-bottom: 0;">
		<div class="container-fluid">
			<div class="navbar-header">
				<ul class="nav navbar-nav">
					<security:authorize access="isAnonymous()">
						<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
					</security:authorize>
					
					<security:authorize access="isAuthenticated()">
						<li class="dropdown">
				          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><security:authentication property="principal.username" /><span class="caret"></span></a>
				          <ul class="dropdown-menu">
				          	<li><a href="folder/actor/list.do"><spring:message code="master.page.actor.folders" /> </a></li>
				          	<li><a href="activityreport/actor/mylist.do"><spring:message code="master.page.myActivityReports" /> </a></li>
				          	<security:authorize access="hasRole('ADMINISTRATOR')">
				          		<li><a href="administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /> </a></li>
				          		<li><a href="administrator/edit.do"><spring:message code="master.page.actor.edit" /> </a></li>
				          		<li><a href="administrator/administratorKey/list.do"><spring:message code="master.page.administrator.keys" /> </a></li>
				          		<li><a href="administrator/payment.do"><spring:message code="master.page.administrator.paid" /> </a></li>
				          	</security:authorize>
				          	<security:authorize access="hasRole('CANDIDATE')">
				          		<li><a href="candidate/actor/edit.do"><spring:message code="master.page.actor.edit" /> </a></li>
				          		<li><a href="curricula/actor/list.do"><spring:message code="master.page.actor.curricula" /> </a></li>
				          		<li><a href="application/candidate/list.do"><spring:message code="master.page.application.list" /> </a></li>
				          		<li><a href="offer/candidate/list-advanced.do"><spring:message code="master.page.candidate.advanced.list" /> </a></li>
				          	</security:authorize>
				          	<security:authorize access="hasRole('COMPANY')">
				          		<li><a href="payment/company/list.do"><spring:message code="master.page.payment.list" /> </a></li>
				          		<li><a href="company/actor/edit.do"><spring:message code="master.page.actor.edit" /> </a></li>
				          		<li><a href="offer/company/list.do"><spring:message code="master.page.offer.company.list" /> </a></li>
				          		<li><a href="creditcard/company/view.do"><spring:message code="master.page.company.creditcard" /> </a></li>
				          		<li><a class="fNiv" href="offer/company/create.do"><spring:message code="company.create.offer" /></a></li>
				          		<li><a class="fNiv" href="application/company/list.do"><spring:message code="master.page.application.company.list" /></a></li>
				          		<li><a class="fNiv" href="candidate/company/list-advanced.do"><spring:message code="master.page.company.advanced.list" /></a></li>
				          	</security:authorize>
				          	<security:authorize access="hasRole('VERIFIER')">
				          		<li><a href="curricula/actor/list-all.do"><spring:message code="master.page.actor.list.all" /> </a></li>
				          		<li><a href="curricula/verifier/list-groupby.do"><spring:message code="master.page.curricula.list.group" /> </a></li>
				          		<li><a href="note/verifier/list-groupby.do"><spring:message code="master.page.note.list.group" /> </a></li>
				          	</security:authorize>
				          	
							<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				          </ul>
				        </li>
					</security:authorize>
					
					<security:authorize access="!isAuthenticated()">
						<li><a class="fNiv" href="candidate/actor/create.do"><spring:message code="master.page.candidate.register"/></a></li>
						<li><a class="fNiv" href="company/actor/create.do"><spring:message code="master.page.company.register"/></a></li>
					</security:authorize>
					
					<li><a class="fNiv" href="company/actor/list.do"><spring:message code="master.page.companies.list.all"/></a></li>
					<li><a class="fNiv" href="offer/actor/listAll.do"><spring:message code="master.page.offer.company.list.all"/></a></li>
				</ul>
			</div>
		</div>
	</nav>
	<a href="?language=en"> <img src="images/flag_en.png" /> </a>
 	<a href="?language=es"> <img src="images/flag_es.png" /> </a>
</div>
