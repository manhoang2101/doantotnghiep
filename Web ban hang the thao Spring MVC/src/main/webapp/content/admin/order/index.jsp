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
			<li class="active">Quản lý đơn hàng</li>
		</ol>
	</div>
	<!--/.row-->

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Danh sách đơn đặt hàng</div>
				<div class="panel-body">
					<table data-toggle="table" data-show-refresh="true"
						data-show-toggle="true" data-show-columns="true"
						data-search="true" data-select-item-name="toolbar1"
						data-pagination="true" data-sort-name="name"
						data-sort-order="desc">
						<thead>
							<tr>
								<th data-sortable="true"><strong>STT</strong></th>
								<th data-sortable="true"><strong>Mã đơn hàng</strong></th>
								<th data-sortable="true"><strong>Tên khách hàng</strong></th>
								<th data-sortable="true"><strong>Địa chỉ</strong></th>
								<th data-sortable="true"><strong>Điện thoại</strong></th>
								<th data-sortable="true"><strong>Email</strong></th>
								<th data-sortable="true"><strong>Ngày đặt</strong></th>
								<th data-sortable="true"><strong>Thành tiền</strong></th>
								<th><strong>Trạng thái</strong></th>
								<th><strong>Chi tiết</strong></th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="orders" status="i">
								<tr>
									<td><s:property value="#i.count" /></td>
									<td><s:property value="id" /></td>
									<td><s:property value="user.name" /></td>
									<td><s:property value="user.address" /></td>
									<td><s:property value="user.phone" /></td>
									<td><s:property value="user.email" /></td>
									<td><s:property value="createdAt" /></td>
									<td><s:property
											value="getText('{0,number,#,##0}',{total})" /> đ</td>
											
									<td>
									<s:if test="%{status==1}">
											<span style="color: #27ae60;">Đã xác nhận</span>																						
									</s:if>			
									<s:elseif test="%{status==2}">
											<span style="color: red;">Đơn hàng đã bị hủy</span>
									</s:elseif>	
									<s:else>
											<span style="color: Orange;">Chưa xác nhận</span>
									</s:else>
									</td>
									<td><s:a action="detail?id=%{id}" title="Xem chi tiết">
											<span class="glyphicon glyphicon-search"></span>
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