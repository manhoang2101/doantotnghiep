<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%--/.header --%>
<s:include value="layout/header.jsp" />
<!--/.main content -->
<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
	<div class="row man8" style="width: 80%; margin-top: 1em; margin-left: -3px;">
		<ol class="breadcrumb">
			<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
			<li class="active">Hồ sơ</li>
		</ol>
	</div>
	<!--/.row-->

	<div class="row">
		<div class="col-lg-12 man7" style="width: 80%;">
			<div class="panel panel-default">
				<div class="panel-heading">Thông tin chi tiết</div>
				<div class="panel-body">
					<s:url var="currentUrl" includeParams="all" />
					<s:form action="%{currentUrl}" includeContext="false" role="form">
						<div class="col-md-6">

							<div class="form-group">
								<label>Họ tên</label>
								<s:fielderror fieldName="user.name" />
								<s:textfield name="user.name" class="form-control" />
							</div>

							<div class="form-group">
								<label>Email</label>
								<s:fielderror fieldName="user.email" />
								<s:textfield type="email" name="user.email"
									class="form-control" />
							</div>

							<div class="form-group">
								<label>Số điện thoại</label>
								<s:fielderror fieldName="user.phone" />
								<s:textfield name="user.phone" class="form-control" />
							</div>

							<div class="form-group">
								<label>Địa chỉ</label>
								<s:fielderror fieldName="user.address" />
								<s:textfield name="user.address" class="form-control" />
							</div>
						</div>

						<div class="col-md-6">

							<div class="form-group">
								<label>Mật khẩu cũ</label>
								<s:fielderror fieldName="oldpassword" />
								<s:textfield type="password" name="oldpassword"
									class="form-control" />
							</div>

							<div class="form-group">
								<label>Mật khẩu mới</label>
								<s:fielderror fieldName="password" />
								<s:textfield type="password" name="password"
									class="form-control" />
							</div>

							<div class="form-group">
								<label>Nhập lại mật khẩu</label>
								<s:fielderror fieldName="repassword" />
								<s:textfield type="password" name="repassword"
									class="form-control" />
							</div>
							<br>
							<button type="submit" class="btn btn-success">Submit</button>
							<button type="reset" class="btn btn-secondary" style="padding: 11px 20px;">Reset</button>
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
<s:include value="layout/footer.jsp" />