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
			<li class="active">Sản phẩm</li>
		</ol>
	</div>
	<!--/.row-->

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					Danh sách sản phẩm
					<s:a action="add">
						<button type="button" class="btn btn-primary pull-right">Thêm
							mới sản phẩm</button>
					</s:a>
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
								<th data-sortable="true"><strong>ID</strong></th>
								<th data-sortable="true"><strong>Hình ảnh</strong></th>
								<th data-sortable="true"><strong>Tên sản phẩm</strong></th>
								<th data-sortable="true"><strong>Tóm tắt chức năng</strong></th>
								<th data-sortable="true"><strong>Thương hiệu</strong></th>
								<th data-sortable="true"><strong>Giá bán</strong></th>
								<th><strong>Trạng thái</strong></th>
								<th><strong>Action</strong></th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="products">
								<tr>
									<td><s:property value="id" /></td>
									<td><img src="uploads/<s:property value="images" />"
										alt="<s:property value="images" />" width="50" height="40"></td>
									<td><s:property value="name" /></td>
									<td><s:property value="intro" /></td>
									<td><s:property value="category.name" /></td>
									<td><s:property
											value="getText('{0,number,#,##0}',{price})" /> VNĐ</td>
									<td><s:if test="%{status==1}">
											<span style="color: green;">Còn hàng</span>
										</s:if> <s:else>
											<span style="color: red;">Tạm hết</span>
										</s:else></td>
									<td><s:a action="edit?id=%{id}" title="Sửa">
											<span class="glyphicon glyphicon-edit"></span>
										</s:a> &nbsp;&nbsp;&nbsp; <s:a action="del?id=%{id}" title="Xóa"
											onclick="return window.confirm('Xóa sản phẩm này ?') ">
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