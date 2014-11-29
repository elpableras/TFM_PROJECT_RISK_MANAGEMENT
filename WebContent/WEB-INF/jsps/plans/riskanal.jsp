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
		<article id="anal">			
			<div id="anal">
			
				<s:set var="sizeO">${applicationScope.impactolist.size()}</s:set>
				<s:set var="cabezaCol">${sizeO+9}</s:set>
				<s:set var="idU">${sessionScope.usuario.getId()}</s:set>
				<s:set var="idP">${sessionScope.usuario.getIdProyecto()}</s:set>					
				<s:set var="option">${param.option}</s:set>	
				<s:set var="id">${applicationScope.analisis.getId()}</s:set>						
				
			
				<s:form id="analRisk" action="SaveAnal" method="post">
					<s:hidden name="idU" value="%{#idU}" />
					<s:hidden name="idP" value="%{#idP}" />
					<s:hidden name="numeroI" value="%{#sizeO}" />
					<s:hidden name="option" value="%{#option}" />
					
					<s:if test="%{#id > 0}">										
						<table id="tablaAnal">
							<tbody id="tbodyAnal"> 
					       		<tr>				       			
					       			<td id="cabeza" colspan="${cabezaCol}"><s:text name="anali.cabeza"/></td>				       							       							       							       			
					       		</tr>
					       		<tr>
					       			<td id="center"><s:text name="anali.id"/>: <input type="text" id="analiRiskId" name="id" value="${application.analisis.getId()}" required readonly tabindex="5"/></td>	
					       			<td id="izq"colspan="6"><s:text name="anali.nombre"/>: <input type="text" id="analiRiskNombre" name="nombre" value="${application.analisis.getNombre()}" required readonly tabindex="6"/></td>
					       		</tr>				
					       		<tr>
					       			<td id="center"><s:text name="anali.descripcion"/>:</td>
					       			<td colspan="6"><textarea cols="30" rows="5" id="analiRisk" name="descripcion" required tabindex="7">${application.analisis.getDescripcion()}</textarea></td>
					       		</tr>
					       		<tr>
					       			<td id="center"><s:text name="anali.categoria"/>:</td>
					       			<td colspan="6"><textarea cols="30" rows="5" id="analiRisk" name="categoria" required tabindex="8">${application.analisis.getCategoria()}</textarea></td>
					       		</tr>
					       		<tr>
					       			<td id="center"><s:text name="anali.status"/>: <input type="text" id="analiRisk" name="status" value="${application.analisis.getStatus()}" tabindex="9"/></td>
					       			<td id="center" colspan="6"><s:text name="anali.causas"/>: <textarea cols="30" rows="5" id="analiRisk2" name="causas" required tabindex="10">${application.analisis.getCausas()}</textarea></td>
					       		</tr>
					       		<tr>
					       			<td id="idenRisk"><s:text name="anali.probabilidad"/></td>	
						       		<s:iterator id="proba" value="%{#application.impactolist}">
						       			<td id="idenRisk"><s:property value="objetivo"/></td>
						       		</s:iterator>	       				       				       		
						       		<td id="idenRisk"><s:text name="anali.impactoValor"/></td>					       	
						       		<td id="idenRisk"><s:text name="anali.response"/></td>
					       		</tr>
					       		<s:set var="tab">${10}</s:set>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<s:set var="prob">${application.analisis.getProbabilidad()}</s:set>
					       			<td id="probabilidad"><s:select id="analiRisk" name="probabilidad" list="%{#application.probabilidadeslist}" listKey="porcentaje" listValue="probabilidad" value="%{#prob}" theme="simple" tabindex="%{#tab}"/></td>				       			
					       			<s:set var="i">${0}</s:set>
					       			<s:iterator value="" begin="1" end="%{#sizeO}">
					       				<s:set var="tab">${tab+1}</s:set>				       				
					       				<s:set var="impacto">${application.impactosanalilist[i]}</s:set>
					       				<td id="impacto"><s:select id="analiRisk" name="impacto" list="%{#application.probabilidadlist}" listKey="porcentaje" listValue="probabilidad" value="%{#impacto}" theme="simple" tabindex="%{#tab}"/></td>		       				
					       				<s:set var="i">${i+1}</s:set>
					       			</s:iterator>	
					       			<td><input type="number" id="idenRiskNumber" name="valor" value="${application.analisis.getValor()}" readonly required/></td>	       							       					       							       			
					       			<s:set var="tab">${tab+1}</s:set>
					       			<s:set var="resp">${application.analisis.getResponse()}</s:set>
					       			<td><s:textarea id="idenRisk" value="%{#resp}" name="response" theme="simple" cols="15" rows="10" required="true" tabindex="%{#tab}"/></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>					       			
					       			<td id="idenRisk"><s:text name="anali.probabilidad2"/><br><s:text name="anali.fecha"/>: <input type="date" name="fechaRevision" min="2000-01-02" value="${application.analisis.getFechaRevisada()}" tabindex="${tab}"></td>						       		
						       		<s:iterator id="proba" value="%{#application.impactolist}">
						       			<td id="idenRisk"><s:property value="objetivo"/></td>
						       		</s:iterator>	       				       				       		
						       		<td id="idenRisk"><s:text name="anali.impactoValor"/></td>					       	
						       		<td id="idenRisk"><s:text name="anali.response"/></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<s:set var="prob">${application.analisis.getProbabilidadRevisada()}</s:set>					       			
					       			<td id="probabilidad"><s:select id="analiRisk" name="probabilidadRevisada" headerKey="-1" headerValue=" " list="%{#application.probabilidadeslist}" value="%{#prob}" listKey="porcentaje" listValue="probabilidad" theme="simple" tabindex="%{#tab}"/></td>				       			
					       			<s:set var="i">${0}</s:set>					       			
					       			<s:iterator value="" begin="1" end="%{#sizeO}">
					       				<s:set var="tab">${tab+1}</s:set>				       				
					       				<s:set var="impactoR">${application.impactosRanalilist[i]}</s:set>
					       				<td id="impacto"><s:select id="analiRisk" name="impactoRevisado" headerKey="-1" headerValue=" " list="%{#application.probabilidadlist}" value="%{#impactoR}"  listKey="porcentaje" listValue="probabilidad" theme="simple" tabindex="%{#tab}"/></td>		       				
					       				<s:set var="i">${i+1}</s:set>					       						       				
					       			</s:iterator>	
					       			<td><input type="number" id="idenRiskNumber" name="valorRevisado" value="${application.analisis.getValorRevisado()}" readonly/></td>	       							       					       							       			
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td><s:textarea id="idenRisk" value="%{#application.analisis.getResponseRevisado()}" name="responseRevisado" theme="simple" cols="15" rows="10" tabindex="%{#tab}"/></td>
					       		</tr>				       		
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center"><s:text name="anali.derivados"/>:</td>
					       			<td colspan="6"><textarea cols="30" rows="5" id="analiRisk" name="derivados" required tabindex="${tab}">${application.analisis.getDerivado()}</textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center"><s:text name="anali.residual"/>:</td>
					       			<td colspan="6"><textarea cols="30" rows="5" id="analiRisk" name="residual" required tabindex="${tab}">${application.analisis.getResidual()}</textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center" colspan="4" rowspan="2"><s:text name="anali.contingencia"/>: <textarea cols="30" rows="5" id="analiRisk" name="contingencia" required tabindex="${tab}">${application.analisis.getContingencia()}</textarea></td>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center" colspan="3"><s:text name="anali.presupuesto"/>: <input type="text" id="analiRiskPresu" name="presupuesto" value="${application.analisis.getPresupuesto()}" tabindex="${tab}"/></td>					       			
					       		</tr>
					       		<tr>					       			
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center" colspan="3"><s:text name="anali.planificacion"/>: <textarea cols="30" rows="5" id="analiPlanificacion" name="planificacion" required tabindex="${tab}">${application.analisis.getPlanificacion()}</textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center"><s:text name="anali.comentarios"/>:</td>				       			
					       			<td colspan="6"><textarea cols="30" rows="5" id="analiRisk" name="comentarios" required tabindex="${tab}">${application.analisis.getComentarios()}</textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center" colspan="7"><s:text name="anali.monitorizacion"/>: <textarea cols="30" rows="5" id="analiRisk" name="monitorizacion" required tabindex="${tab}">${application.analisis.getMonitorizacion()}</textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>				       			
					       			<td id="center"><input type="button" id="buttonAdd" value="<s:text name="analiRisk.indicador"/>" onclick="addRowIndicator()" tabindex="${tab}"/></td>
					       			<td id="center" colspan="6"><s:text name="anali.indicadores"/></td>				       			
					       		</tr>
					       		<s:set var="i">${0}</s:set>					       		
						       	<s:iterator value="%{#application.indicadoreslist}" >
						       		<tr>
						       			<s:set var="tab">${tab+1}</s:set>
						       			<td id="center"><s:text name="anali.indicador"/>: <input type="text" id="analiRisk" name="indicador" value="${application.indicadoreslist[i]}" required tabindex="${tab}"/></td>					       		
						       			<s:set var="tab">${tab+1}</s:set>
						       			<td id="center" colspan="6"><s:text name="anali.evaluacion"/>: <textarea cols="30" rows="5" id="analiRisk" name="evaluacion" required tabindex="${tab}">${application.evaluacioneslist[i]}</textarea></td>
						       		</tr>
						       		<s:set var="i">${i+1}</s:set>	
						       	</s:iterator>					       						       							       		
							</tbody> 
				       	</table>								
					</s:if>
					<s:else>					
						<table id="tablaAnal">
							<tbody id="tbodyAnal"> 
					       		<tr>				       			
					       			<td id="cabeza" colspan="${cabezaCol}"><s:text name="anali.cabeza"/></td>				       							       							       							       			
					       		</tr>
					       		<tr>
					       			<td id="center"><s:text name="anali.id"/>: <input type="text" id="analiRiskId" name="id" value="${application.study[option].getId()}" required readonly tabindex="5"/></td>	
					       			<td id="izq"colspan="6"><s:text name="anali.nombre"/>: <input type="text" id="analiRiskNombre" name="nombre" value="${application.study[option].getNombre()}" required readonly tabindex="6"/></td>
					       		</tr>				
					       		<tr>
					       			<td id="center"><s:text name="anali.descripcion"/>:</td>
					       			<td colspan="6"><textarea cols="30" rows="5" id="analiRisk" name="descripcion" required tabindex="7">${application.study[option].getDescripcion()}</textarea></td>
					       		</tr>
					       		<tr>
					       			<td id="center"><s:text name="anali.categoria"/>:</td>
					       			<td colspan="6"><textarea cols="30" rows="5" id="analiRisk" name="categoria" required tabindex="8"></textarea></td>
					       		</tr>
					       		<tr>
					       			<td id="center"><s:text name="anali.status"/>: <input type="text" id="analiRisk" name="status" value="" tabindex="9"/></td>
					       			<td id="center" colspan="6"><s:text name="anali.causas"/>: <textarea cols="30" rows="5" id="analiRisk2" name="causas" required tabindex="10"></textarea></td>
					       		</tr>
					       		<tr>
					       			<td id="idenRisk"><s:text name="anali.probabilidad"/></td>	
						       		<s:iterator id="proba" value="%{#application.impactolist}">
						       			<td id="idenRisk"><s:property value="objetivo"/></td>
						       		</s:iterator>	       				       				       		
						       		<td id="idenRisk"><s:text name="anali.impactoValor"/></td>					       	
						       		<td id="idenRisk"><s:text name="anali.response"/></td>
					       		</tr>
					       		<s:set var="tab">${10}</s:set>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<s:set var="prob">${application.study[option].getProbabilidad()}</s:set>
					       			<td id="probabilidad"><s:select id="analiRisk" name="probabilidad" list="%{#application.probabilidadeslist}" listKey="porcentaje" listValue="probabilidad" value="%{#prob}" theme="simple" tabindex="%{#tab}"/></td>				       			
					       			<s:set var="i">${sizeO*option}</s:set>
					       			<s:iterator value="" begin="1" end="%{#sizeO}">
					       				<s:set var="tab">${tab+1}</s:set>				       				
					       				<s:set var="impacto">${application.valorimpactolist[i]}</s:set>
					       				<td id="impacto"><s:select id="analiRisk" name="impacto" list="%{#application.probabilidadlist}" listKey="porcentaje" listValue="probabilidad" value="%{#impacto}" theme="simple" tabindex="%{#tab}"/></td>		       				
					       				<s:set var="i">${i+1}</s:set>
					       			</s:iterator>	
					       			<td><input type="number" id="idenRiskNumber" name="valor" value="${application.study[option].getValor()}" readonly required/></td>	       							       					       							       			
					       			<s:set var="tab">${tab+1}</s:set>
					       			<s:set var="resp">${application.study[option].getResponse()}</s:set>
					       			<td><s:textarea id="idenRisk" value="%{#resp}" name="response" theme="simple" cols="15" rows="10" required="true" tabindex="%{#tab}"/></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="idenRisk"><s:text name="anali.probabilidad2"/><br><s:text name="anali.fecha"/>: <input type="date" name="fechaRevision" min="2000-01-02" tabindex="${tab}"></td>						       		
						       		<s:iterator id="proba" value="%{#application.impactolist}">
						       			<td id="idenRisk"><s:property value="objetivo"/></td>
						       		</s:iterator>	       				       				       		
						       		<td id="idenRisk"><s:text name="anali.impactoValor"/></td>					       	
						       		<td id="idenRisk"><s:text name="anali.response"/></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="probabilidad"><s:select id="analiRisk" name="probabilidadRevisada" headerKey="-1" headerValue=" " list="%{#application.probabilidadeslist}" listKey="porcentaje" listValue="probabilidad" theme="simple" tabindex="%{#tab}"/></td>				       			
					       			<s:iterator value="" begin="1" end="%{#sizeO}">
					       				<s:set var="tab">${tab+1}</s:set>
					       				<td id="impacto"><s:select id="analiRisk" name="impactoRevisado" headerKey="-1" headerValue=" " list="%{#application.probabilidadlist}" listKey="porcentaje" listValue="probabilidad" theme="simple" tabindex="%{#tab}"/></td>		       				
					       			</s:iterator>	
					       			<td><input type="number" id="idenRiskNumber" name="valorRevisado" value="" readonly/></td>	       							       					       							       			
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td><s:textarea id="idenRisk" value="" name="responseRevisado" theme="simple" cols="15" rows="10" tabindex="%{#tab}"/></td>
					       		</tr>				       		
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center"><s:text name="anali.derivados"/>:</td>
					       			<td colspan="6"><textarea cols="30" rows="5" id="analiRisk" name="derivados" required tabindex="${tab}"></textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center"><s:text name="anali.residual"/>:</td>
					       			<td colspan="6"><textarea cols="30" rows="5" id="analiRisk" name="residual" required tabindex="${tab}"></textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center" colspan="4" rowspan="2"><s:text name="anali.contingencia"/>: <textarea cols="30" rows="5" id="analiRisk" name="contingencia" required tabindex="${tab}"></textarea></td>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center" colspan="3"><s:text name="anali.presupuesto"/>: <input type="text" id="analiRiskPresu" name="presupuesto" value="" tabindex="${tab}"/></td>					       			
					       		</tr>
					       		<tr>					       			
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center" colspan="3"><s:text name="anali.planificacion"/>: <textarea cols="30" rows="5" id="analiPlanificacion" name="planificacion" required tabindex="${tab}"></textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center"><s:text name="anali.comentarios"/>:</td>				       			
					       			<td colspan="6"><textarea cols="30" rows="5" id="analiRisk" name="comentarios" required tabindex="${tab}">${application.study[option].getNotes()}</textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center" colspan="7"><s:text name="anali.monitorizacion"/>: <textarea cols="30" rows="5" id="analiRisk" name="monitorizacion" required tabindex="${tab}"></textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>				       			
					       			<td id="center"><input type="button" id="buttonAdd" value="<s:text name="analiRisk.indicador"/>" onclick="addRowIndicator()" tabindex="${tab}"/></td>
					       			<td id="center" colspan="6"><s:text name="anali.indicadores"/></td>				       			
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center"><s:text name="anali.indicador"/>: <input type="text" id="analiRisk" name="indicador" value="" required tabindex="${tab}"/></td>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center" colspan="6"><s:text name="anali.evaluacion"/>: <textarea cols="30" rows="5" id="analiRisk" name="evaluacion" required tabindex="${tab}"></textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center"><s:text name="anali.indicador"/>: <input type="text" id="analiRisk" name="indicador" value="" required tabindex="${tab}"/></td>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center"colspan="6"><s:text name="anali.evaluacion"/>: <textarea cols="30" rows="5" id="analiRisk" name="evaluacion" required tabindex="${tab}"></textarea></td>
					       		</tr>
					       		<tr>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center"><s:text name="anali.indicador"/>: <input type="text" id="analiRisk" name="indicador" value="" required tabindex="${tab}"/></td>
					       			<s:set var="tab">${tab+1}</s:set>
					       			<td id="center" colspan="6"><s:text name="anali.evaluacion"/>: <textarea cols="30" rows="5" id="analiRisk" name="evaluacion" required tabindex="${tab}"></textarea></td>
					       		</tr>
							</tbody> 
				       	</table>
					</s:else>
			       							
				<s:set var="tab">${tab+1}</s:set>												       	
				<s:submit id="submitAnali" key="anali.submit" theme="simple" onclick="noDisabled()" tabindex="%{#tab}" />
			</s:form>
								
			</div>				
		</article>
	</section>
	<div id="risksystem">
		<s:a action="RiskAnalManagement"><s:text name="anali.back"/></s:a>
	</div>		
	
	<jsp:include page="../common/footer.jsp" />

</body>

</html>