<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>

<jsp:include page="common/head.jsp" />
<jsp:include page="common/logo.jsp" />

<body>

	<jsp:include page="common/header.jsp" />

	<section id="content2">
		<article id="center" class="contenido">							
	
				<form action="Login" method="post">
					<h1><s:text name="error.title"/></h1>										
					<div>						
						<s:textfield key="text.login.email" id="loginError" name="email" required="true" tabindex="2" />
					</div>
					<div>
						<s:password key="text.login.password" id="loginError" name="password" required="true" tabindex="3" />
					</div>					
					<div>						
						<s:submit key="text.login.submit" id="submitLogin" name="submit" tabindex="4" />						
					</div>
				</form><!-- form -->				
		</article>
	</section>

	<jsp:include page="common/footer.jsp" />

</body>

</html>