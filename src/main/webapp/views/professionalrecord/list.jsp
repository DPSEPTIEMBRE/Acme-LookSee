<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:list hidden_fields="copy" entityUrl="{}" image_fields="attachment" list="${professionalrecord}" deleteUrl="professionalrecord/candidate/delete.do" editUrl="professionalrecord/candidate/edit.do" requestURI="professionalrecord/candidate/list.do" pagesize="6">
</acme:list>
