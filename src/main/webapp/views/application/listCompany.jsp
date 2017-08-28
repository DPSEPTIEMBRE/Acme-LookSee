<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:list color_fields="{status:PENDING:orange,status:ACCEPTED:#BEF781,status:REJECTED:red}" sortable="true" field_mapping="{status:value}" entityUrl="{offer:offer/actor/view.do, curricula:curricula/actor/view.do}" list="${application}" requestURI="application/company/list.do" pagesize="6" editUrl="application/candidate/edit.do"></acme:list>
