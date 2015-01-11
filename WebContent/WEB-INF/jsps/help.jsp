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
	<jsp:include page="nav/nav_help_project.jsp" />

	<section class="principal">
		<article id="centerHelp" class="contenido">			
			<div id="help">			    				
    				<s:set var="usuario">${sessionScope.usuario}</s:set>
					<s:if test="%{#admin}">
	    				<table id="help">
	    					<tr>
	    						<th><s:text name="text.help.admin"/></th>
	    					</tr>
	    					<tr>
	    						<td>
	  								<ul>
	  									<li class="steps">
	  										 1. <s:text name="text.help.admin.1"/>
	  									</li>
	  									<li class="steps">
	  										 2. <s:text name="text.help.admin.2"/>
	  									</li>
	  									<li class="steps">
	  										 3. <s:text name="text.help.admin.3"/>
	  									</li>  									  										  
									</ul>
								</td>    						
	    					</tr>
	    				</table>
	    			</s:if>
				<s:elseif test="%{#manager}">
					<table id="help">
	    					<tr>
	    						<th><s:text name="text.help.manager"/></th>
	    					</tr>
	    					<tr>
	    						<td>
	  								<ul>
	  									<li class="steps">
	  										 1. <s:text name="text.help.manager.1"/>
	  									</li>
	  									<li>
		  									<ul class="enlace">
			  									<li class="steps">
			  										 1. <s:text name="text.help.manager.2"/>
			  									</li>
			  									<li class="steps">
			  										 2. <s:text name="text.help.manager.3"/>
			  									</li>
		  									</ul>
	  									</li>
	  									<li class="steps">
	  										 2. <s:text name="text.help.manager.4"/>
	  									</li>
	  									<li class="steps">
	  										 3. <s:text name="text.help.manager.5"/>
	  									</li>
	  									<li>
		  									<ul class="enlace">
			  									<li class="steps">
			  										 1. <s:text name="text.help.member.2"/>
			  									</li>
			  									<li class="steps">
			  										 2. <s:text name="text.help.member.3"/>
			  									</li>
			  									<li class="steps">
			  										 3. <s:text name="text.help.member.4"/>
			  									</li>
			  									<li class="steps">
			  										 4. <s:text name="text.help.member.5"/>
			  									</li>
		  									</ul>
	  									</li>
	  									<li class="steps">
	  										 4. <s:text name="text.help.member.6"/>
	  									</li>	
	  									<li>
		  									<ul class="enlace">
			  									<li class="steps">
			  										 1. <s:text name="text.help.member.7"/>
			  									</li>
			  									<li class="steps">
			  										 2. <s:text name="text.help.member.8"/>
			  									</li>
			  									<li class="steps">
			  										 3. <s:text name="text.help.member.9"/>
			  									</li>
			  									<li class="steps">
			  										 4. <s:text name="text.help.member.10"/>
			  									</li>
			  									<li class="steps">
			  										 5. <s:text name="text.help.member.11"/>
			  									</li>
			  									<li class="steps">
			  										 6. <s:text name="text.help.member.12"/>
			  									</li>
			  									<li class="steps">
			  										 7. <s:text name="text.help.member.13"/>
			  									</li>
			  									<li class="steps">
			  										 8. <s:text name="text.help.member.14"/>
			  									</li>
			  									<li class="steps">
			  										 9. <s:text name="text.help.member.15"/>
			  									</li>
			  									<li class="steps">
			  										 10. <s:text name="text.help.member.16"/>
			  									</li>
			  									<li class="steps">
			  										 11. <s:text name="text.help.member.17"/>
			  									</li>			  									
		  									</ul>		  									
	  									</li>
	  									<li class="steps">
	  										 5. <s:text name="text.help.member.18"/>
	  									</li>
	  									<li class="steps">
	  										 6. <s:text name="text.help.member.19"/>
	  									</li>
	  									<li class="steps">
	  										 7. <s:text name="text.help.member.20"/>
	  									</li>
	  									<li class="steps">
	  										 8. <s:text name="text.help.member.21"/>
	  									</li>								  										  
									</ul>
								</td>    						
	    					</tr>
	    				</table>
				</s:elseif>
				<s:else>
					<table id="help">
	    					<tr>
	    						<th><s:text name="text.help.member"/></th>
	    					</tr>
	    					<tr>
	    						<td>
	  								<ul>
	  									<li class="steps">
	  										 1. <s:text name="text.help.member.1"/>
	  									</li>
	  									<li>
		  									<ul class="enlace">
			  									<li class="steps">
			  										 1. <s:text name="text.help.member.2"/>
			  									</li>
			  									<li class="steps">
			  										 2. <s:text name="text.help.member.3"/>
			  									</li>
			  									<li class="steps">
			  										 3. <s:text name="text.help.member.4"/>
			  									</li>
			  									<li class="steps">
			  										 4. <s:text name="text.help.member.5"/>
			  									</li>
		  									</ul>
	  									</li>
	  									<li class="steps">
	  										 2. <s:text name="text.help.member.6"/>
	  									</li>	
	  									<li>
		  									<ul class="enlace">
			  									<li class="steps">
			  										 1. <s:text name="text.help.member.7"/>
			  									</li>
			  									<li class="steps">
			  										 2. <s:text name="text.help.member.8"/>
			  									</li>
			  									<li class="steps">
			  										 3. <s:text name="text.help.member.9"/>
			  									</li>
			  									<li class="steps">
			  										 4. <s:text name="text.help.member.10"/>
			  									</li>
			  									<li class="steps">
			  										 5. <s:text name="text.help.member.11"/>
			  									</li>
			  									<li class="steps">
			  										 6. <s:text name="text.help.member.12"/>
			  									</li>
			  									<li class="steps">
			  										 7. <s:text name="text.help.member.13"/>
			  									</li>
			  									<li class="steps">
			  										 8. <s:text name="text.help.member.14"/>
			  									</li>
			  									<li class="steps">
			  										 9. <s:text name="text.help.member.15"/>
			  									</li>
			  									<li class="steps">
			  										 10. <s:text name="text.help.member.16"/>
			  									</li>
			  									<li class="steps">
			  										 11. <s:text name="text.help.member.17"/>
			  									</li>			  									
		  									</ul>		  									
	  									</li>
	  									<li class="steps">
	  										 3. <s:text name="text.help.member.18"/>
	  									</li>
	  									<li class="steps">
	  										 4. <s:text name="text.help.member.19"/>
	  									</li>
	  									<li class="steps">
	  										 5. <s:text name="text.help.member.20"/>
	  									</li>
	  									<li class="steps">
	  										 6. <s:text name="text.help.member.21"/>
	  									</li>							  										  
									</ul>
								</td>    						
	    					</tr>
	    				</table>
				</s:else>
			</div>
		</article>

	</section>
	
	<div id="backHelp">
		<s:a action="Interceptor"><s:text name="back"/></s:a>
	</div>

	<jsp:include page="common/footer.jsp" />

</body>

</html>