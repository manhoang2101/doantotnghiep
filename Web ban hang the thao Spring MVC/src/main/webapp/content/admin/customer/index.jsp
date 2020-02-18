<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%--/.header --%>
<s:include value="../layout/header.jsp" />
<!--/.main content -->
<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
			<li class="active">Người dùng</li>
		</ol>
	</div>
	<!--/.row-->

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					Danh sách người dùng
				</div>
				<%--error display --%>
				<s:include value="../layout/message.jsp" />
				<div class="panel-body">
					<table data-toggle="table" data-show-refresh="true"
						data-show-toggle="true" data-show-columns="true"
						data-search="true" data-select-item-name="toolbar1"
						data-pagination="true" data-sort-name="name"
						data-sort-order="desc">
						<thead>
							<tr>
								<th data-sortable="true"><strong>STT</strong></th>
								<th data-sortable="true"><strong>Mã khách hàng</strong></th>
								<th data-sortable="true"><strong>Tên khách hàng</strong></th>
								<th data-sortable="true"><strong>Địa chỉ</strong></th>
								<th data-sortable="true"><strong>Điện thoại</strong></th>
								<th data-sortable="true"><strong>Email</strong></th>
								<th data-sortable="true"><strong>Ngày đăng ký</strong></th>								
								<th><strong>Chức năng</strong></th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="users" status="i">
								<tr>
									<td><s:property value="#i.count" /></td>
									<td><s:property value="id" /></td>
									<td><s:property value="name" /></td>
									<td><s:property value="address" /></td>
									<td><s:property value="phone" /></td>
									<td><s:property value="email" /></td>
									<td><s:property value="createdAt" /></td>
									
									<td><s:a action="del?id=%{id}" title="Xóa"
											onclick="return window.confirm('Xóa người dùng này ?') ">
											<span class="glyphicon glyphicon-remove"></span>
										</s:a></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!--/.row-->

</div>
<!--/.main-->
<%--/.footer --%>
<s:include value="../layout/footer.jsp" />