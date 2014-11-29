<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>

<jsp:include page="../common/logo.jsp" />
<jsp:include page="../common/login.jsp" />
<jsp:include page="../common/head.jsp" />

<body>

	<jsp:include page="../common/header.jsp" />
	
	<jsp:include page="../nav/nav.jsp" />

	<section class="principal">
		<article id="left" class="contenido">			
			<div id="steps">
				<h2 id="managerIzq"><s:text name="text.steps"/></h2>					
    				
    				<table id="managerIzq">
    					<tr>
    						<th><s:text name="text.index.left.steps"/></th>
    					</tr>
    					<tr class="desplegable">
    						<td>
  								<ul class="navegador">
  									<li class="ok">
  										 1. <s:text name="text.index.left.1"/>
  									</li>
  									<li class="ok">
  										 2. <s:text name="text.index.left.2"/>
  									</li>
  									<li class="actual">
  										 3. <s:text name="text.index.left.3"/>
  									</li> 
  									<li> 4. <s:a action="RiskSystem" tabindex="5"><s:text name="text.index.left.4"/></s:a></li> 								  									    									
    								<li class="cls"> 4.1 <s:text name="text.index.left.4.1"/></li>
    								<li class="cls"> 4.2 <s:text name="text.index.left.4.2"/></li>
    								<li class="cls"> 4.3 <s:text name="text.index.left.4.3"/></li>
    								<li class="cls"> 4.4 <s:text name="text.index.left.4.4"/></li>
    								<li class="cls"> 4.5 <s:text name="text.index.left.4.5"/></li>      										  												 
								</ul>
							</td>    						
    					</tr>
    				</table>
			</div>
		</article>
		
		<article id="right" class="contenido">
			<div id="registerManager">
				<h2 id="managerDch"><s:text name="text.register.member"/></h2>							
				
				<s:set var="managerIdProject">${sessionScope.usuario.getIdProyecto()}</s:set>						   		   						
	
				<s:form id="registerMember" class="contact_form" action="RegisterMember" method="post">
					<s:textfield id="register" key="text.regiter.project" name="projectRegister" required="true" value="%{#managerIdProject}" tabindex="6" />
					<s:textfield id="register" key="text.register.nombre" name="loginRegister" required="true" tabindex="7" />
					<s:textfield id="register" key="text.regiter.email" name="emailRegister" required="true" tabindex="8" />
					<s:select id="register" key="text.register.language" name="languageRegister" list="{'ES - Castellano', 'EN - English'}" tabindex="9" required="true"/>				
					<s:password id="register" key="text.register.password" name="passwordRegister" required="true" value="cambiame" showPassword="true" tabindex="10" />
					<s:hidden name="adminRegister" value="false" />
					<s:hidden name="managerRegister" value="false" />	
					<s:hidden name="stepProjectRegister" value="3" />				
					<s:submit id="submitRegister" key="text.register.submit" name="submitRegister" tabindex="11" />					
				</s:form>
				
				<s:if test="hasActionMessages()">
				<div class="mensajeNuevoMiembro">
					<s:actionmessage />
				</div>
			</s:if>
			<s:if test="hasActionErrors()">
				<div class="mensajeError">
					<s:actionerror />
				</div>
			</s:if>
			</div>
		</article>
	</section>

	<jsp:include page="../common/footer.jsp" />

</body>

</html>