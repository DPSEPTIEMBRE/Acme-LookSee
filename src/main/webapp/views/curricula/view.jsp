<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div class="btn-group btn-group-xs" role="group" aria-label="label">
	<security:authorize access="hasRole('VERIFIER')">
		<button onclick="javascript:location.href='note/verifier/create.do?q=${curricula.id}'" type="button" class="btn btn-default"><spring:message code="curricula.add.notes"/></button>
	</security:authorize>
</div>

<br />
<br />

<table class="table">
	<tr>
		<td><spring:message code="curricula.ticker"/></td>
		<td>${curricula.ticker}</td>
	</tr>
	<tr>
		<td><spring:message code="curricula.educationRecords"/></td>
		<td>
			<table class="table">
				<jstl:forEach items="${curricula.educationRecords}" var="e">
						<tr>
							<td><spring:message code="curricula.diplomaTitle"/></td>
							<td>${e.diplomaTitle}</td>
						</tr>
						<tr>
							<td><spring:message code="curricula.initialStudying"/></td>
							<td><fmt:formatDate value="${e.initialStudying}" pattern="dd/MM/yyyy"/></td>
						</tr>
						<tr>
							<td><spring:message code="curricula.finalStudying"/></td>
							<td><fmt:formatDate value="${e.finalStudying}" pattern="dd/MM/yyyy"/></td>
						</tr>
						<tr>
							<td><spring:message code="curricula.institution"/></td>
							<td>${e.institution}</td>
						</tr>
						<tr>
							<td><spring:message code="curricula.attachment"/></td>
							<td><a href="${e.attachment}" target="_blank"><img src="${e.attachment}" style="max-width: 100px; max-height: 100px;"></a></td>
						</tr>
						<tr>
							<td><spring:message code="curricula.comments"/></td>
							<td>
								<table class="table">
									<jstl:forEach items="${e.comments}" var="s">
										<tr>
											<td>${s}</td>
										</tr>
									</jstl:forEach>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<hr />
							</td>
						</tr>
				</jstl:forEach>
			</table>
		</td>
	</tr>
	<tr>
		<td><spring:message code="curricula.personalRecord"/></td>
		<td>
			<table class="table">
				<tr>
					<td><spring:message code="curricula.fullName"/></td>
					<td>${curricula.personalRecord.fullName}</td>
				</tr>
				<tr>
					<td><spring:message code="curricula.picture"/></td>
					<td><a href="${curricula.personalRecord.picture}" target="_blank"><img src="${curricula.personalRecord.picture}" style="max-width: 100px; max-height: 100px;"></a></td>
				</tr>
				<tr>
					<td><spring:message code="curricula.email"/></td>
					<td>${curricula.personalRecord.email}</td>
				</tr>
				<tr>
					<td><spring:message code="curricula.phone"/></td>
					<td>${curricula.personalRecord.phone}</td>
				</tr>
				<tr>
					<td><spring:message code="curricula.linkedIn"/></td>
					<td><a target="_blank" href="${curricula.personalRecord.linkedIn}">${curricula.personalRecord.linkedIn}</a></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td><spring:message code="curricula.professionalRecords"/></td>
		<td>
			<table class="table">
				<jstl:forEach items="${curricula.professionalRecords}" var="e">
						<tr>
							<td><spring:message code="curricula.companyName"/></td>
							<td>${e.companyName}</td>
						</tr>
						<tr>
							<td><spring:message code="curricula.initialWork"/></td>
							<td><fmt:formatDate value="${e.initialWork}" pattern="dd/MM/yyyy"/></td>
						</tr>
						<tr>
							<td><spring:message code="curricula.finalWork"/></td>
							<td><fmt:formatDate value="${e.finalWork}" pattern="dd/MM/yyyy"/></td>
						</tr>
						<tr>
							<td><spring:message code="curricula.role"/></td>
							<td>${e.role}</td>
						</tr>
						<tr>
							<td><spring:message code="curricula.attachment"/></td>
							<td><a href="${e.attachment}" target="_blank"><img src="${e.attachment}" style="max-width: 100px; max-height: 100px;"></a></td>
						</tr>
						<tr>
							<td><spring:message code="curricula.comments"/></td>
							<td>
								<table class="table">
									<jstl:forEach items="${e.comments}" var="s">
										<tr>
											<td>${s}</td>
										</tr>
									</jstl:forEach>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<hr />
							</td>
						</tr>
				</jstl:forEach>
			</table>
		</td>
	</tr>
	<tr>
		<td><spring:message code="curricula.miscellaneousRecords"/></td>
		<td>
			<table class="table">
				<jstl:forEach items="${curricula.miscellaneousRecords}" var="e">
						<tr>
							<td><spring:message code="curricula.title"/></td>
							<td>${e.title}</td>
						</tr>
						<tr>
							<td><spring:message code="curricula.attachment"/></td>
							<td><a href="${e.attachment}" target="_blank">${e.attachment}</a></td>
						</tr>
						<tr>
							<td><spring:message code="curricula.comments"/></td>
							<td>
								<table class="table">
									<jstl:forEach items="${e.comments}" var="s">
										<tr>
											<td>${s}</td>
										</tr>
									</jstl:forEach>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<hr />
							</td>
						</tr>
				</jstl:forEach>
			</table>
		</td>
	</tr>
	<tr>
		<td><spring:message code="curricula.endorserRecords"/></td>
		<td>
			<table class="table">
				<jstl:forEach items="${curricula.endorserRecords}" var="e">
						<tr>
							<td><spring:message code="curricula.endorserName"/></td>
							<td>${e.endorserName}</td>
						</tr>
						<tr>
							<td><spring:message code="curricula.endorserEmail"/></td>
							<td>${e.endorserEmail}</td>
						</tr>
						<tr>
							<td><spring:message code="curricula.endorserPhone"/></td>
							<td>${e.endorserPhone}</td>
						</tr>
						<tr>
							<td><spring:message code="curricula.linkedIn"/></td>
							<td><a href="${e.linkedIn}" target="_blank">${e.linkedIn}</a></td>
						</tr>
						<tr>
							<td><spring:message code="curricula.comments"/></td>
							<td>
								<table class="table">
									<jstl:forEach items="${e.comments}" var="s">
										<tr>
											<td>${s}</td>
										</tr>
									</jstl:forEach>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<hr />
							</td>
						</tr>
				</jstl:forEach>
			</table>
		</td>
	</tr>
	
	<tr>
		<td><spring:message code="curricula.notes"/></td>
		<td>
			<table class="table">
				<jstl:forEach items="${curricula.notes}" var="e">
						<tr>
							<td><spring:message code="curricula.createdMoment"/></td>
							<td><fmt:formatDate value="${e.createdMoment}" pattern="dd/MM/yyyy"/></td>
						</tr>
						<tr>
							<td><spring:message code="curricula.remark"/></td>
							<td>${e.remark}</td>
						</tr>
						<tr>
							<td><spring:message code="curricula.reply"/></td>
							<td>${e.reply}</td>
						</tr>
						<tr>
							<td><spring:message code="curricula.replyMoment"/></td>
							<td><fmt:formatDate value="${e.replyMoment}" pattern="dd/MM/yyyy"/></td>
						</tr>
						<tr>
							<td><spring:message code="curricula.reply"/></td>
							<td>${e.reply}</td>
						</tr>
						<tr>
							<td><spring:message code="curricula.status"/></td>
							<td>${e.status.value}</td>
						</tr>
						<tr>
							<td colspan="2">
								<hr />
							</td>
						</tr>
				</jstl:forEach>
			</table>
		</td>
	</tr>
</table>

