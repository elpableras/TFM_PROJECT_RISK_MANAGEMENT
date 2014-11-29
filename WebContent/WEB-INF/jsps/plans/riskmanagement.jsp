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
										<li class="ok"> 1 <s:a action="RiskSystem" tabindex="5"><s:text name="text.index.left.4.1"/></s:a></li>										
										<li class="actual"> 2 <s:a action="RiskIden" tabindex="6"><s:text name="text.index.left.4.3"/></s:a></li>
										<li class="steps"> 3 <s:a action="RiskAnalManagement" tabindex="7"><s:text name="text.index.left.4.4"/></s:a></li>
										<li class="steps"> 4 <s:a action="LoadPDF" tabindex="8"><s:text name="text.index.left.4.5"/></s:a></li>																			
									</s:if>									
									<s:elseif test='%{#play == 3}'>
										<li class="ok"> 1 <s:a action="RiskSystem" tabindex="5"><s:text name="text.index.left.4.1"/></s:a></li>										
										<li class="ok"> 2 <s:a action="RiskIden" tabindex="6"><s:text name="text.index.left.4.3"/></s:a></li>
										<li class="actual"> 3 <s:text name="text.index.left.4.4"/></li>
										<li class="steps"> 4 <s:a action="LoadPDF" tabindex="7"><s:text name="text.index.left.4.5"/></s:a></li>
									</s:elseif>
									<s:elseif test='%{#play == 4}'>
										<li class="ok"> 1 <s:a action="RiskSystem" tabindex="5"><s:text name="text.index.left.4.1"/></s:a></li>										
										<li class="ok"> 2 <s:a action="RiskIden" tabindex="6"><s:text name="text.index.left.4.3"/></s:a></li>
										<li class="ok"> 3 <s:a action="RiskAnalManagement" tabindex="7"><s:text name="text.index.left.4.4"/></s:a></li>
										<li class="actual"> 4 <s:text name="text.index.left.4.5"/></li>
									</s:elseif>
									<s:elseif test='%{#play == 5}'>
										<li class="ok"> 1 <s:a action="RiskSystem" tabindex="5"><s:text name="text.index.left.4.1"/></s:a></li>										
										<li class="ok"> 2 <s:a action="RiskIden" tabindex="6"><s:text name="text.index.left.4.3"/></s:a></li>
										<li class="ok"> 3 <s:a action="RiskAnalManagement" tabindex="7"><s:text name="text.index.left.4.4"/></s:a></li>
										<li class="ok"> 4 <s:a action="LoadPDF" tabindex="8"><s:text name="text.index.left.4.5"/></s:a></li>
									</s:elseif>
									<s:else>																				
										<li class="actual"> 1 <s:text name="text.index.left.4.1"/></li>										
										<li class="steps"> 2 <s:a action="RiskIden" tabindex="5"><s:text name="text.index.left.4.3"/></s:a></li>
										<li class="steps"> 3 <s:a action="RiskAnalManagement" tabindex="6"><s:text name="text.index.left.4.4"/></s:a></li>
										<li class="steps"> 4 <s:a action="LoadPDF" tabindex="7"><s:text name="text.index.left.4.5"/></s:a></li>
									</s:else>																		     										  												
								</ul>
							</td>    													
    					</tr>
    				</table>
			</div>
		</article>
		
		<article id="rightMember" class="contenido">
		
			<s:if test='%{#play == 3}'>
				<div id="member">
					<h2 id="riskM"><s:text name="text.anali"/></h2>								
									
	   				<table id="riskM">
	   					<tr>
	   						<th><s:text name="text.anali.right"/> ${applicationScope.corte}</th>
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
				</s:if>
		
				<s:else>
				<div id="member">
					<h2 id="riskM"><s:text name="text.member.right"/></h2>
									
	    				<table id="riskM">
	    					<tr>
	    						<th><s:text name="text.member.right.steps"/></th>
	    					</tr>
	    					<tr>
	    						<td>
	    							<ul class="navegador">    								
		    							<li class="steps">  										
			  								 1. <s:a action="RiskManagement" tabindex="7"><s:text name="text.member.right.1"/><s:param name="option" value="1" /></s:a>  																					   									
			  							</li> 	
			  							<s:if test='%{#saveplan == 2}'>
	  										<li class="ok">
	  										 	2. <s:a action="RiskManagement" tabindex="8"><s:text name="text.member.right.2"/><s:param name="option" value="2" /></s:a>  																					
		  									</li>
		  									<li class="actual">
		  										 3. <s:a action="RiskManagement" tabindex="9"><s:text name="text.member.right.3"/><s:param name="option" value="3" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 4. <s:a action="RiskManagement" tabindex="10"><s:text name="text.member.right.4"/><s:param name="option" value="4" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 5. <s:a action="RiskManagement" tabindex="11"><s:text name="text.member.right.5"/><s:param name="option" value="5" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 6. <s:a action="RiskManagement" tabindex="12"><s:text name="text.member.right.6"/><s:param name="option" value="6" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 7. <s:a action="RiskManagement" tabindex="13"><s:text name="text.member.right.7"/><s:param name="option" value="7" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 8. <s:a action="RiskManagement" tabindex="14"><s:text name="text.member.right.8"/><s:param name="option" value="8" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 9. <s:a action="RiskManagement" tabindex="15"><s:text name="text.member.right.9"/><s:param name="option" value="9" /></s:a>
		  									</li>  									
		  									<li class="steps">
		  										 10. <s:a action="RiskManagement" tabindex="16"><s:text name="text.member.right.10"/><s:param name="option" value="10" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 11. <s:a action="RiskManagement" tabindex="17"><s:text name="text.member.right.11"/><s:param name="option" value="11" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 12. <s:a action="RiskManagement" tabindex="18"><s:text name="text.member.right.12"/><s:param name="option" value="12" /></s:a>
		  									</li>
	  									</s:if>	  							
	  									<s:elseif test='%{#saveplan == 3}'>
	  										<li class="ok">
	  										 	2. <s:a action="RiskManagement" tabindex="8"><s:text name="text.member.right.2"/><s:param name="option" value="2" /></s:a>  																					
		  									</li>
		  									<li class="ok">
		  										 3. <s:a action="RiskManagement" tabindex="9"><s:text name="text.member.right.3"/><s:param name="option" value="3" /></s:a>
		  									</li>
		  									<li class="actual">
		  										 4. <s:a action="RiskManagement" tabindex="10"><s:text name="text.member.right.4"/><s:param name="option" value="4" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 5. <s:a action="RiskManagement" tabindex="11"><s:text name="text.member.right.5"/><s:param name="option" value="5" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 6. <s:a action="RiskManagement" tabindex="12"><s:text name="text.member.right.6"/><s:param name="option" value="6" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 7. <s:a action="RiskManagement" tabindex="13"><s:text name="text.member.right.7"/><s:param name="option" value="7" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 8. <s:a action="RiskManagement" tabindex="14"><s:text name="text.member.right.8"/><s:param name="option" value="8" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 9. <s:a action="RiskManagement" tabindex="15"><s:text name="text.member.right.9"/><s:param name="option" value="9" /></s:a>
		  									</li>  									
		  									<li class="steps">
		  										 10. <s:a action="RiskManagement" tabindex="16"><s:text name="text.member.right.10"/><s:param name="option" value="10" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 11. <s:a action="RiskManagement" tabindex="17"><s:text name="text.member.right.11"/><s:param name="option" value="11" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 12. <s:a action="RiskManagement" tabindex="18"><s:text name="text.member.right.12"/><s:param name="option" value="12" /></s:a>
		  									</li>
	  									</s:elseif>
	  									<s:elseif test='%{#saveplan == 4}'>
		  									<li class="ok">
	  										 	2. <s:a action="RiskManagement" tabindex="8"><s:text name="text.member.right.2"/><s:param name="option" value="2" /></s:a>  																					
		  									</li>
		  									<li class="ok">
		  										 3. <s:a action="RiskManagement" tabindex="9"><s:text name="text.member.right.3"/><s:param name="option" value="3" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 4. <s:a action="RiskManagement" tabindex="10"><s:text name="text.member.right.4"/><s:param name="option" value="4" /></s:a>
		  									</li>
		  									<li class="actual">
		  										 5. <s:a action="RiskManagement" tabindex="11"><s:text name="text.member.right.5"/><s:param name="option" value="5" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 6. <s:a action="RiskManagement" tabindex="12"><s:text name="text.member.right.6"/><s:param name="option" value="6" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 7. <s:a action="RiskManagement" tabindex="13"><s:text name="text.member.right.7"/><s:param name="option" value="7" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 8. <s:a action="RiskManagement" tabindex="14"><s:text name="text.member.right.8"/><s:param name="option" value="8" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 9. <s:a action="RiskManagement" tabindex="15"><s:text name="text.member.right.9"/><s:param name="option" value="9" /></s:a>
		  									</li>  									
		  									<li class="steps">
		  										 10. <s:a action="RiskManagement" tabindex="16"><s:text name="text.member.right.10"/><s:param name="option" value="10" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 11. <s:a action="RiskManagement" tabindex="17"><s:text name="text.member.right.11"/><s:param name="option" value="11" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 12. <s:a action="RiskManagement" tabindex="18"><s:text name="text.member.right.12"/><s:param name="option" value="12" /></s:a>
		  									</li>
	  									</s:elseif>
	  									<s:elseif test='%{#saveplan == 5}'>
		  									<li class="ok">
	  										 	2. <s:a action="RiskManagement" tabindex="8"><s:text name="text.member.right.2"/><s:param name="option" value="2" /></s:a>  																					
		  									</li>
		  									<li class="ok">
		  										 3. <s:a action="RiskManagement" tabindex="9"><s:text name="text.member.right.3"/><s:param name="option" value="3" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 4. <s:a action="RiskManagement" tabindex="10"><s:text name="text.member.right.4"/><s:param name="option" value="4" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 5. <s:a action="RiskManagement" tabindex="11"><s:text name="text.member.right.5"/><s:param name="option" value="5" /></s:a>
		  									</li>
		  									<li class="actual">
		  										 6. <s:a action="RiskManagement" tabindex="12"><s:text name="text.member.right.6"/><s:param name="option" value="6" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 7. <s:a action="RiskManagement" tabindex="13"><s:text name="text.member.right.7"/><s:param name="option" value="7" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 8. <s:a action="RiskManagement" tabindex="14"><s:text name="text.member.right.8"/><s:param name="option" value="8" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 9. <s:a action="RiskManagement" tabindex="15"><s:text name="text.member.right.9"/><s:param name="option" value="9" /></s:a>
		  									</li>  									
		  									<li class="steps">
		  										 10. <s:a action="RiskManagement" tabindex="16"><s:text name="text.member.right.10"/><s:param name="option" value="10" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 11. <s:a action="RiskManagement" tabindex="17"><s:text name="text.member.right.11"/><s:param name="option" value="11" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 12. <s:a action="RiskManagement" tabindex="18"><s:text name="text.member.right.12"/><s:param name="option" value="12" /></s:a>
		  									</li>
	  									</s:elseif>
	  									<s:elseif test='%{#saveplan == 6}'>
		  									<li class="ok">
	  										 	2. <s:a action="RiskManagement" tabindex="8"><s:text name="text.member.right.2"/><s:param name="option" value="2" /></s:a>  																					
		  									</li>
		  									<li class="ok">
		  										 3. <s:a action="RiskManagement" tabindex="9"><s:text name="text.member.right.3"/><s:param name="option" value="3" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 4. <s:a action="RiskManagement" tabindex="10"><s:text name="text.member.right.4"/><s:param name="option" value="4" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 5. <s:a action="RiskManagement" tabindex="11"><s:text name="text.member.right.5"/><s:param name="option" value="5" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 6. <s:a action="RiskManagement" tabindex="12"><s:text name="text.member.right.6"/><s:param name="option" value="6" /></s:a>
		  									</li>
		  									<li class="actual">
		  										 7. <s:a action="RiskManagement" tabindex="13"><s:text name="text.member.right.7"/><s:param name="option" value="7" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 8. <s:a action="RiskManagement" tabindex="14"><s:text name="text.member.right.8"/><s:param name="option" value="8" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 9. <s:a action="RiskManagement" tabindex="15"><s:text name="text.member.right.9"/><s:param name="option" value="9" /></s:a>
		  									</li>  									
		  									<li class="steps">
		  										 10. <s:a action="RiskManagement" tabindex="16"><s:text name="text.member.right.10"/><s:param name="option" value="10" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 11. <s:a action="RiskManagement" tabindex="17"><s:text name="text.member.right.11"/><s:param name="option" value="11" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 12. <s:a action="RiskManagement" tabindex="18"><s:text name="text.member.right.12"/><s:param name="option" value="12" /></s:a>
		  									</li>
	  									</s:elseif>
	  									<s:elseif test='%{#saveplan == 7}'>
		  									<li class="ok">
	  										 	2. <s:a action="RiskManagement" tabindex="8"><s:text name="text.member.right.2"/><s:param name="option" value="2" /></s:a>  																					
		  									</li>
		  									<li class="ok">
		  										 3. <s:a action="RiskManagement" tabindex="9"><s:text name="text.member.right.3"/><s:param name="option" value="3" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 4. <s:a action="RiskManagement" tabindex="10"><s:text name="text.member.right.4"/><s:param name="option" value="4" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 5. <s:a action="RiskManagement" tabindex="11"><s:text name="text.member.right.5"/><s:param name="option" value="5" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 6. <s:a action="RiskManagement" tabindex="12"><s:text name="text.member.right.6"/><s:param name="option" value="6" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 7. <s:a action="RiskManagement" tabindex="13"><s:text name="text.member.right.7"/><s:param name="option" value="7" /></s:a>
		  									</li>
		  									<li class="actual">
		  										 8. <s:a action="RiskManagement" tabindex="14"><s:text name="text.member.right.8"/><s:param name="option" value="8" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 9. <s:a action="RiskManagement" tabindex="15"><s:text name="text.member.right.9"/><s:param name="option" value="9" /></s:a>
		  									</li>  									
		  									<li class="steps">
		  										 10. <s:a action="RiskManagement" tabindex="16"><s:text name="text.member.right.10"/><s:param name="option" value="10" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 11. <s:a action="RiskManagement" tabindex="17"><s:text name="text.member.right.11"/><s:param name="option" value="11" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 12. <s:a action="RiskManagement" tabindex="18"><s:text name="text.member.right.12"/><s:param name="option" value="12" /></s:a>
		  									</li>
	  									</s:elseif>
	  									<s:elseif test='%{#saveplan == 8}'>
		  									<li class="ok">
	  										 	2. <s:a action="RiskManagement" tabindex="8"><s:text name="text.member.right.2"/><s:param name="option" value="2" /></s:a>  																					
		  									</li>
		  									<li class="ok">
		  										 3. <s:a action="RiskManagement" tabindex="9"><s:text name="text.member.right.3"/><s:param name="option" value="3" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 4. <s:a action="RiskManagement" tabindex="10"><s:text name="text.member.right.4"/><s:param name="option" value="4" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 5. <s:a action="RiskManagement" tabindex="11"><s:text name="text.member.right.5"/><s:param name="option" value="5" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 6. <s:a action="RiskManagement" tabindex="12"><s:text name="text.member.right.6"/><s:param name="option" value="6" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 7. <s:a action="RiskManagement" tabindex="13"><s:text name="text.member.right.7"/><s:param name="option" value="7" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 8. <s:a action="RiskManagement" tabindex="14"><s:text name="text.member.right.8"/><s:param name="option" value="8" /></s:a>
		  									</li>
		  									<li class="actual">
		  										 9. <s:a action="RiskManagement" tabindex="15"><s:text name="text.member.right.9"/><s:param name="option" value="9" /></s:a>
		  									</li>  									
		  									<li class="steps">
		  										 10. <s:a action="RiskManagement" tabindex="16"><s:text name="text.member.right.10"/><s:param name="option" value="10" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 11. <s:a action="RiskManagement" tabindex="17"><s:text name="text.member.right.11"/><s:param name="option" value="11" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 12. <s:a action="RiskManagement" tabindex="18"><s:text name="text.member.right.12"/><s:param name="option" value="12" /></s:a>
		  									</li>
	  									</s:elseif>
	  									<s:elseif test='%{#saveplan == 9}'>
		  									<li class="ok">
	  										 	2. <s:a action="RiskManagement" tabindex="8"><s:text name="text.member.right.2"/><s:param name="option" value="2" /></s:a>  																					
		  									</li>
		  									<li class="ok">
		  										 3. <s:a action="RiskManagement" tabindex="9"><s:text name="text.member.right.3"/><s:param name="option" value="3" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 4. <s:a action="RiskManagement" tabindex="10"><s:text name="text.member.right.4"/><s:param name="option" value="4" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 5. <s:a action="RiskManagement" tabindex="11"><s:text name="text.member.right.5"/><s:param name="option" value="5" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 6. <s:a action="RiskManagement" tabindex="12"><s:text name="text.member.right.6"/><s:param name="option" value="6" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 7. <s:a action="RiskManagement" tabindex="13"><s:text name="text.member.right.7"/><s:param name="option" value="7" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 8. <s:a action="RiskManagement" tabindex="14"><s:text name="text.member.right.8"/><s:param name="option" value="8" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 9. <s:a action="RiskManagement" tabindex="15"><s:text name="text.member.right.9"/><s:param name="option" value="9" /></s:a>
		  									</li>  									
		  									<li class="actual">
		  										 10. <s:a action="RiskManagement" tabindex="16"><s:text name="text.member.right.10"/><s:param name="option" value="10" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 11. <s:a action="RiskManagement" tabindex="17"><s:text name="text.member.right.11"/><s:param name="option" value="11" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 12. <s:a action="RiskManagement" tabindex="18"><s:text name="text.member.right.12"/><s:param name="option" value="12" /></s:a>
		  									</li>
	  									</s:elseif>
	  									<s:elseif test='%{#saveplan == 10}'>
		  									<li class="ok">
	  										 	2. <s:a action="RiskManagement" tabindex="8"><s:text name="text.member.right.2"/><s:param name="option" value="2" /></s:a>  																					
		  									</li>
		  									<li class="ok">
		  										 3. <s:a action="RiskManagement" tabindex="9"><s:text name="text.member.right.3"/><s:param name="option" value="3" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 4. <s:a action="RiskManagement" tabindex="10"><s:text name="text.member.right.4"/><s:param name="option" value="4" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 5. <s:a action="RiskManagement" tabindex="11"><s:text name="text.member.right.5"/><s:param name="option" value="5" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 6. <s:a action="RiskManagement" tabindex="12"><s:text name="text.member.right.6"/><s:param name="option" value="6" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 7. <s:a action="RiskManagement" tabindex="13"><s:text name="text.member.right.7"/><s:param name="option" value="7" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 8. <s:a action="RiskManagement" tabindex="14"><s:text name="text.member.right.8"/><s:param name="option" value="8" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 9. <s:a action="RiskManagement" tabindex="15"><s:text name="text.member.right.9"/><s:param name="option" value="9" /></s:a>
		  									</li>  									
		  									<li class="ok">
		  										 10. <s:a action="RiskManagement" tabindex="16"><s:text name="text.member.right.10"/><s:param name="option" value="10" /></s:a>
		  									</li>
		  									<li class="actual">
		  										 11. <s:a action="RiskManagement" tabindex="17"><s:text name="text.member.right.11"/><s:param name="option" value="11" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 12. <s:a action="RiskManagement" tabindex="18"><s:text name="text.member.right.12"/><s:param name="option" value="12" /></s:a>
		  									</li>
	  									</s:elseif>
	  									<s:elseif test='%{#saveplan == 11}'>
		  									<li class="ok">
	  										 	2. <s:a action="RiskManagement" tabindex="8"><s:text name="text.member.right.2"/><s:param name="option" value="2" /></s:a>  																					
		  									</li>
		  									<li class="ok">
		  										 3. <s:a action="RiskManagement" tabindex="9"><s:text name="text.member.right.3"/><s:param name="option" value="3" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 4. <s:a action="RiskManagement" tabindex="10"><s:text name="text.member.right.4"/><s:param name="option" value="4" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 5. <s:a action="RiskManagement" tabindex="11"><s:text name="text.member.right.5"/><s:param name="option" value="5" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 6. <s:a action="RiskManagement" tabindex="12"><s:text name="text.member.right.6"/><s:param name="option" value="6" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 7. <s:a action="RiskManagement" tabindex="13"><s:text name="text.member.right.7"/><s:param name="option" value="7" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 8. <s:a action="RiskManagement" tabindex="14"><s:text name="text.member.right.8"/><s:param name="option" value="8" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 9. <s:a action="RiskManagement" tabindex="15"><s:text name="text.member.right.9"/><s:param name="option" value="9" /></s:a>
		  									</li>  									
		  									<li class="ok">
		  										 10. <s:a action="RiskManagement" tabindex="16"><s:text name="text.member.right.10"/><s:param name="option" value="10" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 11. <s:a action="RiskManagement" tabindex="17"><s:text name="text.member.right.11"/><s:param name="option" value="11" /></s:a>
		  									</li>
		  									<li class="actual">
		  										 12. <s:a action="RiskManagement" tabindex="18"><s:text name="text.member.right.12"/><s:param name="option" value="12" /></s:a>
		  									</li>
	  									</s:elseif>
	  									<s:elseif test='%{#saveplan == 12}'>
		  									<li class="ok">
	  										 	2. <s:a action="RiskManagement" tabindex="8"><s:text name="text.member.right.2"/><s:param name="option" value="2" /></s:a>  																					
		  									</li>
		  									<li class="ok">
		  										 3. <s:a action="RiskManagement" tabindex="9"><s:text name="text.member.right.3"/><s:param name="option" value="3" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 4. <s:a action="RiskManagement" tabindex="10"><s:text name="text.member.right.4"/><s:param name="option" value="4" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 5. <s:a action="RiskManagement" tabindex="11"><s:text name="text.member.right.5"/><s:param name="option" value="5" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 6. <s:a action="RiskManagement" tabindex="12"><s:text name="text.member.right.6"/><s:param name="option" value="6" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 7. <s:a action="RiskManagement" tabindex="13"><s:text name="text.member.right.7"/><s:param name="option" value="7" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 8. <s:a action="RiskManagement" tabindex="14"><s:text name="text.member.right.8"/><s:param name="option" value="8" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 9. <s:a action="RiskManagement" tabindex="15"><s:text name="text.member.right.9"/><s:param name="option" value="9" /></s:a>
		  									</li>  									
		  									<li class="ok">
		  										 10. <s:a action="RiskManagement" tabindex="16"><s:text name="text.member.right.10"/><s:param name="option" value="10" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 11. <s:a action="RiskManagement" tabindex="17"><s:text name="text.member.right.11"/><s:param name="option" value="11" /></s:a>
		  									</li>
		  									<li class="ok">
		  										 12. <s:a action="RiskManagement" tabindex="18"><s:text name="text.member.right.12"/><s:param name="option" value="12" /></s:a>
		  									</li>		  										  								
	  									</s:elseif>	
	  									<s:else>
	  										<li class="actual">
	  										 	2. <s:a action="RiskManagement" tabindex="8"><s:text name="text.member.right.2"/><s:param name="option" value="2" /></s:a>  																					
		  									</li>
		  									<li class="steps">
		  										 3. <s:a action="RiskManagement" tabindex="9"><s:text name="text.member.right.3"/><s:param name="option" value="3" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 4. <s:a action="RiskManagement" tabindex="10"><s:text name="text.member.right.4"/><s:param name="option" value="4" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 5. <s:a action="RiskManagement" tabindex="11"><s:text name="text.member.right.5"/><s:param name="option" value="5" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 6. <s:a action="RiskManagement" tabindex="12"><s:text name="text.member.right.6"/><s:param name="option" value="6" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 7. <s:a action="RiskManagement" tabindex="13"><s:text name="text.member.right.7"/><s:param name="option" value="7" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 8. <s:a action="RiskManagement" tabindex="14"><s:text name="text.member.right.8"/><s:param name="option" value="8" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 9. <s:a action="RiskManagement" tabindex="15"><s:text name="text.member.right.9"/><s:param name="option" value="9" /></s:a>
		  									</li>  									
		  									<li class="steps">
		  										 10. <s:a action="RiskManagement" tabindex="16"><s:text name="text.member.right.10"/><s:param name="option" value="10" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 11. <s:a action="RiskManagement" tabindex="17"><s:text name="text.member.right.11"/><s:param name="option" value="11" /></s:a>
		  									</li>
		  									<li class="steps">
		  										 12. <s:a action="RiskManagement" tabindex="18"><s:text name="text.member.right.12"/><s:param name="option" value="12" /></s:a>
		  									</li>		  									 
	  									</s:else>									  																	
									</ul>
								</td>    						
	    					</tr>
	    				</table>
				</div>
				</s:else>
			
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