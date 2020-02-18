<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#sidebar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"><span>DMV SHOP</span>Admin</a>
			<ul class="user-menu">
				<li class="dropdown pull-right"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"><span
						class="glyphicon glyphicon-user"></span> <s:property
							value="%{#session.a_user.name}" /> <span class="caret"></span></a>
							
					<ul class="dropdown-menu" role="menu">
						<li><s:a action="profile" namespace="/admin">
								<span class="glyphicon glyphicon-user"></span>
								Thông tin cá nhân</s:a></li>
						<li><s:a action="logout" namespace="/admin">
								<span class="glyphicon glyphicon-log-out"></span>
								Đăng xuất</s:a></li>
					</ul>
					
					</li>
			</ul>
		</div>

	</div>
	<!-- /.container-fluid -->
</nav>