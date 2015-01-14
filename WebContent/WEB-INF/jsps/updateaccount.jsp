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

	<jsp:include page="nav/nav_account_project.jsp" />

	<section class="principal">
		<article id="centerAccount">
			<div id="account">
				<h2 id="account">
					<s:text name="update.account" />
				</h2>

				<s:set var="id">${sessionScope.usuario.getId()}</s:set>
				<s:set var="login">${sessionScope.usuario.getLogin()}</s:set>
				<s:set var="email">${sessionScope.usuario.getEmail()}</s:set>
				<s:set var="language">${sessionScope.usuario.getLanguage()}</s:set>
				<s:set var="idProyecto">${sessionScope.usuario.getIdProyecto()}</s:set>
				<s:set var="admin">${sessionScope.usuario.isAdmin()}</s:set>
				<s:set var="manager">${sessionScope.usuario.isManager()}</s:set>

				<s:if test="%{#language=='EN'}">
					<s:set var="language">EN - English</s:set>
				</s:if>
				<s:else>
					<s:set var="language">ES - Castellano</s:set>
				</s:else>

				<s:form id="accountUpdate" action="UpdateAccount" method="post">
					<s:hidden name="idAccountUpdate" value="%{#id}" />
					<s:textfield id="updateLogin" key="update.account.login"
						name="loginAccountUpdate" required="true" tabindex="6"
						value="%{#login}" />
					<s:textfield id="updateEmail" key="update.account.email"
						name="emailAccountUpdate" required="true" tabindex="7"
						value="%{#email}" />
					<s:password id="updatePass" key="update.account.pass"
						name="passAccountUpdate" required="true" tabindex="8" value="" />
					<s:select id="updateLanguage" key="update.account.language"
						name="languageAccountUpdate"
						list="{'ES - Castellano', 'EN - English'}" value="%{#language}"
						tabindex="9" required="true" />
					<s:hidden name="idProyectoAccountUpdate" value="%{#idProyecto}" />
					<s:hidden name="adminAccountUpdate" value="%{#admin}" />
					<s:hidden name="managerAccountUpdate" value="%{#manager}" />
					<s:submit id="submitUpdate" key="update.account.submit"
						name="submitUpdate" tabindex="10" />
				</s:form>
			</div>

			<s:if test="hasActionMessages()">
				<div class="mensajeUpdate">
					<s:actionmessage />
				</div>
			</s:if>
			<s:if test="hasActionErrors()">
				<div class="mensajeNoUpdate">
					<s:actionerror />
				</div>
			</s:if>
		</article>

	</section>

	<div id="back">
		<s:a id="update" action="Interceptor">
			<s:text name="back" />
		</s:a>
	</div>

	<jsp:include page="common/footer.jsp" />

</body>

</html>