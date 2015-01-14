<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.miw.model.*" import="java.util.Vector"%>
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
		<article class="contenido">

			<s:set var="admin">${sessionScope.usuario.isAdmin()}</s:set>
			<s:set var="manager">${sessionScope.usuario.isManager()}</s:set>

			<s:if test="%{#admin!='null' && #admin}">
				<h2 id="info2">
					<s:text name="infoprojects" />
				</h2>

				<div id="info">
					<table>
						<tr id="info">
							<td><s:text name="tabla.infoproject.id" /></td>
							<td><s:text name="tabla.infoproject.name" /></td>
							<td><s:text name="tabla.infoproject.manager" /></td>
							<td><s:text name="tabla.infoproject.email" /></td>
							<td><s:text name="tabla.infoproject.fecha" /></td>
							<td><s:text name="tabla.infoproject.paso" /></td>
							<td><s:text name="tabla.infoproject.creacion" /></td>
							<td><s:text name="tabla.infoproject.modificacion" /></td>
							<td></td>
							<td></td>
						</tr>

						<s:iterator id="infoproject"
							value="%{#application.listinfoproject}">
							<tr>
								<td><s:property value="id" /></td>
								<td><s:property value="nombre" /></td>
								<td><s:property value="manager" /></td>
								<td><s:property value="email" /></td>
								<td><s:property value="fecha" /></td>
								<td><s:property value="paso" /></td>
								<td><s:property value="create" /></td>
								<td><s:property value="modify" /></td>
								<td><s:form id="options" action="OptionProject"
										method="post" theme="simple">
										<s:hidden name="updateId" value="%{id}" />
										<s:hidden name="updateNombre" value="%{nombre}" />
										<s:hidden name="updateManager" value="%{manager}" />
										<s:hidden name="updateEmail" value="%{email}" />
										<s:hidden name="updateFecha" value="%{fecha}" />
										<s:hidden name="updatePaso" value="%{paso}" />
										<s:submit id="submitOptions" key="tabla.infoproject.update"
											name="submitOptions" tabindex="6" />
									</s:form></td>
								<td><s:form id="options" action="OptionProject"
										method="post" theme="simple">
										<s:hidden name="delete" value="%{id}" />
										<s:submit id="submitOptions" key="tabla.infoproject.delete"
											name="submitOptions" tabindex="6" />
									</s:form></td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</s:if>
			<s:elseif test="%{#manager!='null' && #manager}">
				<h2 id="info2">
					<s:text name="infoproject" />
				</h2>

				<div id="info">
					<table>
						<tr id="info">
							<td><s:text name="tabla.infoproject.id" /></td>
							<td><s:text name="tabla.infoproject.name" /></td>
							<td><s:text name="tabla.infoproject.manager" /></td>
							<td><s:text name="tabla.infoproject.email" /></td>
							<td><s:text name="tabla.infoproject.fecha" /></td>
							<td><s:text name="tabla.infoproject.paso" /></td>
							<td><s:text name="tabla.infoproject.creacion" /></td>
							<td><s:text name="tabla.infoproject.modificacion" /></td>
						</tr>

						<s:iterator id="infoproject"
							value="%{#application.listinfoprojectmanager}">
							<tr>
								<td><s:property value="id" /></td>
								<td><s:property value="nombre" /></td>
								<td><s:property value="manager" /></td>
								<td><s:property value="email" /></td>
								<td><s:property value="fecha" /></td>
								<td><s:property value="paso" /></td>
								<td><s:property value="create" /></td>
								<td><s:property value="modify" /></td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</s:elseif>


			<s:if test="%{#admin!='null' && #admin}">
				<h2 id="info2">
					<s:text name="infomanager" />
				</h2>
				<div id="info">
					<table>
						<tr id="info">
							<td><s:text name="tabla.infomanager.id" /></td>
							<td><s:text name="tabla.infomanager.login" /></td>
							<td><s:text name="tabla.infomanager.email" /></td>
							<td><s:text name="tabla.infomanager.project" /></td>
							<td><s:text name="tabla.infoproject.creacion" /></td>
							<td><s:text name="tabla.infoproject.modificacion" /></td>
							<td></td>
							<td></td>
						</tr>
						<s:iterator id="infomanager"
							value="%{#application.listinfomanager}">
							<tr>
								<td><s:property value="id" /></td>
								<td><s:property value="login" /></td>
								<td><s:property value="email" /></td>
								<td><s:property value="idProyecto" /></td>
								<td><s:property value="create" /></td>
								<td><s:property value="modify" /></td>
								<td><s:form id="options" action="OptionManager"
										method="post" theme="simple">
										<s:hidden name="updateId" value="%{id}" />
										<s:hidden name="updateLogin" value="%{login}" />
										<s:hidden name="updateEmail" value="%{email}" />
										<s:hidden name="updateIdProject" value="%{idProyecto}" />
										<s:submit id="submitOptions" key="tabla.infoproject.update"
											name="submitOptions" tabindex="7" />
									</s:form></td>
								<td><s:form id="options" action="OptionManager"
										method="post" theme="simple">
										<s:hidden name="delete" value="%{id}" />
										<s:submit id="submitOptions" key="tabla.infoproject.delete"
											name="submitOptions" tabindex="7" />
									</s:form></td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</s:if>
			<s:elseif test="%{#manager!='null' && #manager}">
				<h2 id="info2">
					<s:text name="infomembers" />
				</h2>
				<div id="info">
					<table>
						<tr id="info">
							<td><s:text name="tabla.infomanager.id" /></td>
							<td><s:text name="tabla.infomanager.login" /></td>
							<td><s:text name="tabla.infomanager.email" /></td>
							<td><s:text name="tabla.infomanager.project" /></td>
							<td><s:text name="tabla.infoproject.creacion" /></td>
							<td><s:text name="tabla.infoproject.modificacion" /></td>
						</tr>
						<s:iterator id="infomanager"
							value="%{#application.listinfomembers}">
							<tr>
								<td><s:property value="id" /></td>
								<td><s:property value="login" /></td>
								<td><s:property value="email" /></td>
								<td><s:property value="idProyecto" /></td>
								<td><s:property value="create" /></td>
								<td><s:property value="modify" /></td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</s:elseif>

			<s:if test="hasActionMessages()">
				<div class="mensajeInfo">
					<s:actionmessage />
				</div>
			</s:if>
			<s:if test="hasActionErrors()">
				<div class="mensajeNoInfo">
					<s:actionerror />
				</div>
			</s:if>
			<s:if test="%{#admin!='null' && #admin}">
				<div id="info">
					<s:form id="newManager" action="SeekManagerFree" method="post">
						<s:select id="register" name="addedManager"
							key="text.infomanager.label"
							list="%{#application.listinfomanager}" listKey="email"
							listValue="email" tabindex="8" />
						<s:submit id="submitRegister" key="text.infomanager.submit"
							name="submit" tabindex="9" />
					</s:form>
				</div>
			</s:if>

			<div id="backInfo">
				<s:a action="Interceptor">
					<s:text name="back" />
				</s:a>
			</div>

		</article>

	</section>

	<jsp:include page="common/footer.jsp" />

</body>

</html>