<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html lang="es">

<jsp:include page="common/logo.jsp" />
<jsp:include page="common/head.jsp" />

<body>

	<jsp:include page="common/header.jsp" />

	<div class="container">
		<section id="content2">
			<article id="forgotten" class="contenido">

				<s:if test="hasActionErrors()">
					<div class="errors">
						<s:actionerror />
					</div>
				</s:if>

				<s:if test="hasActionMessages()">
					<div class="sendEmail">
						<s:actionmessage />
					</div>
				</s:if>


				<form action="PassForgotten" method="post">
					<h1>
						<s:text name="forgotten.title" />
					</h1>
					<h2>
						<s:text name="forgotten" />
					</h2>
					<div>
						<s:textfield key="text.forgotten.email" id="emailForgotten"
							name="email" required="true" tabindex="2" />
					</div>
					<div>
						<s:submit key="text.forgotten.submit" id="submitForgotten"
							name="submit" tabindex="3" />
					</div>
				</form>


			</article>
		</section>
	</div>

	<jsp:include page="common/footer.jsp" />

</body>

</html>