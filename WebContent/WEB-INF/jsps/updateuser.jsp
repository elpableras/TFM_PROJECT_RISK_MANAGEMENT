<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html lang="es">

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
								
				<s:set var="id">${sessionScope.user.getId()}</s:set>
				<s:set var="login">${sessionScope.user.getLogin()}</s:set>
				<s:set var="email">${sessionScope.user.getEmail()}</s:set>
				<s:set var="idProject">${sessionScope.user.getIdProyecto()}</s:set>				

				<s:form id="update" action="UpdateUser" method="post">
					<s:hidden name="idUserUpdate" value="%{#id}" />
					<s:textfield id="register" key="user.update.login"
						name="loginUserUpdate" required="true" tabindex="6"
						value="%{#login}" />				
					<s:textfield id="register" key="user.update.email"
						name="emailUserUpdate" required="true" tabindex="7"
						value="%{#email}" />
					<s:textfield id="register" key="user.update.idProject"
						name="idProjectUserUpdate" required="true" tabindex="8"
						value="%{#idProject}" />				
					<s:submit id="submitUpdate" key="user.update.submit"
						name="submitUpdate" tabindex="9" />
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
		<s:a id="updateInfo" action="InfoProjects"><s:text name="back"/></s:a>
	</div>

	<jsp:include page="common/footer.jsp" />

</body>

</html>