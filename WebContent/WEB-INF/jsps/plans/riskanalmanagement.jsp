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
		<s:set var="play">${sessionScope.play}</s:set>   																		  											  								  																														
  		<s:set var="saveplan">${sessionScope.saveplan}</s:set>  		
		<article id="left" class="contenido">			
			<div id="steps">
				<h2 id="memberIzq"><s:text name="text.steps"/></h2>					
    				
    				<table id="memberIzq">
    					<tr>
    						<th><s:text name="text.member.right"/></th>
    					</tr>
    					<tr>
    						<td>
  								<ul>   									
									<s:if test='%{#play == 2 && #saveplan == 12}'>
										<li class="ok"> 1 <s:a action="RiskPlan" tabindex="5"><s:text name="text.index.left.4.1"/></s:a></li>										
										<li class="actual"> 2 <s:a action="RiskIden" tabindex="6"><s:text name="text.index.left.4.3"/></s:a></li>
										<li class="steps"> 3 <s:text name="text.index.left.4.4"/></li>
										<li class="steps"> 4 <s:a action="LoadPDF" tabindex="7"><s:text name="text.index.left.4.5"/></s:a></li>																			
									</s:if>									
									<s:elseif test='%{#play == 3}'>
										<li class="ok"> 1 <s:a action="RiskPlan" tabindex="5"><s:text name="text.index.left.4.1"/></s:a></li>										
										<li class="ok"> 2 <s:a action="RiskIden" tabindex="6"><s:text name="text.index.left.4.3"/></s:a></li>
										<li class="actual"> 3 <s:text name="text.index.left.4.4"/></li>
										<li class="steps"> 4 <s:a action="LoadPDF" tabindex="7"><s:text name="text.index.left.4.5"/></s:a></li>
									</s:elseif>
									<s:elseif test='%{#play == 4}'>
										<li class="ok"> 1 <s:a action="RiskPlan" tabindex="5"><s:text name="text.index.left.4.1"/></s:a></li>								
										<li class="ok"> 2 <s:a action="RiskIden" tabindex="6"><s:text name="text.index.left.4.3"/></s:a></li>
										<li class="ok"> 3 <s:a action="RiskAnalManagement" tabindex="7"><s:text name="text.index.left.4.4"/></s:a></li>
										<li class="actual"> 4 <s:text name="text.index.left.4.5"/></li>
									</s:elseif>
									<s:elseif test='%{#play == 5}'>
										<li class="ok"> 1 <s:a action="RiskPlan" tabindex="5"><s:text name="text.index.left.4.1"/></s:a></li>										
										<li class="ok"> 2 <s:a action="RiskIden" tabindex="6"><s:text name="text.index.left.4.3"/></s:a></li>
										<li class="ok"> 3 <s:a action="RiskAnalManagement" tabindex="7"><s:text name="text.index.left.4.4"/></s:a></li>
										<li class="steps"> 4 <s:a action="LoadPDF" tabindex="8"><s:text name="text.index.left.4.5"/></s:a></li>
									</s:elseif>
									<s:else>																				
										<li class="actual"> 1 <s:a action="RiskPlan" tabindex="5"><s:text name="text.index.left.4.1"/></s:a></li>										
										<li class="steps"> 2 <s:a action="RiskIden" tabindex="6"><s:text name="text.index.left.4.3"/></s:a></li>
										<li class="steps"> 3 <s:a action="RiskAnalManagement" tabindex="7"><s:text name="text.index.left.4.4"/></s:a></li>
										<li class="steps"> 4 <s:a action="LoadPDF" tabindex="8"><s:text name="text.index.left.4.5"/></s:a></li>
									</s:else>      										  												 
								</ul>
							</td>    						
    					</tr>
    				</table>
			</div>
		</article>
		
		<article id="rightMember" class="contenido">
			<div id="member">
				<h2 id="riskM"><s:text name="text.anali"/></h2>								
								
    				<table id="riskM">
    					<tr>
    						<th><s:text name="text.anali.right"/> ${sessionScope.corte}</th>
    					</tr>
    					<tr>
    						<td>
    							<ul class="navegador">
    								<s:set var="numRisk">${0}</s:set>
    								
    								<s:iterator value="%{#application.study}">    									 
				       					<li class="steps" id="analiRisk"><s:a action="RiskAnal" tabindex="7"><s:property value="nombre"/><s:param name="option" value="%{#numRisk}" /></s:a></li>
				       					<s:set var="numRisk">${numRisk+1}</s:set>
				       				</s:iterator>    									    							  									 					 															  																	
								</ul>
							</td>    						
    					</tr>
    				</table>
			</div>
			<s:if test="hasActionMessages()">
				<div class="mensajePlan">
					<s:actionmessage />
				</div>
			</s:if>
			<s:if test="hasActionErrors()">
				<div class="mensajeNoPlan">
					<s:actionerror />
				</div>
			</s:if>
		</article>
	</section>	

	<jsp:include page="../common/footer.jsp" />

</body>

</html>