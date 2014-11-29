<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>

<jsp:include page="common/logo.jsp" />
<jsp:include page="common/login.jsp" />
<jsp:include page="common/head.jsp" />

<body>

	<jsp:include page="common/header.jsp" />

	<jsp:include page="nav/nav_info_project.jsp" />

	<section class="principal">
		<article id="centerAccount">
			<div id="account">
				<h2 id="account">
					<s:text name="userUpdate" />
				</h2>			
				
				<s:set var="id">${sessionScope.project.getId()}</s:set>
				<s:set var="nombre">${sessionScope.project.getNombre()}</s:set>
				<s:set var="manager">${sessionScope.project.getManager()}</s:set>
				<s:set var="email">${sessionScope.project.getEmail()}</s:set>
				<s:set var="fecha">${sessionScope.project.getFecha()}</s:set>				
				<s:set var="paso">${sessionScope.project.getPaso()}</s:set>					

				<s:form id="update" action="UpdateProject" method="post">
					<s:hidden name="idProjectUpdate" value="%{#id}" />
					<s:textfield id="register" key="project.update.nombre"
						name="nombreProjectUpdate" required="true" tabindex="6"
						value="%{#nombre}" />
					<s:textfield id="register" key="project.update.manager"
						name="managerProjectUpdate" required="true" tabindex="7"
						value="%{#manager}" />
					<s:textfield id="register" key="project.update.email"
						name="emailProjectUpdate" required="true" tabindex="8"
						value="%{#email}" />
					<s:textfield id="register" key="project.update.fecha"
						name="fechaProjectUpdate" required="true" tabindex="9"
						value="%{#fecha}" />					
					<s:textfield id="register" key="project.update.paso"
						name="pasoProjectUpdate" required="true" tabindex="11"
						value="%{#paso}" />
					<s:submit id="submitUpdate" key="project.update.submit"
						name="submitUpdate" tabindex="12" />
				</s:form>
			</div>

			<s:if test="hasActionMessages()">
				<div class="mensajeUpdateOptions">
					<s:actionmessage />
				</div>
			</s:if>
			<s:if test="hasActionErrors()">
				<div class="mensajeNoUpdateOptions">
					<s:actionerror />
				</div>
			</s:if>
		</article>

	</section>
	
	<div id="back">
		<s:a id="update" action="InfoProjects"><s:text name="back"/></s:a>
	</div>

	<jsp:include page="common/footer.jsp" />

</body>

</html>