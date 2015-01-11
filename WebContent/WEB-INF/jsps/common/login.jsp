<%@page import="com.sun.org.apache.xalan.internal.xsltc.compiler.sym"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="login">		
	<ul id="login">
		<s:set var="usuario">${sessionScope.usuario}</s:set>
		<s:if test="%{#usuario != 'null'}">
			<s:set var="usuario">${sessionScope.usuario.getLogin()}</s:set>
		</s:if>
		
		<s:if test="%{#usuario == 'null'}">
			<s:if test="hasActionErrors()">
		   		<div class="errors">
		      		<s:actionerror/>
		   		</div>
			</s:if>
		
			<s:form action="Login" theme="simple" method="post">			
				<li><s:textfield key="text.login.email" id="email" name="email" theme="xhtml" required="true" tabindex="1" /></li>			
				<li>
					<s:password key="text.login.password" id="login" name="password" theme="xhtml" required="true" tabindex="2" />				
				</li>
				<li><s:a id="forgotten" action="Forgotten" tabindex="3"><s:text name="login.forget"/></s:a></li>
				<li><s:submit key="text.login.submit" id="submitLogin" name="submit" theme="xhtml" tabindex="4" /></li>
			</s:form>
		</s:if>
		<s:else>
			<li><img alt="<s:text name="nav.login.photo.alt"/>" src="img/user.png"><li>
			<li>${sessionScope.usuario.getLogin()}</li>
			<li> | </li>
		 	<li><s:a action="UnLogin" tabindex="2"><s:text name="out"/></s:a><br/></li>
		</s:else>	 			
	</ul>	
</div>