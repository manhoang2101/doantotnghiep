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
				<div class="panel-heading">Thêm mới loại sản phẩm</div>
				<div class="panel-body">
					<s:form action="add" role="form">
						<div class="col-md-6">

							<div class="form-group">
								<label>Danh mục</label>
								<s:fielderror fieldName="category.parentId" />
								<s:select headerKey="0" headerValue="--Chọn--" list="categories"
									listKey="id" listValue="name" name="category.parentId"
									class="form-control" />
							</div>

							<div class="form-group">
								<label>Tên loại sản phẩm (*)</label>
								<s:fielderror style="color: red;" fieldName="category.name" />
								<s:textfield name="category.name" class="form-control" />
							</div>

							<button type="submit" class="btn btn-primary">Thêm mới</button>
							<button type="reset" class="btn btn-default">Nhập lại</button>
						</div>
					</s:form>
				</div>
			</div>
		</div>
		<!-- /.col-->
	</div>
	<!-- /.row -->

</div>
<!--/.main-->
<%--/.footer --%>
<s:include value="../layout/footer.jsp" />