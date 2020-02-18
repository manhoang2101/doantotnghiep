<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<ul class="nav menu">
		<li class="active"><s:a action="index" namespace="/admin">
				<span class="glyphicon glyphicon-dashboard"></span> Thống kê</s:a></li>
		<li><s:a action="index" namespace="/admin/category">
				<span class="glyphicon glyphicon-th"></span> Danh mục loại sản phẩm</s:a></li>
		<li><s:a action="index" namespace="/admin/product">
				<span class="glyphicon glyphicon-stats"></span>Quản lý sản phẩm</s:a></li>
		<li><s:a action="index" namespace="/admin/order">
				<span class="glyphicon glyphicon-list-alt"></span>Quản lý đơn hàng</s:a></li>
		<li><s:a action="index" namespace="/admin/customer">
				<span class="glyphicon glyphicon-user"></span>Quản lý người dùng</s:a></li>
				
		<li role="presentation" class="divider"></li>
		<li><a href="index.html"><span
				class="glyphicon glyphicon-user"></span> Trang bán hàng</a></li>
		</ul>
</div>
<!--/.sidebar-->