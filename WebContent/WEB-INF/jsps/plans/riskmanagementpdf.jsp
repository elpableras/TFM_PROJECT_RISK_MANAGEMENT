<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html lang="es">

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
				<h2 id="memberIzq">
					<s:text name="text.steps" />
				</h2>

				<table id="memberIzq">
					<tr>
						<th><s:text name="text.member.right" /></th>
					</tr>
					<tr>
						<td>
							<ul>
								<s:if test='%{#play == 2 && #saveplan == 12}'>
									<li class="ok">1 <s:a action="RiskPlan" tabindex="5">
											<s:text name="text.index.left.4.1" />
										</s:a></li>
									<li class="actual">2 <s:a action="RiskIden" tabindex="6">
											<s:text name="text.index.left.4.3" />
										</s:a></li>
									<li class="steps">3 <s:text name="text.index.left.4.4" /></li>
									<li class="steps">4 <s:a action="LoadPDF" tabindex="7">
											<s:text name="text.index.left.4.5" />
										</s:a></li>
								</s:if>
								<s:elseif test='%{#play == 3}'>
									<li class="ok">1 <s:a action="RiskPlan" tabindex="5">
											<s:text name="text.index.left.4.1" />
										</s:a></li>
									<li class="ok">2 <s:a action="RiskIden" tabindex="6">
											<s:text name="text.index.left.4.3" />
										</s:a></li>
									<li class="actual">3 <s:text name="text.index.left.4.4" /></li>
									<li class="steps">4 <s:a action="LoadPDF" tabindex="7">
											<s:text name="text.index.left.4.5" />
										</s:a></li>
								</s:elseif>
								<s:elseif test='%{#play == 4}'>
									<li class="ok">1 <s:a action="RiskPlan" tabindex="5">
											<s:text name="text.index.left.4.1" />
										</s:a></li>
									<li class="ok">2 <s:a action="RiskIden" tabindex="6">
											<s:text name="text.index.left.4.3" />
										</s:a></li>
									<li class="ok">3 <s:a action="RiskRespManagement"
											tabindex="7">
											<s:text name="text.index.left.4.4" />
										</s:a></li>
									<li class="actual">4 <s:text name="text.index.left.4.5" /></li>
								</s:elseif>
								<s:elseif test='%{#play == 5}'>
									<li class="ok">1 <s:a action="RiskPlan" tabindex="5">
											<s:text name="text.index.left.4.1" />
										</s:a></li>
									<li class="ok">2 <s:a action="RiskIden" tabindex="6">
											<s:text name="text.index.left.4.3" />
										</s:a></li>
									<li class="ok">3 <s:a action="RiskRespManagement"
											tabindex="7">
											<s:text name="text.index.left.4.4" />
										</s:a></li>
									<li class="steps">4 <s:a action="LoadPDF" tabindex="8">
											<s:text name="text.index.left.4.5" />
										</s:a></li>
								</s:elseif>
								<s:else>
									<li class="actual">1 <s:a action="RiskPlan" tabindex="5">
											<s:text name="text.index.left.4.1" />
										</s:a></li>
									<li class="steps">2 <s:a action="RiskIden" tabindex="6">
											<s:text name="text.index.left.4.3" />
										</s:a></li>
									<li class="steps">3 <s:a action="RiskRespManagement"
											tabindex="7">
											<s:text name="text.index.left.4.4" />
										</s:a></li>
									<li class="steps">4 <s:a action="LoadPDF" tabindex="8">
											<s:text name="text.index.left.4.5" />
										</s:a></li>
								</s:else>
							</ul>
						</td>
					</tr>
				</table>
			</div>
		</article>

		<article id="rightMember" class="contenido">
			<div id="member">
				<h2 id="riskM">
					<s:text name="pdf.riesgos" />
				</h2>
				<s:set var="idenSize">${applicationScope.iden.size()}</s:set>
				<s:set var="nombrePlan">
					<s:text name="pdf.plan" />
				</s:set>
				<s:set var="nombreIden">
					<s:text name="pdf.iden" />
				</s:set>
				<table id="riskM">
					<tr>
						<th><s:text name="pdf.plan" /></th>
					</tr>
					<tr>
						<td>
							<ul class="navegador">
								<s:iterator value="%{#application.info}">
									<li class="steps" id="PDFRisk"><s:a action="CreatePDF"
											tabindex="7">
											<s:text name="pdf.plan" />
											<s:param name="option" value="1" />
											<s:param name="title" value="%{#nombrePlan}" />
										</s:a></li>
								</s:iterator>
							</ul>
						</td>
					</tr>
					<tr>
						<th><s:text name="pdf.iden" /></th>
					</tr>
					<tr>
						<td>
							<ul class="navegador">
								<s:if test='%{#idenSize > 0}'>
									<li class="steps" id="PDFRisk"><s:a action="CreatePDF"
											tabindex="7">
											<s:text name="pdf.iden" />
											<s:param name="option" value="2" />
											<s:param name="title" value="%{#nombreIden}" />
										</s:a></li>
								</s:if>
							</ul>
						</td>
					</tr>
					<tr>
						<th><s:text name="pdf.resp" /></th>
					</tr>
					<tr>
						<td>
							<ul class="navegador">
								<s:set var="numRisk">${0}</s:set>
								<s:iterator value="%{#application.study}">
									<s:set var="nombreResp">
										<s:text name="pdf.resp" />
									</s:set>
									<s:set var="nombreResp">${nombreResp} ${nombre}</s:set>
									<li class="steps" id="PDFRisk"><s:a action="CreatePDF"
											tabindex="7">
											<s:text name="pdf.resp" />
											<s:property value="nombre" />
											<s:param name="option" value="3" />
											<s:param name="num" value="%{#numRisk}" />
											<s:param name="title" value="%{#nombreResp}" />
										</s:a></li>
									<s:set var="numRisk">${numRisk+1}</s:set>
									<s:set var="nombreResp"></s:set>
								</s:iterator>
							</ul>
						</td>
					</tr>
				</table>
			</div>
			<s:if test="hasActionMessages()">
				<div class="mensajePDF">
					<s:actionmessage />
				</div>
			</s:if>
			<s:if test="hasActionErrors()">
				<div class="mensajeNoPDF">
					<s:actionerror />
				</div>
			</s:if>
		</article>
	</section>

	<jsp:include page="../common/footer.jsp" />

</body>

</html>