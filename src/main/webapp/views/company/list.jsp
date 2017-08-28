<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>



<acme:list requestURI="company/actor/list.do" list="${company}" hidden_fields="creditCard,payments" another_mapped_urls="{company.activityReport:activityreport/list.do}" viewUrl="company/actor/view.do" entityUrl="{offers:offer/company/list.do}"></acme:list>