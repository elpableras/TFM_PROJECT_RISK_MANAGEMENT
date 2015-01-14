<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html lang="es">

<jsp:include page="../common/logo.jsp" />
<jsp:include page="../common/login.jsp" />
<jsp:include page="../common/head.jsp" />

<body>

	<jsp:include page="../common/header.jsp" />

	<jsp:include page="../nav/nav.jsp" />

	<section class="principal">
		<article id="left" class="contenido">
			<div id="steps">
				<h2 id="adminIzq">
					<s:text name="text.steps" />
				</h2>

				<table id="adminIzq">
					<tr>
						<th><s:text name="text.index.left.steps" /></th>
					</tr>
					<tr class="desplegable">
						<td>
							<ul class="navegador">
								<li class="actual">1. <s:text name="text.index.left.1" />
								</li>
								<li class="steps">2. <s:text name="text.index.left.2" />
								</li>
								<li class="steps">3. <s:text name="text.index.left.3" />
								</li>
								<li>4. <s:text name="text.index.left.4" />
									<ul class="subnavegador">
										<li class="steps">4.1 <s:text name="text.index.left.4.1" /></li>
										<li class="steps">4.2 <s:text name="text.index.left.4.3" /></li>
										<li class="steps">4.3 <s:text name="text.index.left.4.4" /></li>
										<li class="steps">4.4 <s:text name="text.index.left.4.5" /></li>
									</ul>
								</li>
							</ul>
						</td>
					</tr>
				</table>
			</div>
		</article>

		<article id="right" class="contenido">
			<div id="registerManager">
				<h2 id="adminDch">
					<s:text name="text.register" />
				</h2>

				<s:form id="registerManager" class="contact_form" action="Register"
					method="post">
					<s:textfield id="register" key="text.register.manager"
						name="loginRegister" required="true" tabindex="6" />
					<s:textfield id="register" key="text.regiter.email"
						name="emailRegister" required="true" tabindex="7" />
					<s:select id="register" key="text.register.language"
						name="languageRegister" list="{'ES - Castellano', 'EN - English'}"
						tabindex="8" required="true" />
					<s:password id="register" key="text.register.password"
						name="passwordRegister" required="true" value="cambiame"
						showPassword="true" tabindex="9" />
					<s:hidden name="adminRegister" value="false" />
					<s:hidden name="managerRegister" value="true" />
					<s:submit id="submitRegister" key="text.register.submit"
						name="submitRegister" tabindex="10" />
				</s:form>

				<s:if test="hasActionErrors()">
					<div class="mensajeErrorRegister">
						<s:actionerror />
					</div>
				</s:if>
			</div>
		</article>
	</section>

	<jsp:include page="../common/footer.jsp" />

</body>

</html>