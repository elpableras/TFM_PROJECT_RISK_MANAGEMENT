<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<footer>
	<p>
		<a href="http://www.uniovi.es" title="<s:text name="footer.uni.title"/>"><s:text name="footer.uni"/></a>
	</p>
	<p>
		<small><s:text name="footer.small"/></small>
	</p>
	<p><small>© <a href="mailto:UO173780@uniovi.es">Pablo González Jiménez</a> 2014 MIW-TFM</small></p>
	
	<s:set var="usuario">${sessionScope.usuario}</s:set>
	<s:if test="%{#usuario != 'null'}">
		<s:set var="usuario">${sessionScope.usuario.getLogin()}</s:set>
		<p><small><s:text name="footer.visitas"/>: <s:property value="%{#application.counter.getValue()}" /></small>
			<small><s:text name="footer.timestamp"/> <s:property value="%{#application.counter.getTimestamp()}" /></small>
	   		<small><s:text name="footer.ip"/>: <s:property value="%{#application.counter.getIp()}" /></small></p>
	</s:if>					
</footer>