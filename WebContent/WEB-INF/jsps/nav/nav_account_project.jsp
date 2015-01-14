<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="navigation">
	<nav id="navigation">
		<ul id="navigation">
			<s:set var="usuario">${sessionScope.usuario}</s:set>
			<s:if test="%{#usuario!='null'}">
				<s:set var="admin">${sessionScope.usuario.isAdmin()}</s:set>
				<s:set var="manager">${sessionScope.usuario.isManager()}</s:set>
			</s:if>
			<s:if test="%{#admin}">
				<li class="noselected"><h2>
						<s:a action="Interceptor" tabindex="3">
							<s:text name="nav.create.project" />
						</s:a>
					</h2></li>
				<li class="noselected"><h2>
						<s:a action="InfoProjects" tabindex="4">
							<s:text name="nav.info.project" />
						</s:a>
					</h2></li>
			</s:if>
			<s:elseif test="%{#manager}">
				<li class="noselected"><h2>
						<s:a action="Interceptor" tabindex="3">
							<s:text name="nav.create.member" />
						</s:a>
					</h2></li>
				<li class="noselected"><h2>
						<s:a action="InfoProjects" tabindex="4">
							<s:text name="nav.info.project" />
						</s:a>
					</h2></li>
			</s:elseif>
			<s:else>
				<li class="noselected"><h2>
						<s:a action="RiskSystem" tabindex="3">
							<s:text name="nav.risksystem.member" />
						</s:a>
					</h2></li>
			</s:else>
			<li class="selected"><h2>
					<s:text name="nav.account.project" />
				</h2></li>
			<li class="noselected"><h2>
					<s:a action="Help" tabindex="5">
						<s:text name="nav.help.project" />
					</s:a>
				</h2></li>
		</ul>
	</nav>
</div>