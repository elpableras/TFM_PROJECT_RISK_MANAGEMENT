<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html>
<html lang="es">

<jsp:include page="../common/logo.jsp" />
<jsp:include page="../common/login.jsp" />
<jsp:include page="../script/add_delete.jsp" />

<body>

	<jsp:include page="../common/header.jsp" />

	<jsp:include page="../nav/nav.jsp" />

	<section class="principal">
		<article id="iden">
			<div id="iden">
				<h2 id="iden">
					<s:text name="iden.cabeza" />
				</h2>
				<s:set var="sizeO">${applicationScope.impactolist.size()}</s:set>
				<s:if test="%{#sizeO != 0}">
					<s:set var="cabezaCol">${sizeO+10}</s:set>
					<s:set var="id">${sessionScope.usuario.getId()}</s:set>
					<s:set var="idP">${sessionScope.usuario.getIdProyecto()}</s:set>
					<s:set var="idenO">${applicationScope.iden.size()}</s:set>

					<s:if test="%{#idenO == 0}">
						<s:form id="idenRisk" action="SaveIden" method="post">
							<s:hidden name="idU" value="%{#id}" />
							<s:hidden name="idP" value="%{#idP}" />
							<s:hidden name="numeroI" value="%{#sizeO}" />

							<table id="tablaIden">
								<tbody id="tbodyIden">
									<tr>
										<td id="cabeza" colspan="${cabezaCol}"><s:text
												name="iden.cabeza" /></td>
									</tr>
									<tr>
										<td id="idenRisk" rowspan="2"><input type="button"
											id="buttonAdd" value="<s:text name="plan.add"/>"
											onclick="addRow()" tabindex="5" /></td>
										<td id="idenRisk" rowspan="2"><s:text name="iden.id" /></td>
										<td id="idenRisk" rowspan="2"><s:text name="iden.nombre" /></td>
										<td id="idenRisk" rowspan="2"><s:text
												name="iden.descripcion" /></td>
										<td id="idenRisk" rowspan="2"><s:text
												name="iden.responsable" /></td>
										<td id="idenRisk" rowspan="2"><s:text
												name="iden.probabilidad" /></td>
										<s:iterator id="proba" value="%{#application.impactolist}">
											<td id="idenRisk"><s:property value="objetivo" /></td>
										</s:iterator>
										<td id="idenRisk" rowspan="2"><s:text
												name="iden.impactoValor" /></td>
										<!-- VALOR DE MATRIX -->
										<td id="idenRisk" class="corte">${application.corte}</td>
										<td id="idenRisk" rowspan="2"><s:text
												name="iden.response" /></td>
										<td id="idenRisk" rowspan="2"><s:text name="iden.notes" /></td>
									</tr>
									<tr>
										<td id="idenRisk" colspan="${sizeO}"><s:text
												name="iden.impacto" /></td>
										<td id="idenRisk"><s:text name="iden.priorizacion" /></td>
									</tr>
									<tr>
										<td><input type="button" id="buttonDelete"
											value="<s:text name="plan.delete"/>" class="eliminarFila"
											tabindex="1"></td>
										<td><input id="idenRiskId" name="id" value="1" readonly
											required /></td>
										<td><s:textarea id="idenRisk"
												value="Aprendizaje de nuevo personal" name="nombre"
												theme="simple" cols="20" rows="10" readonly="true"
												required="true" tabindex="6" /></td>
										<td><s:textarea id="idenRisk" value="" name="descripcion"
												theme="simple" cols="20" rows="10" required="true"
												tabindex="7" /></td>
										<td><input type="text" id="idenRisk" name="responsable"
											value="" required tabindex="8" /></td>
										<td id="probabilidad"><s:select id="idenRisk"
												name="probabilidad"
												list="%{#application.probabilidadeslist}"
												listKey="porcentaje" listValue="probabilidad" theme="simple"
												tabindex="9" /></td>
										<s:set var="tab">${9}</s:set>
										<s:iterator value="" begin="1" end="%{#sizeO}">
											<s:set var="tab">${tab+1}</s:set>
											<td id="impacto"><s:select id="idenRisk" name="impacto"
													list="%{#application.probabilidadlist}"
													listKey="porcentaje" listValue="probabilidad"
													theme="simple" tabindex="%{#tab}" /></td>
										</s:iterator>
										<td><input type="number" id="idenRiskNumber" name="valor"
											value="" readonly required /></td>
										<td class="estudio"></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="response"
												theme="simple" cols="15" rows="10" required="true"
												tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="notes"
												theme="simple" cols="15" rows="10" tabindex="%{#tab}" /></td>
									</tr>
									<tr>
										<s:set var="tab">${tab+1}</s:set>
										<td><input type="button" id="buttonDelete"
											value="<s:text name="plan.delete"/>" class="eliminarFila"
											tabindex="${tab}"></td>
										<td><input id="idenRiskId" name="id" value="2" readonly
											required /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk"
												value="Baja de trabajadores" name="nombre" theme="simple"
												cols="20" rows="10" readonly="true" required="true"
												tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="descripcion"
												theme="simple" cols="20" rows="10" required="true"
												tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><input type="text" id="idenRisk" name="responsable"
											value="" required tabindex="${tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td id="probabilidad"><s:select id="idenRisk"
												name="probabilidad"
												list="%{#application.probabilidadeslist}"
												listKey="porcentaje" listValue="probabilidad" theme="simple"
												tabindex="%{#tab}" /></td>
										<s:iterator value="" begin="1" end="%{#sizeO}">
											<s:set var="tab">${tab+1}</s:set>
											<td id="impacto"><s:select id="idenRisk" name="impacto"
													list="%{#application.probabilidadlist}"
													listKey="porcentaje" listValue="probabilidad"
													theme="simple" tabindex="%{#tab}" /></td>
										</s:iterator>
										<td><input type="number" id="idenRiskNumber" name="valor"
											value="" readonly required /></td>
										<td class="estudio"></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="response"
												theme="simple" cols="15" rows="10" required="true"
												tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="notes"
												theme="simple" cols="15" rows="10" tabindex="%{#tab}" /></td>
									</tr>
									<tr>
										<s:set var="tab">${tab+1}</s:set>
										<td><input type="button" id="buttonDelete"
											value="<s:text name="plan.delete"/>" class="eliminarFila"
											tabindex="${tab}"></td>
										<td><input id="idenRiskId" name="id" value="3" readonly
											required /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk"
												value="Retrasos en las tareas" name="nombre" theme="simple"
												cols="20" rows="10" readonly="true" required="true"
												tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="descripcion"
												theme="simple" cols="20" rows="10" required="true"
												tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><input type="text" id="idenRisk" name="responsable"
											value="" required tabindex="${tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td id="probabilidad"><s:select id="idenRisk"
												name="probabilidad"
												list="%{#application.probabilidadeslist}"
												listKey="porcentaje" listValue="probabilidad" theme="simple"
												tabindex="%{#tab}" /></td>
										<s:iterator value="" begin="1" end="%{#sizeO}">
											<s:set var="tab">${tab+1}</s:set>
											<td id="impacto"><s:select id="idenRisk" name="impacto"
													list="%{#application.probabilidadlist}"
													listKey="porcentaje" listValue="probabilidad"
													theme="simple" tabindex="%{#tab}" /></td>
										</s:iterator>
										<td><input type="number" id="idenRiskNumber" name="valor"
											value="" readonly required /></td>
										<td class="estudio"></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="response"
												theme="simple" cols="15" rows="10" required="true"
												tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="notes"
												theme="simple" cols="15" rows="10" tabindex="%{#tab}" /></td>
									</tr>
									<tr>
										<s:set var="tab">${tab+1}</s:set>
										<td><input type="button" id="buttonDelete"
											value="<s:text name="plan.delete"/>" class="eliminarFila"
											tabindex="${tab}"></td>
										<td><input id="idenRiskId" name="id" value="4" readonly
											required /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk"
												value="Cambio político en la empresa" name="nombre"
												theme="simple" cols="20" rows="10" readonly="true"
												required="true" tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="descripcion"
												theme="simple" cols="20" rows="10" required="true"
												tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><input type="text" id="idenRisk" name="responsable"
											value="" required tabindex="${tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td id="probabilidad"><s:select id="idenRisk"
												name="probabilidad"
												list="%{#application.probabilidadeslist}"
												listKey="porcentaje" listValue="probabilidad" theme="simple"
												tabindex="%{#tab}" /></td>
										<s:iterator value="" begin="1" end="%{#sizeO}">
											<s:set var="tab">${tab+1}</s:set>
											<td id="impacto"><s:select id="idenRisk" name="impacto"
													list="%{#application.probabilidadlist}"
													listKey="porcentaje" listValue="probabilidad"
													theme="simple" tabindex="%{#tab}" /></td>
										</s:iterator>
										<td><input type="number" id="idenRiskNumber" name="valor"
											value="" readonly required /></td>
										<td class="estudio"></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="response"
												theme="simple" cols="15" rows="10" required="true"
												tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="notes"
												theme="simple" cols="15" rows="10" tabindex="%{#tab}" /></td>
									</tr>
									<tr>
										<s:set var="tab">${tab+1}</s:set>
										<td><input type="button" id="buttonDelete"
											value="<s:text name="plan.delete"/>" class="eliminarFila"
											tabindex="${tab}"></td>
										<td><input id="idenRiskId" name="id" value="5" readonly
											required /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk"
												value="Cancelación del proyecto" name="nombre"
												theme="simple" cols="20" rows="10" readonly="true"
												required="true" tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="descripcion"
												theme="simple" cols="20" rows="10" required="true"
												tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><input type="text" id="idenRisk" name="responsable"
											value="" required tabindex="${tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td id="probabilidad"><s:select id="idenRisk"
												name="probabilidad"
												list="%{#application.probabilidadeslist}"
												listKey="porcentaje" listValue="probabilidad" theme="simple"
												tabindex="%{#tab}" /></td>
										<s:iterator value="" begin="1" end="%{#sizeO}">
											<s:set var="tab">${tab+1}</s:set>
											<td id="impacto"><s:select id="idenRisk" name="impacto"
													list="%{#application.probabilidadlist}"
													listKey="porcentaje" listValue="probabilidad"
													theme="simple" tabindex="%{#tab}" /></td>
										</s:iterator>
										<td><input type="number" id="idenRiskNumber" name="valor"
											value="" readonly required /></td>
										<td class="estudio"></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="response"
												theme="simple" cols="15" rows="10" required="true"
												tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="notes"
												theme="simple" cols="15" rows="10" tabindex="%{#tab}" /></td>
									</tr>
									<tr>
										<s:set var="tab">${tab+1}</s:set>
										<td><input type="button" id="buttonDelete"
											value="<s:text name="plan.delete"/>" class="eliminarFila"
											tabindex="${tab}"></td>
										<td><input id="idenRiskId" name="id" value="6" readonly
											required /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="Empleados que fumen"
												name="nombre" theme="simple" cols="20" rows="10"
												readonly="true" required="true" tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="descripcion"
												theme="simple" cols="20" rows="10" required="true"
												tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><input type="text" id="idenRisk" name="responsable"
											value="" required tabindex="${tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td id="probabilidad"><s:select id="idenRisk"
												name="probabilidad"
												list="%{#application.probabilidadeslist}"
												listKey="porcentaje" listValue="probabilidad" theme="simple"
												tabindex="%{#tab}" /></td>
										<s:iterator value="" begin="1" end="%{#sizeO}">
											<s:set var="tab">${tab+1}</s:set>
											<td id="impacto"><s:select id="idenRisk" name="impacto"
													list="%{#application.probabilidadlist}"
													listKey="porcentaje" listValue="probabilidad"
													theme="simple" tabindex="%{#tab}" /></td>
										</s:iterator>
										<td><input type="number" id="idenRiskNumber" name="valor"
											value="" readonly required /></td>
										<td class="estudio"></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="response"
												theme="simple" cols="15" rows="10" required="true"
												tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="notes"
												theme="simple" cols="15" rows="10" tabindex="%{#tab}" /></td>
									</tr>
									<tr>
										<s:set var="tab">${tab+1}</s:set>
										<td><input type="button" id="buttonDelete"
											value="<s:text name="plan.delete"/>" class="eliminarFila"
											tabindex="${tab}"></td>
										<td><input id="idenRiskId" name="id" value="7" readonly
											required /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk"
												value="Mala coordinación entre los equipos de desarrollo"
												name="nombre" theme="simple" cols="20" rows="10"
												readonly="true" required="true" tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="descripcion"
												theme="simple" cols="20" rows="10" required="true"
												tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><input type="text" id="idenRisk" name="responsable"
											value="" required tabindex="${tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td id="probabilidad"><s:select id="idenRisk"
												name="probabilidad"
												list="%{#application.probabilidadeslist}"
												listKey="porcentaje" listValue="probabilidad" theme="simple"
												tabindex="%{#tab}" /></td>
										<s:iterator value="" begin="1" end="%{#sizeO}">
											<s:set var="tab">${tab+1}</s:set>
											<td id="impacto"><s:select id="idenRisk" name="impacto"
													list="%{#application.probabilidadlist}"
													listKey="porcentaje" listValue="probabilidad"
													theme="simple" tabindex="%{#tab}" /></td>
										</s:iterator>
										<td><input type="number" id="idenRiskNumber" name="valor"
											value="" readonly required /></td>
										<td class="estudio"></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="response"
												theme="simple" cols="15" rows="10" required="true"
												tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="notes"
												theme="simple" cols="15" rows="10" tabindex="%{#tab}" /></td>
									</tr>
									<tr>
										<s:set var="tab">${tab+1}</s:set>
										<td><input type="button" id="buttonDelete"
											value="<s:text name="plan.delete"/>" class="eliminarFila"
											tabindex="${tab}"></td>
										<td><input id="idenRiskId" name="id" value="8" readonly
											required /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk"
												value="Mala cualificación del personal" name="nombre"
												theme="simple" cols="20" rows="10" readonly="true"
												required="true" tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="descripcion"
												theme="simple" cols="20" rows="10" required="true"
												tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><input type="text" id="idenRisk" name="responsable"
											value="" required tabindex="${tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td id="probabilidad"><s:select id="idenRisk"
												name="probabilidad"
												list="%{#application.probabilidadeslist}"
												listKey="porcentaje" listValue="probabilidad" theme="simple"
												tabindex="%{#tab}" /></td>
										<s:iterator value="" begin="1" end="%{#sizeO}">
											<s:set var="tab">${tab+1}</s:set>
											<td id="impacto"><s:select id="idenRisk" name="impacto"
													list="%{#application.probabilidadlist}"
													listKey="porcentaje" listValue="probabilidad"
													theme="simple" tabindex="%{#tab}" /></td>
										</s:iterator>
										<td><input type="number" id="idenRiskNumber" name="valor"
											value="" readonly required /></td>
										<td class="estudio"></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="response"
												theme="simple" cols="15" rows="10" required="true"
												tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="notes"
												theme="simple" cols="15" rows="10" tabindex="%{#tab}" /></td>
									</tr>
									<tr>
										<s:set var="tab">${tab+1}</s:set>
										<td><input type="button" id="buttonDelete"
											value="<s:text name="plan.delete"/>" class="eliminarFila"
											tabindex="${tab}"></td>
										<td><input id="idenRiskId" name="id" value="9" readonly
											required /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk"
												value="Planificación mal ajustada" name="nombre"
												theme="simple" cols="20" rows="10" readonly="true"
												required="true" tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="descripcion"
												theme="simple" cols="20" rows="10" required="true"
												tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><input type="text" id="idenRisk" name="responsable"
											value="" required tabindex="${tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td id="probabilidad"><s:select id="idenRisk"
												name="probabilidad"
												list="%{#application.probabilidadeslist}"
												listKey="porcentaje" listValue="probabilidad" theme="simple"
												tabindex="%{#tab}" /></td>
										<s:iterator value="" begin="1" end="%{#sizeO}">
											<s:set var="tab">${tab+1}</s:set>
											<td id="impacto"><s:select id="idenRisk" name="impacto"
													list="%{#application.probabilidadlist}"
													listKey="porcentaje" listValue="probabilidad"
													theme="simple" tabindex="%{#tab}" /></td>
										</s:iterator>
										<td><input type="number" id="idenRiskNumber" name="valor"
											value="" readonly required /></td>
										<td class="estudio"></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="response"
												theme="simple" cols="15" rows="10" required="true"
												tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="notes"
												theme="simple" cols="15" rows="10" tabindex="%{#tab}" /></td>
									</tr>
									<tr>
										<s:set var="tab">${tab+1}</s:set>
										<td><input type="button" id="buttonDelete"
											value="<s:text name="plan.delete"/>" class="eliminarFila"
											tabindex="${tab}"></td>
										<td><input id="idenRiskId" name="id" value="10" readonly
											required /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk"
												value="Cambio de los requisitos durante el proyecto"
												name="nombre" theme="simple" cols="20" rows="10"
												readonly="true" required="true" tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="descripcion"
												theme="simple" cols="20" rows="10" required="true"
												tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><input type="text" id="idenRisk" name="responsable"
											value="" required tabindex="${tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td id="probabilidad"><s:select id="idenRisk"
												name="probabilidad"
												list="%{#application.probabilidadeslist}"
												listKey="porcentaje" listValue="probabilidad" theme="simple"
												tabindex="%{#tab}" /></td>
										<s:iterator value="" begin="1" end="%{#sizeO}">
											<s:set var="tab">${tab+1}</s:set>
											<td id="impacto"><s:select id="idenRisk" name="impacto"
													list="%{#application.probabilidadlist}"
													listKey="porcentaje" listValue="probabilidad"
													theme="simple" tabindex="%{#tab}" /></td>
										</s:iterator>
										<td><input type="number" id="idenRiskNumber" name="valor"
											value="" readonly required /></td>
										<td class="estudio"></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="response"
												theme="simple" cols="15" rows="10" required="true"
												tabindex="%{#tab}" /></td>
										<s:set var="tab">${tab+1}</s:set>
										<td><s:textarea id="idenRisk" value="" name="notes"
												theme="simple" cols="15" rows="10" tabindex="%{#tab}" /></td>
									</tr>
								</tbody>
							</table>

							<s:set var="tab">${tab+1}</s:set>
							<s:submit id="submitIden" key="iden.submit" name="submitIden"
								theme="simple" onclick="noDisabled()" tabindex="%{#tab}" />
						</s:form>
					</s:if>
					<s:elseif test="%{#iden != 'null'}">
						<s:form id="idenRisk" action="SaveIden" method="post">
							<s:hidden name="idU" value="%{#id}" />
							<s:hidden name="idP" value="%{#idP}" />
							<s:hidden name="numeroI" value="%{#sizeO}" />
							<table id="tablaIden">
								<tbody id="tbodyIden">
									<tr>
										<td id="cabeza" colspan="${cabezaCol}"><s:text
												name="iden.cabeza" /></td>
									</tr>
									<tr>
										<td id="idenRisk" rowspan="2"><input type="button"
											id="buttonAdd" value="<s:text name="plan.add"/>"
											onclick="addRow()" tabindex="5" /></td>
										<td id="idenRisk" rowspan="2"><s:text name="iden.id" /></td>
										<td id="idenRisk" rowspan="2"><s:text name="iden.nombre" /></td>
										<td id="idenRisk" rowspan="2"><s:text
												name="iden.descripcion" /></td>
										<td id="idenRisk" rowspan="2"><s:text
												name="iden.responsable" /></td>
										<td id="idenRisk" rowspan="2"><s:text
												name="iden.probabilidad" /></td>
										<s:iterator id="proba" value="%{#application.impactolist}">
											<td id="idenRisk"><s:property value="objetivo" /></td>
										</s:iterator>
										<td id="idenRisk" rowspan="2"><s:text
												name="iden.impactoValor" /></td>
										<!-- VALOR DE MATRIX -->
										<td id="idenRisk" class="corte">${application.corte}</td>
										<td id="idenRisk" rowspan="2"><s:text
												name="iden.response" /></td>
										<td id="idenRisk" rowspan="2"><s:text name="iden.notes" /></td>
									</tr>
									<tr>
										<td id="idenRisk" colspan="${sizeO}"><s:text
												name="iden.impacto" /></td>
										<td id="idenRisk"><s:text name="iden.priorizacion" /></td>
									</tr>
									<s:set var="num">${0}</s:set>
									<s:set var="tab">${0}</s:set>
									<s:set var="i">${0}</s:set>
									<s:iterator var="iden" value="%{#application.iden}">
										<tr>
											<s:set var="tab">${tab+1}</s:set>
											<td><input type="button" id="buttonDeleteIden" value=" "
												class="eliminarFila" tabindex="${tab}"></td>
											<s:set var="num">${num+1}</s:set>
											<td><input id="idenRiskId" name="id" value="${num}"
												readonly required /></td>
											<s:set var="tab">${tab+1}</s:set>
											<td><s:textarea id="idenRisk" value="%{#iden.nombre}"
													name="nombre" theme="simple" cols="20" rows="10"
													required="true" tabindex="%{#tab}" /></td>
											<s:set var="tab">${tab+1}</s:set>
											<td><s:textarea id="idenRisk"
													value="%{#iden.descripcion}" name="descripcion"
													theme="simple" cols="20" rows="10" required="true"
													tabindex="%{#tab}" /></td>
											<s:set var="tab">${tab+1}</s:set>
											<td><input type="text" id="idenRisk" name="responsable"
												value="${iden.responsable}" required tabindex="${tab}" /></td>
											<s:set var="tab">${tab+1}</s:set>
											<td id="probabilidad"><s:select id="idenRisk"
													name="probabilidad"
													list="%{#application.probabilidadeslist}"
													listKey="porcentaje" listValue="probabilidad"
													theme="simple" value="%{#iden.probabilidad}"
													tabindex="%{#tab}" /></td>
											<s:iterator value="" begin="1" end="%{#sizeO}">
												<s:set var="tab">${tab+1}</s:set>
												<s:set var="impacto">${application.valorimpactolist[i]}</s:set>
												<td id="impacto"><s:select id="idenRisk" name="impacto"
														list="%{#application.probabilidadlist}"
														listKey="porcentaje" listValue="probabilidad"
														theme="simple" value="%{#impacto}" tabindex="%{#tab}" /></td>
												<s:set var="i">${i+1}</s:set>
											</s:iterator>
											<td><input type="number" id="idenRiskNumber"
												name="valor" value="${iden.valor}" readonly required /></td>
											<td class="estudio"></td>
											<s:set var="tab">${tab+1}</s:set>
											<td><s:textarea id="idenRisk" value="%{#iden.response}"
													name="response" theme="simple" cols="15" rows="10"
													required="true" tabindex="%{#tab}" /></td>
											<s:set var="tab">${tab+1}</s:set>
											<td><s:textarea id="idenRisk" value="%{#iden.notes}"
													name="notes" theme="simple" cols="15" rows="10"
													tabindex="%{#tab}" /></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
							<s:set var="tab">${tab+1}</s:set>
							<s:submit id="submitIden" key="iden.submit" name="submitIden"
								theme="simple" onclick="noDisabled()" tabindex="%{#tab}" />
						</s:form>
					</s:elseif>
				</s:if>
			</div>
		</article>
	</section>
	<div id="risksystem">
		<s:a action="RiskSystem">
			<s:text name="plan.back" />
		</s:a>
	</div>

	<jsp:include page="../common/footer.jsp" />

</body>

</html>