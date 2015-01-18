<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html lang="es">

<jsp:include page="../common/logo.jsp" />
<jsp:include page="../common/login.jsp" />
<jsp:include page="../script/add_delete.jsp" />

<body>

	<jsp:include page="../common/header.jsp" />

	<jsp:include page="../nav/nav.jsp" />

	<section class="principal">
		<article id="plan">
			<div id="plan">

				<s:set var="option">${param.option}</s:set>

				<jsp:useBean id="date" class="java.util.Date" />
				<fmt:formatDate value="${date}" pattern="dd/MM/yyyy" var="date" />

				<s:set var="date">${date}</s:set>
				<s:set var="id">${sessionScope.usuario.getId()}</s:set>
				<s:set var="name">${sessionScope.usuario.getLogin()}</s:set>
				<s:set var="idP">${sessionScope.usuario.getIdProyecto()}</s:set>
				<s:set var="version">${sessionScope.plan.getVersion()}</s:set>

				<s:if test='%{#option == "1"}'>
					<h2 id="version">
						<s:text name="plan.1" />
					</h2>
					<s:if test="%{#version!='null'}">
						<table id="tablaCambio">
							<tr>
								<td><s:text name="plan.1.1" /></td>
								<td><s:text name="plan.1.2" /></td>
								<td><s:text name="plan.1.4" /></td>
								<td><s:text name="plan.1.5" /></td>
							</tr>
							<s:iterator id="cambio" value="%{#application.cambioslist}">
								<tr>
									<td><input type="number" id="planCambioVersion" name="id"
										readonly value="<s:property value="id"/>" tabindex="6" /></td>
									<td><input type="text" id="planCambio" name="plan"
										readonly value="<s:property value="plan"/>" tabindex="7" /></td>
									<td><input type="text" id="planCambio" name="version"
										readonly value="<s:property value="version"/>" tabindex="9" /></td>
									<td><input type="text" id="planCambio" name="fecha"
										readonly value="<s:property value="fecha"/>" tabindex="10" /></td>
								</tr>
							</s:iterator>
						</table>

						<table id="tablaVersion">
							<tr>
								<td><s:text name="plan.input.change" /></td>
							</tr>
							<s:set var="i">${0}</s:set>
							<s:iterator id="proba" value="%{#application.versiones}">
								<s:set var="version">${applicationScope.versiones[i]}</s:set>
								<s:set var="v">${applicationScope.version}</s:set>
								<tr>
									<s:if test="%{#v==#version}">
										<td class="actual"><s:a action="ChangeVersion"
												tabindex="11">
												<s:property value="version" />
												<s:param name="version" value="%{#version}" />
											</s:a></td>
									</s:if>
									<s:else>
										<td><s:a action="ChangeVersion" tabindex="11">
												<s:property value="version" />
												<s:param name="version" value="%{#version}" />
											</s:a></td>
									</s:else>
								</tr>
								<s:set var="i">${i+1}</s:set>
							</s:iterator>
						</table>
					</s:if>
				</s:if>

				<s:if test='%{#option == "2"}'>
					<h2 id="plan">
						<s:text name="plan.2" />
					</h2>

					<s:form id="plan" action="SavePlan" method="post">
						<s:hidden name="date" value="%{#date}" />
						<s:hidden name="id" value="%{#id}" />
						<s:hidden name="name" value="%{#name}" />
						<s:hidden name="idP" value="%{#idP}" />
						<s:hidden name="codPlan" value="2" />
						<s:hidden name="version" value="%{#version}" />

						<s:set var="metodologia">${sessionScope.plan.getMetodologia()}</s:set>

						<s:if test="%{#metodologia!='null'}">
							<s:set var="plan">${metodologia}</s:set>
							<s:textarea key="plan.2" name="plan" cols="40" rows="10"
								value="%{#plan}" tabindex="5" />
						</s:if>
						<s:else>
							<s:textarea key="plan.2" name="plan" cols="40" rows="10"
								tabindex="5" />
						</s:else>

						<s:submit id="submitPlan" key="plan.submit" name="submitPlan"
							tabindex="6" />
					</s:form>
				</s:if>

				<s:if test='%{#option == "3"}'>
					<h2 id="plan">
						<s:text name="plan.3" />
					</h2>

					<s:form id="plan" action="SavePlan" method="post">
						<s:hidden name="id" value="%{#id}" />
						<s:hidden name="idP" value="%{#idP}" />
						<s:hidden name="codPlan" value="3" />
						<s:hidden name="version" value="%{#version}" />

						<s:set var="tecno">${sessionScope.plan.getHtecno()}</s:set>

						<s:if test="%{#tecno!='null'}">
							<s:set var="plan">${tecno}</s:set>
							<s:textarea key="plan.3" name="plan" cols="40" rows="10"
								value="%{#plan}" tabindex="5" />
						</s:if>
						<s:else>
							<s:textarea key="plan.3" name="plan" cols="40" rows="10"
								tabindex="5" />
						</s:else>

						<s:submit id="submitPlan" key="plan.submit" name="submitPlan"
							tabindex="6" />
					</s:form>
				</s:if>


				<s:if test='%{#option == "4"}'>
					<h2 id="plan">
						<s:text name="plan.4" />
					</h2>
					<s:form id="plan" action="SavePlan" method="post">
						<s:hidden name="id" value="%{#id}" />
						<s:hidden name="idP" value="%{#idP}" />
						<s:hidden name="codPlan" value="4" />
						<s:hidden name="version" value="%{#version}" />

						<s:set var="roles">${sessionScope.plan.getRoles()}</s:set>

						<s:if test="%{#roles!='null'}">
							<s:set var="plan">${roles}</s:set>
							<s:textarea key="plan.4" name="plan" cols="40" rows="10"
								value="%{#plan}" tabindex="5" />
						</s:if>
						<s:else>
							<s:textarea key="plan.4" name="plan" cols="40" rows="10"
								tabindex="5" />
						</s:else>

						<s:submit id="submitPlan" key="plan.submit" name="submitPlan"
							tabindex="6" />
					</s:form>
				</s:if>
				<s:if test='%{#option == "5"}'>
					<h2 id="plan">
						<s:text name="plan.5" />
					</h2>
					<s:form id="plan" action="SavePlan" method="post">
						<s:hidden name="id" value="%{#id}" />
						<s:hidden name="idP" value="%{#idP}" />
						<s:hidden name="codPlan" value="5" />
						<s:hidden name="version" value="%{#version}" />

						<s:set var="presu">${sessionScope.plan.getPresu()}</s:set>

						<s:if test="%{#presu!='null'}">
							<s:set var="plan">${presu}</s:set>
							<s:textarea key="plan.5" name="plan" cols="40" rows="10"
								value="%{#plan}" tabindex="5" />
						</s:if>
						<s:else>
							<s:textarea key="plan.5" name="plan" cols="40" rows="10"
								tabindex="5" />
						</s:else>

						<s:submit id="submitPlan" key="plan.submit" name="submitPlan"
							tabindex="6" />
					</s:form>
				</s:if>
				<s:if test='%{#option == "6"}'>
					<h2 id="plan">
						<s:text name="plan.6" />
					</h2>
					<s:form id="plan" action="SavePlan" method="post">
						<s:hidden name="id" value="%{#id}" />
						<s:hidden name="idP" value="%{#idP}" />
						<s:hidden name="codPlan" value="6" />
						<s:hidden name="version" value="%{#version}" />

						<s:set var="calendar">${sessionScope.plan.getCalendar()}</s:set>

						<s:if test="%{#calendar!='null'}">
							<s:set var="plan">${calendar}</s:set>
							<s:textarea key="plan.6" name="plan" cols="40" rows="10"
								value="%{#plan}" tabindex="5" />
						</s:if>
						<s:else>
							<s:textarea key="plan.6" name="plan" cols="40" rows="10"
								tabindex="5" />
						</s:else>

						<s:submit id="submitPlan" key="plan.submit" name="submitPlan"
							tabindex="6" />
					</s:form>
				</s:if>
				<s:if test='%{#option == "7"}'>
					<h2 id="plan">
						<s:text name="plan.7" />
					</h2>
					<s:form id="plan" action="SavePlan" method="post">
						<s:hidden name="id" value="%{#id}" />
						<s:hidden name="idP" value="%{#idP}" />
						<s:hidden name="codPlan" value="7" />
						<s:hidden name="version" value="%{#version}" />

						<s:set var="riesgo">${sessionScope.plan.getRiesgo()}</s:set>

						<s:if test="%{#riesgo!='null'}">
							<s:set var="plan">${riesgo}</s:set>
							<s:textarea key="plan.7" name="plan" cols="40" rows="10"
								value="%{#plan}" tabindex="5" />
						</s:if>
						<s:else>
							<s:textarea key="plan.7" name="plan" cols="40" rows="10"
								tabindex="5" />
						</s:else>

						<s:submit id="submitPlan" key="plan.submit" name="submitPlan"
							tabindex="6" />
					</s:form>
				</s:if>
				<s:if test='%{#option == "8"}'>
					<h2 id="plan">
						<s:text name="plan.8" />
					</h2>
					<form id="planP" action="SavePlan" method="post">
						<s:hidden name="id" value="%{#id}" />
						<s:hidden name="idP" value="%{#idP}" />
						<s:hidden name="codPlan" value="8" />
						<s:hidden name="version" value="%{#version}" />

						<s:set var="probabilidad">${sessionScope.plan.getProbabilidad().size()}</s:set>

						<s:if test="%{#probabilidad > 0}">
							<table id="tablaPlan">
								<s:iterator id="proba"
									value="%{#application.probabilidadeslist}">
									<tr>
										<td><s:text name="plan.8.1" />: <input type="number"
											id="planNumber" step="5" min="5" max="95" maxlength="2"
											size="2" name="porcentaje"
											value="<s:property value="porcentaje"/>" required
											tabindex="5" /></td>
										<td><s:text name="plan.8.2" />: <input type="text"
											id="planImpact" name="probabilidad"
											value="<s:property value="probabilidad"/>" required
											tabindex="6" /></td>
										<td><s:text name="plan.8.3" />: <textarea cols="30"
												rows="5" id="planDefine1" name="descripcion" required
												tabindex="20"><s:property value="descripcion" /></textarea></td>
									</tr>
								</s:iterator>
							</table>
						</s:if>
						<s:else>
							<table id="tablaPlan">
								<tr>
									<td><s:text name="plan.8.1" />: <input type="number"
										placeholder="90" id="planNumber" step="5" min="5" max="95"
										maxlength="2" size="2" name="porcentaje" required tabindex="5" /></td>
									<td><s:text name="plan.8.2" />: <input type="text"
										placeholder="Muy Alta" id="planImpact" name="probabilidad"
										required tabindex="6" /></td>
									<td><s:text name="plan.8.3" />: <textarea cols="30"
											rows="5"
											placeholder="Objetivos críticos del proyecto están seriamente impactados o no se cumplirán (coste, calendario, calidad o satisfacción del cliente)"
											id="planDefine1" name="descripcion" required tabindex="7"></textarea></td>
								</tr>
								<tr>
									<td><s:text name="plan.8.1" />: <input type="number"
										placeholder="70" id="planNumber" step="5" min="5" max="95"
										maxlength="2" size="2" name="porcentaje" required tabindex="8" /></td>
									<td><s:text name="plan.8.2" />: <input type="text"
										placeholder="Alta" id="planImpact" name="probabilidad"
										required tabindex="9" /></td>
									<td><s:text name="plan.8.3" />: <textarea cols="30"
											rows="5"
											placeholder="Objetivos críticos del proyecto que están amenazados"
											id="planDefine1" name="descripcion" required tabindex="10"></textarea></td>
								</tr>
								<tr>
									<td><s:text name="plan.8.1" />: <input type="number"
										placeholder="50" id="planNumber" step="5" min="5" max="95"
										maxlength="2" size="2" name="porcentaje" required
										tabindex="11" /></td>
									<td><s:text name="plan.8.2" />: <input type="text"
										placeholder="Media" id="planImpact" name="probabilidad"
										required tabindex="12" /></td>
									<td><s:text name="plan.8.3" />: <textarea cols="30"
											rows="5"
											placeholder="Algunos objetivos del proyecto pueden verse afectados"
											id="planDefine1" name="descripcion" required tabindex="13"></textarea></td>
								</tr>
								<tr>
									<td><s:text name="plan.8.1" />: <input type="number"
										placeholder="30" id="planNumber" step="5" min="5" max="95"
										maxlength="2" size="2" name="porcentaje" required
										tabindex="14" /></td>
									<td><s:text name="plan.8.2" />: <input type="text"
										placeholder="Baja" id="planImpact" name="probabilidad"
										required tabindex="15" /></td>
									<td><s:text name="plan.8.3" />: <textarea cols="30"
											rows="5"
											placeholder="Remediables. Los objetivos del proyecto pueden verse afectados, pero sin grandes problemas"
											id="planDefine1" name="descripcion" required tabindex="16"></textarea></td>
								</tr>
								<tr>
									<td><s:text name="plan.8.1" />: <input type="number"
										placeholder="10" id="planNumber" step="5" min="5" max="95"
										maxlength="2" size="2" name="porcentaje" required
										tabindex="17" /></td>
									<td><s:text name="plan.8.2" />: <input type="text"
										placeholder="Muy Baja" id="planImpact" name="probabilidad"
										required tabindex="18" /></td>
									<td><s:text name="plan.8.3" />: <textarea cols="30"
											rows="5"
											placeholder="Fácilmente remediable. Los objetivos del proyecto no serán afectados"
											id="planDefine1" name="descripcion" required tabindex="19"></textarea></td>
								</tr>
							</table>
						</s:else>

						<div id="submitPlanRiskProbability">
							<input type="submit" id="submitPlanRisk"
								value="<s:text name="plan.submit" />" tabindex="20" />
						</div>
					</form>
				</s:if>

				<s:if test='%{#option == "9"}'>
					<form id="planObjetive" action="SavePlan" method="post">
						<s:hidden name="id" value="%{#id}" />
						<s:hidden name="idP" value="%{#idP}" />
						<s:hidden name="codPlan" value="9" />
						<s:hidden name="version" value="%{#version}" />

						<s:set var="impacto">${sessionScope.plan.getImpacto().size()}</s:set>

						<s:if test="%{#impacto > 0}">
							<c:set var="j" value="true" />
							<s:set var="j">${j}</s:set>
							<c:set var="i" value="0" />
							<s:set var="i">${i}</s:set>
							<c:set var="k" value="4" />
							<s:set var="k">${k}</s:set>

							<table id="tablaPlanObjetive">
								<tbody id="tbodyPlan">
									<tr>
										<td id="cabeza" colspan="7"><s:text name="plan.cabeza.9" /></td>
									</tr>
									<tr>
										<td id="cabeza" rowspan="2" colspan="2"><s:text
												name="plan.objetives" /></td>
										<s:iterator value="%{#application.probabilidades}" begin="0"
											end="4">
											<td><s:text name="plan.9.1" />: <input type="number"
												id="planNumber" step="5" min="5" max="95" maxlength="2"
												size="2" name="porcentaje"
												value="<s:property value="porcentaje"/>" required
												tabindex="5" /></td>
										</s:iterator>
									</tr>
									<tr>
										<s:iterator value="%{#application.probabilidades}" begin="0"
											end="4">
											<td><s:text name="plan.9.2" />: <input type="text"
												id="planImpact" name="probabilidad"
												value="<s:property value="probabilidad"/>" required
												tabindex="6" /></td>
										</s:iterator>
									</tr>
									<s:iterator id="proba" value="%{#application.impactolist}">
										<tr>
											<s:if test="%{#j == 'true'}">
												<td><input type="button" id="buttonAdd"
													value="<s:text name="plan.add"/>" class="agregarFila"
													tabindex="7" /></td>
											</s:if>
											<s:else>
												<td><input type="button" id="buttonDelete" value=" "
													class="eliminarFila" tabindex="8"></td>
											</s:else>
											<td><s:text name="plan.9.3" />: <input type="text"
												id="planObjetive" name="objetivo"
												value="<s:property value="objetivo"/>" required tabindex="9" /></td>
											<s:iterator value="%{#application.probabilidades}"
												begin="%{#i}" end="%{#k}">
												<td><s:text name="plan.9.4" />: <textarea cols="20"
														rows="4" id="planDefine" name="descripcion" required
														tabindex="10"><s:property value="descripcion" /></textarea></td>
											</s:iterator>
											<s:set var="i">${i+5}</s:set>
											<s:set var="k">${k+5}</s:set>
											<s:set var="j">${false}</s:set>
										</tr>
									</s:iterator>
								</tbody>
							</table>

						</s:if>
						<s:else>
							<table id="tablaPlanObjetive">
								<tbody id="tbodyPlan">
									<tr>
										<td id="cabeza" colspan="7"><s:text name="plan.cabeza.9" /></td>
									</tr>
									<tr>
										<td id="cabeza" rowspan="2" colspan="2"><s:text
												name="plan.objetives" /></td>
										<td><s:text name="plan.9.1" />: <input type="number"
											placeholder="5" id="planNumber" step="5" min="5" max="95"
											maxlength="2" size="2" name="porcentaje" required
											tabindex="5" /></td>
										<td><s:text name="plan.9.1" />: <input type="number"
											placeholder="15" id="planNumber" step="5" min="5" max="95"
											maxlength="2" size="2" name="porcentaje" required
											tabindex="6" /></td>
										<td><s:text name="plan.9.1" />: <input type="number"
											placeholder="30" id="planNumber" step="5" min="5" max="95"
											maxlength="2" size="2" name="porcentaje" required
											tabindex="7" /></td>
										<td><s:text name="plan.9.1" />: <input type="number"
											placeholder="55" id="planNumber" step="5" min="5" max="95"
											maxlength="2" size="2" name="porcentaje" required
											tabindex="8" /></td>
										<td><s:text name="plan.9.1" />: <input type="number"
											placeholder="90" id="planNumber" step="5" min="5" max="95"
											maxlength="2" size="2" name="porcentaje" required
											tabindex="9" /></td>
									</tr>
									<tr>
										<td><s:text name="plan.9.2" />: <input type="text"
											placeholder="Muy Bajo" id="planImpact" name="probabilidad"
											required tabindex="10" /></td>
										<td><s:text name="plan.9.2" />: <input type="text"
											placeholder="Bajo" id="planImpact" name="probabilidad"
											required tabindex="11" /></td>
										<td><s:text name="plan.9.2" />: <input type="text"
											placeholder="Medio" id="planImpact" name="probabilidad"
											required tabindex="12" /></td>
										<td><s:text name="plan.9.2" />: <input type="text"
											placeholder="Alto" id="planImpact" name="probabilidad"
											required tabindex="13" /></td>
										<td><s:text name="plan.9.2" />: <input type="text"
											placeholder="Muy Alto" id="planImpact" name="probabilidad"
											required tabindex="14" /></td>
									</tr>
									<tr>
										<td><input type="button" id="buttonAdd"
											value="<s:text name="plan.add"/>" class="agregarFila"
											tabindex="15" /></td>
										<td><s:text name="plan.9.3" />: <input type="text"
											placeholder="Coste" id="planObjetive" name="objetivo"
											required tabindex="16" /></td>
										<td><s:text name="plan.9.4" />: <textarea cols="20"
												rows="4" placeholder="Aumento del coste insignificante"
												id="planDefine" name="descripcion" required tabindex="17"></textarea></td>
										<td><s:text name="plan.9.4" />: <textarea cols="20"
												rows="4" placeholder="Incremento del coste <15%"
												id="planDefine" name="descripcion" required tabindex="18"></textarea></td>
										<td><s:text name="plan.9.4" />: <textarea cols="20"
												rows="4" placeholder="Incremento del coste entre el 15-30%"
												id="planDefine" name="descripcion" required tabindex="19"></textarea></td>
										<td><s:text name="plan.9.4" />: <textarea cols="20"
												rows="4" placeholder="Incremento del coste entre el 30-55%"
												id="planDefine" name="descripcion" required tabindex="20"></textarea></td>
										<td><s:text name="plan.9.4" />: <textarea cols="20"
												rows="4" placeholder="Incremento del coste > 55%"
												id="planDefine" name="descripcion" required tabindex="21"></textarea></td>
									</tr>
								</tbody>
							</table>
						</s:else>

						<div id="submitPlanRiskImpact">
							<input type="submit" id="submitPlanRisk" class="enviarDatos"
								value="<s:text name="plan.submit"/>" tabindex="22" />
						</div>
					</form>
				</s:if>
				<s:if test='%{#option == "10"}'>

					<s:set var="probabilidad">${sessionScope.plan.getProbabilidad().size()}</s:set>
					<s:set var="rangoSize">${application.rango.size()}</s:set>
					<h2 id="matriz">
						<s:text name="plan.10" />
					</h2>
					<s:if test="%{#probabilidad != 0}">
						<form id="corte" action="SavePlan" method="post">
							<s:hidden name="id" value="%{#id}" />
							<s:hidden name="idP" value="%{#idP}" />
							<s:hidden name="codPlan" value="10" />
							<s:hidden name="version" value="%{#version}" />
							<table id="tablaRangosMatriz">
								<tbody>
									<tr>
										<td id="cabeza" colspan="3"><s:text name="matriz.cabeza" /></td>
									</tr>
									<tr>
										<td id=subcabeza><s:text name="matriz.color" /></td>
										<td id=subcabeza>&gt;=</td>
										<td id=subcabeza>&lt;</td>
									</tr>
									<s:if test="%{#rangoSize > 0}">
										<tr class="verde">
											<td id="verde"><s:text name="matriz.color.green" /></td>
											<td id="verde">0.00</td>
											<td id="verde"><input onchange="cambiaValorMatriz()"
												type="number" id="matrixNumberGreen" step="0.01" min="0"
												max="1" maxlength="1" size="1" name="rango"
												value="${application.rango[0]}" required tabindex="5" /></td>
										</tr>
										<tr class="amarillo">
											<td id="amarillo"><s:text name="matriz.color.yellow" /></td>
											<td id="amarillo">${application.rango[0]}</td>
											<td id="amarillo">${application.rango[1]}</td>
										</tr>
										<tr class="rojo">
											<td id="rojo"><s:text name="matriz.color.red" /></td>
											<td id="rojo"><input onchange="cambiaValorMatriz()"
												type="number" id="matrixNumberRed" step="0.01" min="0"
												max="1" maxlength="1" size="1" name="rango"
												value="${application.rango[1]}" required tabindex="6" /></td>
											<td id="rojo">1</td>
										</tr>
									</s:if>
									<s:else>
										<tr class="verde">
											<td id="verde"><s:text name="matriz.color.green" /></td>
											<td id="verde">0.00</td>
											<td id="verde"><input onchange="cambiaValorMatriz()"
												type="number" id="matrixNumberGreen" step="0.01" min="0"
												max="1" maxlength="1" size="1" name="rango" value="0.05"
												required tabindex="5" /></td>
										</tr>
										<tr class="amarillo">
											<td id="amarillo"><s:text name="matriz.color.yellow" /></td>
											<td id="amarillo">0.05</td>
											<td id="amarillo">0.50</td>
										</tr>
										<tr class="rojo">
											<td id="rojo"><s:text name="matriz.color.red" /></td>
											<td id="rojo"><input onchange="cambiaValorMatriz()"
												type="number" id="matrixNumberRed" step="0.01" min="0"
												max="1" maxlength="1" size="1" name="rango" value="0.50"
												required tabindex="6" /></td>
											<td id="rojo">1</td>
										</tr>
									</s:else>
									<s:if test="%{#application.corte > 0}">
										<tr>
											<td id="corte"><s:text name="matriz.estudio" /></td>
											<td id="corte"><s:text name="matriz.corte" /></td>
											<td id="corte"><input type="number" id="matrixCorte"
												step="0.01" min="0" max="1" maxlength="1" size="1"
												name="corte" value="${application.corte}" required
												tabindex="7" /></td>
										</tr>
									</s:if>
									<s:else>
										<tr>
											<td id="corte"><s:text name="matriz.estudio" /></td>
											<td id="corte"><s:text name="matriz.corte" /></td>
											<td id="corte"><input type="number" id="matrixCorte"
												step="0.01" min="0" max="1" maxlength="1" size="1"
												name="corte" value="0.50" required tabindex="7" /></td>
										</tr>
									</s:else>
									<tr>
										<td></td>
										<td></td>
										<td><input type="submit" id="submitCorte"
											value="<s:text name="matrix.submit"/>" tabindex="8" /></td>
									</tr>
								</tbody>
							</table>
						</form>

						<table id="tablaMatrix">
							<tbody id="tbodyPlan">
								<tr>
									<td id="cabeza" colspan="9"><s:text name="plan.cabeza.10" /></td>
								</tr>
								<tr>
									<td id="rotar" rowspan="8" class="rotar"><s:text
											name="plan.8.2" /></td>
								</tr>
								<s:iterator value="%{#application.probabilidadeslist}" begin="0"
									end="4" step="1">
									<tr>
										<td><s:property value="probabilidad" /></td>
										<td><s:property value="porcentaje" /></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</s:iterator>
								<tr>
									<td></td>
									<td></td>
									<s:iterator value="%{#application.probabilidades}" begin="0"
										end="4">
										<td><s:property value="porcentaje" /></td>
									</s:iterator>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<s:iterator value="%{#application.probabilidades}" begin="0"
										end="4">
										<td><s:property value="probabilidad" /></td>
									</s:iterator>
								</tr>
								<tr>
									<td id="cabeza" colspan="9"><s:text name="plan.9.1" /></td>
								</tr>
							</tbody>
						</table>
						<script>
			       		fillMatrix();
			       	</script>
					</s:if>
				</s:if>
				<s:if test='%{#option == "11"}'>
					<h2 id="plan">
						<s:text name="plan.11" />
					</h2>
					<s:form id="plan" action="SavePlan" method="post">
						<s:hidden name="id" value="%{#id}" />
						<s:hidden name="idP" value="%{#idP}" />
						<s:hidden name="codPlan" value="11" />
						<s:hidden name="version" value="%{#version}" />

						<s:set var="contingencia">${sessionScope.plan.getContingencia()}</s:set>

						<s:if test="%{#contingencia!='null'}">
							<s:set var="plan">${contingencia}</s:set>
							<s:textarea key="plan.11" name="plan" cols="40" rows="10"
								value="%{#plan}" tabindex="5" />
						</s:if>
						<s:else>
							<s:textarea key="plan.11" name="plan" cols="40" rows="10"
								tabindex="5" />
						</s:else>

						<s:submit id="submitPlan" key="plan.submit" name="submitPlan"
							tabindex="6" />
					</s:form>
				</s:if>
				<s:if test='%{#option == "12"}'>
					<h2 id="plan">
						<s:text name="plan.12" />
					</h2>
					<s:form id="plan" action="SavePlan" method="post">
						<s:hidden name="id" value="%{#id}" />
						<s:hidden name="idP" value="%{#idP}" />
						<s:hidden name="codPlan" value="12" />
						<s:hidden name="version" value="%{#version}" />

						<s:set var="formato">${sessionScope.plan.getFormato()}</s:set>

						<s:if test="%{#formato!='null'}">
							<s:set var="plan">${formato}</s:set>
							<s:textarea key="plan.12" name="plan" cols="40" rows="10"
								value="%{#plan}" tabindex="5" />
						</s:if>
						<s:else>
							<s:textarea key="plan.12" name="plan" cols="40" rows="10"
								tabindex="5" />
						</s:else>

						<s:submit id="submitPlan" key="plan.submit" name="submitPlan"
							tabindex="6" />
					</s:form>
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