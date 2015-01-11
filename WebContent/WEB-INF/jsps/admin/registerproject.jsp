<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
				<h2 id="adminIzq"><s:text name="text.steps"/></h2>					
    				
    				<table id="adminIzq">
    					<tr>
    						<th><s:text name="text.index.left.steps"/></th>
    					</tr>
    					<tr class="desplegable">
    						<td>
  								<ul class="navegador">
  									<li class="ok">
  										1. <s:text name="text.index.left.1"/>
  									</li>
  									<li class="actual">
  										2. <s:text name="text.index.left.2"/>
  									</li>
  									<li class="steps">
  										3. <s:text name="text.index.left.3"/>
  									</li>  								
  									<li> 4. <s:text name="text.index.left.4"/>
    									<ul class="subnavegador">
      										<li class="steps"> 4.1 <s:text name="text.index.left.4.1"/></li>      						
      										<li class="steps"> 4.2 <s:text name="text.index.left.4.3"/></li>
      										<li class="steps"> 4.3 <s:text name="text.index.left.4.4"/></li>
      										<li class="steps"> 4.4 <s:text name="text.index.left.4.5"/></li>      										
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
				<h2 id="adminDch"><s:text name="text.project"/></h2>
				
				<jsp:useBean id="date" class="java.util.Date" />				
				<fmt:formatDate value="${date}" pattern="dd/MM/yyyy" var="date" />																																					
										
				<s:set var="date">${date}</s:set>
				
				<s:set var="managerName">${sessionScope.managerRegistrado.getLogin()}</s:set>
				<s:set var="managerEmail">${sessionScope.managerRegistrado.getEmail()}</s:set>						   		   						
	
				<s:form id="registerProject" class="contact_form" action="CreationProject" method="post">
					<s:textfield id="register" key="text.project.name" name="nameProjectRegister" required="true" tabindex="6" />
					<s:textfield id="register" key="text.project.date" name="dateProjectRegister" required="true" tabindex="7" value="%{#date}" />
					<s:textfield id="register" key="text.project.manager" name="managerProjectRegister" required="true" tabindex="8" value="%{#managerName}"/>
					<s:textfield id="register" key="text.project.email" name="emailProjectRegister" required="true" tabindex="9" value="%{#managerEmail}"/>					
					<s:hidden name="stepProjectRegister" value="2" />			
					<s:submit id="submitRegister" key="text.project.submit" name="submitRegister" tabindex="11" />					
				</s:form>
			</div>
			<s:if test="hasActionMessages()">
				<div class="mensajeProject">
					<s:actionmessage />
				</div>
			</s:if>
			<s:if test="hasActionErrors()">
				<div class="mensajeErrorProject">
					<s:actionerror />
				</div>
			</s:if>
			<s:a id="newmanager" action="Interceptor"><s:text name="new.manager"/></s:a>
		</article>		
	</section>	

	<jsp:include page="../common/footer.jsp" />

</body>

</html>