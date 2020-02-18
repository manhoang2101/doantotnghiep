<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="card-menu">
				<li class="dropdown pull-right">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
					<span
						class="glyphicon glyphicon-user">
						</span>
						 <s:property
							value="%{#session.user.name}" /> 
							<span class="caret">
							</span>
							</a>
					<ul class="dropdown-menu" role="menu">
						<li><s:a action="profile">
								<span class="glyphicon glyphicon-user"></span>
								Profile</s:a></li>
						
						<li><s:a action="logout">
								<span class="glyphicon glyphicon-log-out"></span>
								Logout</s:a></li>
					</ul>
					</li>
		
</div>
	
