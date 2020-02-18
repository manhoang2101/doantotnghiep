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
			<li class="active">Danh mục loại sản phẩm</li>
		</ol>
	</div>
	<!--/.row-->

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					Danh sách loại sản phẩm
					<s:a action="add">
						<button type="button" class="btn btn-primary pull-right">Thêm
							mới loại sản phẩm</button>
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
								<th data-sortable="true"><strong>Mã loại sản phẩm</strong></th>
								<th data-sortable="true"><strong>Tên loại sản phẩm</strong></th>
								<th><strong>Chức năng</strong></th>
							</tr>
						</thead>
						<tbody>
							<s:set var="Id" value="0" />
							<s:iterator value="categories">
								<tr>
									<td><strong><s:property value="id" /></strong></td>
									<s:if test="%{parentId==0 or parentId==null}">
										<td><strong style="color: blue;">- <s:property
													value="name" /></strong></td>
									</s:if>
									<s:elseif test="%{parentId!=0 or parentId!=null}">
										<td style="color: green;">---| --|<s:property
												value="name" /></td>
									</s:elseif>
									<td class="list_td aligncenter"><s:a
											action="edit?id=%{id}" title="Sửa">
											<span class="glyphicon glyphicon-edit"></span>
										</s:a> &nbsp;&nbsp;&nbsp; <s:a action="del?id=%{id}" title="Xóa"
											onclick="return window.confirm('Xóa loại sản phẩm này ?') ">
											<span class="glyphicon glyphicon-remove"></span>
										</s:a></td>
								</tr>
								<s:set var="Id" value="parentId" />
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