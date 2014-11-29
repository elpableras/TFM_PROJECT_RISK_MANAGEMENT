<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>

<jsp:include page="WEB-INF/jsps/common/logoIndex.jsp" />
<jsp:include page="WEB-INF/jsps/common/head.jsp" />

<body>

	<jsp:include page="WEB-INF/jsps/common/header.jsp" />
	
	<div class="container">
	<section id="content">
	
		<s:if test="hasActionErrors()">
		   		<div class="errors">
		      		<s:actionerror/>
		   		</div>
			</s:if>	
	
		<form action="Login" method="post">
			<h1><s:text name="welcome"/></h1>
			<h2><s:text name="welcome.insert.data"/></h2>
			<div>
				<s:textfield key="text.login.email" id="email" name="email" required="true" tabindex="1" />
			</div>
			<div>
				<s:password key="text.login.password" id="password" name="password" required="true" tabindex="2" />
			</div>
			<div>
				<s:submit key="text.login.submit" id="submitLogin" name="submit" tabindex="3" />
				<s:a id="forgotten" action="Forgotten" tabindex="4"><s:text name="login.forget"/></s:a>
			</div>
		</form><!-- form -->
	</section><!-- content -->
	</div><!-- container -->

	<jsp:include page="WEB-INF/jsps/common/footer.jsp" />

</body>

</html>