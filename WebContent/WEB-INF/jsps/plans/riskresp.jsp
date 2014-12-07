<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html>
<html>

<jsp:include page="../common/logo.jsp" />
<jsp:include page="../common/login.jsp" />
<jsp:include page="../script/add_delete.jsp" />

<body>

	<jsp:include page="../common/header.jsp" />
	
	<jsp:include page="../nav/nav.jsp" />

	<section class="principal">
		<article id="resp">			
			<div id="resp">
			
				<s:set var="sizeO">${applicationScope.impactolist.size()}</s:set>
				<s:set var="cabezaCol">${sizeO+9}</s:set>
				<s:set var="idU">${sessionScope.usuario.getId()}</s:set>
				<s:set var="idP">${sessionScope.usuario.getIdProyecto()}</s:set>					
				<s:set var="option">${param.option}</s:set>	
				<s:set var="id">${applicationScope.respuesta.getId()}</s:set>						
				
			
				<s:form id="respRisk" action="SaveResp" method="post">
					<s:hidden name="idU" value="%{#idU}" />
					<s:hidden name="idP" value="%{#idP}" />
					<s:hidden name="numeroI" value="%{#sizeO}" />
					<s:hidden name="option" value="%{#option}" />
					
					<s:if test="%{#id > 0}">										
						<table id="tablaResp">
							<tbody id="tbodyResp"> 
					       		<tr>				       			
					       			<td id="cabeza" colspan="${cabezaCol}"><s:text name="resp.cabeza"/></td>				       							       							       							       			
					       		</tr>
					       		<tr>
					       			<td id="center"><s:text name="resp.id"/>: <input type="text" id="respRiskId" name="id" value="${application.respuesta.getId()}" required readonly tabindex="5"/></td>	
					       			<td id="izq"colspan="6"><s:text name="resp.nombre"/>: <input type="text" id="respRiskNombre" name="nombre" value="${application.respuesta.getNombre()}" required readonly tabindex="6"/></td>
					       		</tr>				
					       		<tr>
					       			<td id="center"><s:text name="resp.descripcion"/>:</td>
					       			<td colspan="6"><textarea cols="30" rows="5" id="respRisk" name="descripcion" required tabindex="7">${application.respuesta.getDescripcion()}</textarea></td>
					       		</tr>
					       		<tr>
					       			<td id="center"><s:text name="resp.categoria"/>:</td>
					       			<td colspan="6"><textarea cols="30" rows="5" id="respRisk" name="categoria" required tabindex="8">${application.respuesta.getCategoria()}</textarea></td>
					       		</tr>
					       		<tr>
					       			<td id="center"><s:text name="resp.status"/>: <input type="text" id="respRisk" name="status" value="${application.respuesta.getStatus()}" tabindex="9"/></td>
					       			<td id="center" colspan="6"><s:text name="resp.causas"/>: <textarea cols="30" rows="5" id="respRisk2" name="causas" required tabindex="10">${application.respuesta.getCausas()}</textarea></td>
					       		</tr>
					       		<tr>
					       			<td id="idenRisk"><s:text name="resp.probabilidad"/></td>	
						       		<s:iterator id="proba" value="%{#application.impactolist}">
						       			<td id="idenRisk"><s:property value="objetivo"/></td>
						       		</s:iterator>	       				       				       		
						       		<td id="idenRisk"><s:text name="resp.impactoValor"/></td>					       	
						       		<td id="idenRisk"><s:text name="resp.response"/></td>
					       		</tr>
					       		<s:set var="tab">${10}</s:set>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<s:set var="prob">${application.respuesta.getProbabilidad()}</s:set>
					       			<td id="probabilidad"><s:select id="respRisk" name="probabilidad" list="%{#application.probabilidadeslist}" listKey="porcentaje" listValue="probabilidad" value="%{#prob}" theme="simple" tabindex="%{#tab}"/></td>				       			
					       			<s:set var="i">${0}</s:set>
					       			<s:iterator value="" begin="1" end="%{#sizeO}">
					       				<s:set var="tab">${tab+1}</s:set>				       				
					       				<s:set var="impacto">${application.impactosresplist[i]}</s:set>
					       				<td id="impacto"><s:select id="respRisk" name="impacto" list="%{#application.probabilidadlist}" listKey="porcentaje" listValue="probabilidad" value="%{#impacto}" theme="simple" tabindex="%{#tab}"/></td>		       				
					       				<s:set var="i">${i+1}</s:set>
					       			</s:iterator>	
					       			<td><input type="number" id="idenRiskNumber" name="valor" value="${application.respuesta.getValor()}" readonly required/></td>	       							       					       							       			
					       			<s:set var="tab">${tab+1}</s:set>
					       			<s:set var="resp">${application.respuesta.getResponse()}</s:set>
					       			<td><s:textarea id="idenRisk" value="%{#resp}" name="response" theme="simple" cols="15" rows="10" required="true" tabindex="%{#tab}"/></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>					       			
					       			<td id="idenRisk"><s:text name="resp.probabilidad2"/><br><s:text name="resp.fecha"/>: <input type="date" name="fechaRevision" min="2000-01-02" value="${application.respuesta.getFechaRevisada()}" tabindex="${tab}"></td>						       		
						       		<s:iterator id="proba" value="%{#application.impactolist}">
						       			<td id="idenRisk"><s:property value="objetivo"/></td>
						       		</s:iterator>	       				       				       		
						       		<td id="idenRisk"><s:text name="resp.impactoValor"/></td>					       	
						       		<td id="idenRisk"><s:text name="resp.response"/></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<s:set var="prob">${application.respuesta.getProbabilidadRevisada()}</s:set>					       			
					       			<td id="probabilidad"><s:select id="respRisk" name="probabilidadRevisada" headerKey="-1" headerValue=" " list="%{#application.probabilidadeslist}" value="%{#prob}" listKey="porcentaje" listValue="probabilidad" theme="simple" tabindex="%{#tab}"/></td>				       			
					       			<s:set var="i">${0}</s:set>					       			
					       			<s:iterator value="" begin="1" end="%{#sizeO}">
					       				<s:set var="tab">${tab+1}</s:set>				       				
					       				<s:set var="impactoR">${application.impactosRresplist[i]}</s:set>
					       				<td id="impacto"><s:select id="respRisk" name="impactoRevisado" headerKey="-1" headerValue=" " list="%{#application.probabilidadlist}" value="%{#impactoR}"  listKey="porcentaje" listValue="probabilidad" theme="simple" tabindex="%{#tab}"/></td>		       				
					       				<s:set var="i">${i+1}</s:set>					       						       				
					       			</s:iterator>	
					       			<td><input type="number" id="idenRiskNumber" name="valorRevisado" value="${application.respuesta.getValorRevisado()}" readonly/></td>	       							       					       							       			
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td><s:textarea id="idenRisk" value="%{#application.respuesta.getResponseRevisado()}" name="responseRevisado" theme="simple" cols="15" rows="10" tabindex="%{#tab}"/></td>
					       		</tr>				       		
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center"><s:text name="resp.derivados"/>:</td>
					       			<td colspan="6"><textarea cols="30" rows="5" id="respRisk" name="derivados" required tabindex="${tab}">${application.respuesta.getDerivado()}</textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center"><s:text name="resp.residual"/>:</td>
					       			<td colspan="6"><textarea cols="30" rows="5" id="respRisk" name="residual" required tabindex="${tab}">${application.respuesta.getResidual()}</textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center" colspan="4" rowspan="2"><s:text name="resp.contingencia"/>: <textarea cols="30" rows="5" id="respRisk" name="contingencia" required tabindex="${tab}">${application.respuesta.getContingencia()}</textarea></td>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center" colspan="3"><s:text name="resp.presupuesto"/>: <input type="text" id="respRiskPresu" name="presupuesto" value="${application.respuesta.getPresupuesto()}" tabindex="${tab}"/></td>					       			
					       		</tr>
					       		<tr>					       			
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center" colspan="3"><s:text name="resp.planificacion"/>: <textarea cols="30" rows="5" id="respPlanificacion" name="planificacion" required tabindex="${tab}">${application.respuesta.getPlanificacion()}</textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center"><s:text name="resp.comentarios"/>:</td>				       			
					       			<td colspan="6"><textarea cols="30" rows="5" id="respRisk" name="comentarios" required tabindex="${tab}">${application.respuesta.getComentarios()}</textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center" colspan="7"><s:text name="resp.monitorizacion"/>: <textarea cols="30" rows="5" id="respRisk" name="monitorizacion" required tabindex="${tab}">${application.respuesta.getMonitorizacion()}</textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>				       			
					       			<td id="center"><input type="button" id="buttonAdd" value="<s:text name="respRisk.indicador"/>" onclick="addRowIndicator()" tabindex="${tab}"/></td>
					       			<td id="center" colspan="6"><s:text name="resp.indicadores"/></td>				       			
					       		</tr>
					       		<s:set var="i">${0}</s:set>					       		
						       	<s:iterator value="%{#application.indicadoreslist}" >
						       		<tr>
						       			<s:set var="tab">${tab+1}</s:set>
						       			<td id="center"><s:text name="resp.indicador"/>: <input type="text" id="respRisk" name="indicador" value="${application.indicadoreslist[i]}" required tabindex="${tab}"/></td>					       		
						       			<s:set var="tab">${tab+1}</s:set>
						       			<td id="center" colspan="6"><s:text name="resp.evaluacion"/>: <textarea cols="30" rows="5" id="respRisk" name="evaluacion" required tabindex="${tab}">${application.evaluacioneslist[i]}</textarea></td>
						       		</tr>
						       		<s:set var="i">${i+1}</s:set>	
						       	</s:iterator>					       						       							       		
							</tbody> 
				       	</table>								
					</s:if>
					<s:else>					
						<table id="tablaResp">
							<tbody id="tbodyResp"> 
					       		<tr>				       			
					       			<td id="cabeza" colspan="${cabezaCol}"><s:text name="resp.cabeza"/></td>				       							       							       							       			
					       		</tr>
					       		<tr>
					       			<td id="center"><s:text name="resp.id"/>: <input type="text" id="respRiskId" name="id" value="${application.study[option].getId()}" required readonly tabindex="5"/></td>	
					       			<td id="izq"colspan="6"><s:text name="resp.nombre"/>: <input type="text" id="respRiskNombre" name="nombre" value="${application.study[option].getNombre()}" required readonly tabindex="6"/></td>
					       		</tr>				
					       		<tr>
					       			<td id="center"><s:text name="resp.descripcion"/>:</td>
					       			<td colspan="6"><textarea cols="30" rows="5" id="respRisk" name="descripcion" required tabindex="7">${application.study[option].getDescripcion()}</textarea></td>
					       		</tr>
					       		<tr>
					       			<td id="center"><s:text name="resp.categoria"/>:</td>
					       			<td colspan="6"><textarea cols="30" rows="5" id="respRisk" name="categoria" required tabindex="8"></textarea></td>
					       		</tr>
					       		<tr>
					       			<td id="center"><s:text name="resp.status"/>: <input type="text" id="respRisk" name="status" value="" tabindex="9"/></td>
					       			<td id="center" colspan="6"><s:text name="resp.causas"/>: <textarea cols="30" rows="5" id="respRisk2" name="causas" required tabindex="10"></textarea></td>
					       		</tr>
					       		<tr>
					       			<td id="idenRisk"><s:text name="resp.probabilidad"/></td>	
						       		<s:iterator id="proba" value="%{#application.impactolist}">
						       			<td id="idenRisk"><s:property value="objetivo"/></td>
						       		</s:iterator>	       				       				       		
						       		<td id="idenRisk"><s:text name="resp.impactoValor"/></td>					       	
						       		<td id="idenRisk"><s:text name="resp.response"/></td>
					       		</tr>
					       		<s:set var="tab">${10}</s:set>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<s:set var="prob">${application.study[option].getProbabilidad()}</s:set>
					       			<td id="probabilidad"><s:select id="respRisk" name="probabilidad" list="%{#application.probabilidadeslist}" listKey="porcentaje" listValue="probabilidad" value="%{#prob}" theme="simple" tabindex="%{#tab}"/></td>				       			
					       			<s:set var="i">${sizeO*option}</s:set>
					       			<s:iterator value="" begin="1" end="%{#sizeO}">
					       				<s:set var="tab">${tab+1}</s:set>				       				
					       				<s:set var="impacto">${application.valorimpactolist[i]}</s:set>
					       				<td id="impacto"><s:select id="respRisk" name="impacto" list="%{#application.probabilidadlist}" listKey="porcentaje" listValue="probabilidad" value="%{#impacto}" theme="simple" tabindex="%{#tab}"/></td>		       				
					       				<s:set var="i">${i+1}</s:set>
					       			</s:iterator>	
					       			<td><input type="number" id="idenRiskNumber" name="valor" value="${application.study[option].getValor()}" readonly required/></td>	       							       					       							       			
					       			<s:set var="tab">${tab+1}</s:set>
					       			<s:set var="resp">${application.study[option].getResponse()}</s:set>
					       			<td><s:textarea id="idenRisk" value="%{#resp}" name="response" theme="simple" cols="15" rows="10" required="true" tabindex="%{#tab}"/></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="idenRisk"><s:text name="resp.probabilidad2"/><br><s:text name="resp.fecha"/>: <input type="date" name="fechaRevision" min="2000-01-02" tabindex="${tab}"></td>						       		
						       		<s:iterator id="proba" value="%{#application.impactolist}">
						       			<td id="idenRisk"><s:property value="objetivo"/></td>
						       		</s:iterator>	       				       				       		
						       		<td id="idenRisk"><s:text name="resp.impactoValor"/></td>					       	
						       		<td id="idenRisk"><s:text name="resp.response"/></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="probabilidad"><s:select id="respRisk" name="probabilidadRevisada" headerKey="-1" headerValue=" " list="%{#application.probabilidadeslist}" listKey="porcentaje" listValue="probabilidad" theme="simple" tabindex="%{#tab}"/></td>				       			
					       			<s:iterator value="" begin="1" end="%{#sizeO}">
					       				<s:set var="tab">${tab+1}</s:set>
					       				<td id="impacto"><s:select id="respRisk" name="impactoRevisado" headerKey="-1" headerValue=" " list="%{#application.probabilidadlist}" listKey="porcentaje" listValue="probabilidad" theme="simple" tabindex="%{#tab}"/></td>		       				
					       			</s:iterator>	
					       			<td><input type="number" id="idenRiskNumber" name="valorRevisado" value="" readonly/></td>	       							       					       							       			
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td><s:textarea id="idenRisk" value="" name="responseRevisado" theme="simple" cols="15" rows="10" tabindex="%{#tab}"/></td>
					       		</tr>				       		
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center"><s:text name="resp.derivados"/>:</td>
					       			<td colspan="6"><textarea cols="30" rows="5" id="respRisk" name="derivados" required tabindex="${tab}"></textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center"><s:text name="resp.residual"/>:</td>
					       			<td colspan="6"><textarea cols="30" rows="5" id="respRisk" name="residual" required tabindex="${tab}"></textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center" colspan="4" rowspan="2"><s:text name="resp.contingencia"/>: <textarea cols="30" rows="5" id="respRisk" name="contingencia" required tabindex="${tab}"></textarea></td>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center" colspan="3"><s:text name="resp.presupuesto"/>: <input type="text" id="respRiskPresu" name="presupuesto" value="" tabindex="${tab}"/></td>					       			
					       		</tr>
					       		<tr>					       			
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center" colspan="3"><s:text name="resp.planificacion"/>: <textarea cols="30" rows="5" id="respPlanificacion" name="planificacion" required tabindex="${tab}"></textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center"><s:text name="resp.comentarios"/>:</td>				       			
					       			<td colspan="6"><textarea cols="30" rows="5" id="respRisk" name="comentarios" required tabindex="${tab}">${application.study[option].getNotes()}</textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center" colspan="7"><s:text name="resp.monitorizacion"/>: <textarea cols="30" rows="5" id="respRisk" name="monitorizacion" required tabindex="${tab}"></textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>				       			
					       			<td id="center"><input type="button" id="buttonAdd" value="<s:text name="respRisk.indicador"/>" onclick="addRowIndicator()" tabindex="${tab}"/></td>
					       			<td id="center" colspan="6"><s:text name="resp.indicadores"/></td>				       			
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center"><s:text name="resp.indicador"/>: <input type="text" id="respRisk" name="indicador" value="" required tabindex="${tab}"/></td>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center" colspan="6"><s:text name="resp.evaluacion"/>: <textarea cols="30" rows="5" id="respRisk" name="evaluacion" required tabindex="${tab}"></textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center"><s:text name="resp.indicador"/>: <input type="text" id="respRisk" name="indicador" value="" required tabindex="${tab}"/></td>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center"colspan="6"><s:text name="resp.evaluacion"/>: <textarea cols="30" rows="5" id="respRisk" name="evaluacion" required tabindex="${tab}"></textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center"><s:text name="resp.indicador"/>: <input type="text" id="respRisk" name="indicador" value="" required tabindex="${tab}"/></td>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center" colspan="6"><s:text name="resp.evaluacion"/>: <textarea cols="30" rows="5" id="respRisk" name="evaluacion" required tabindex="${tab}"></textarea></td>
					       		</tr>
							</tbody> 
				       	</table>
					</s:else>
			       							
				<s:set var="tab">${tab+1}</s:set>												       	
				<s:submit id="submitRespi" key="resp.submit" theme="simple" onclick="noDisabled()" tabindex="%{#tab}" />
			</s:form>
								
			</div>				
		</article>
	</section>
	<div id="risksystem">
		<s:a action="RiskRespManagement"><s:text name="resp.back"/></s:a>
	</div>		
	
	<jsp:include page="../common/footer.jsp" />

</body>

</html>