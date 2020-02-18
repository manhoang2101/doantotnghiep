<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:url var="currentUrl" includeParams="all" />
<s:if test="%{#session.error != null}">
	<div class="alert bg-danger" role="alert">
		<span class="glyphicon glyphicon-exclamation-sign"></span>
		<s:property value="%{#session.error}" />
		<s:a href="%{#currentUrl}" class="pull-right">
			<span class="glyphicon glyphicon-remove"></span>
		</s:a>
	</div>
	<s:set var="error" value="" scope="session" />
</s:if>
<s:if test="%{#session.success != null}">
	<div class="alert bg-success" role="alert">
		<span class="glyphicon glyphicon-check"></span>
		<s:property value="%{#session.success}" />
		<s:a href="%{#currentUrl}" class="pull-right">
			<span class="glyphicon glyphicon-remove"></span>
		</s:a>
	</div>
	<s:set var="success" value="" scope="session" />
</s:if>