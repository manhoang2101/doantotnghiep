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
			<li class="active">Đơn hàng</li>
		</ol>
	</div>
	<!--/.row-->

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Thông tin đơn hàng</div>
				<%--error display --%>
				<s:include value="../layout/message.jsp" />
				<div class="panel-body">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Mã khách hàng</th>
								<th>Tên khách hàng</th>
								<th>Địa chỉ</th>
								<th>Điện thoại</th>
								<th>Ngày đặt</th>
								<th>Tổng tiền</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><s:property value="order.user.id" /></td>
								<td><s:property value="order.user.name" /></td>
								<td><s:property value="order.user.address" /></td>
								<td><s:property value="order.user.phone" /></td>
								<td><s:property value="order.createdAt" /></td>
								<td><s:property
										value="getText('{0,number,#,##0}',{order.total})" /> đ</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="panel-heading">Chi tiết sản phẩm trong đơn đặt
					hàng</div>
				<div class="panel-body" style="font-size: 12px;">
					<div class="table-responsive">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>Mã đơn hàng</th>
									<th>Hình ảnh</th>
									<th>Tên sản phẩm</th>
									<th>Tóm tắt chức năng</th>
									<th>Số lượng</th>
									<th>Giá bán</th>
									<th>Trạng thái</th>
									
								</tr>
							</thead>
							<tbody>
								<s:iterator value="orderDetails">
									<tr>
										<td><s:property value="order.id" /></td>
										<td><img
											src="uploads/<s:property value="product.images" />"
											alt="<s:property value="product.images" />" width="50"
											height="40"></td>
										<td><s:property value="product.name" /></td>
										<td><s:property value="product.intro" /></td>
										<td><s:property value="qty" /></td>
										<td><s:property
												value="getText('{0,number,#,##0}',{product.price})" /> đ</td>
										<td><s:if test="%{product.status==1}">
												<span style="color: green;">Còn hàng</span>
											</s:if> <s:else>
												<span style="color: red;">Tạm hết</span>
											</s:else></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			
			<table><tr><td style="padding-right: 20px">
			<s:if test="%{order.status!=1}">
				<s:url var="currentUrl">
					<s:param name="id" value="%{id}" />
				</s:url>
				<s:form action="%{currentUrl}" includeContext="false">
					<s:hidden name="order.status" value="1" />
					<s:submit value="Xác nhận đơn hàng" class="btn btn-danger"
						onclick="return xacnhan('Xác nhận đơn hàng
					này ?')" />
				</s:form>
			</s:if>
			</td>
			<td>
			<s:if test="%{order.status!=2}">
				<s:url var="currentUrl">
					<s:param name="id" value="%{id}" />
				</s:url>
				<s:form action="%{currentUrl}" includeContext="false">
					<s:hidden name="order.status" value="2" />
					<s:submit value="Hủy đơn hàng" class="btn btn-danger"
						onclick="return window.confirm('Hủy đơn hàng này ?')" />
				</s:form>
			 </s:if>
			</td></tr></table>
			
			
		</div>
	</div>
	<!--/.row-->

</div>
<!--/.main-->
<%--/.footer --%>
<s:include value="../layout/footer.jsp" />