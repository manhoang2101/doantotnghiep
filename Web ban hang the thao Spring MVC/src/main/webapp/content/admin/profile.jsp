<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%--/.header --%>
<s:include value="layout/header.jsp" />
<!--/.main content -->
<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
			<li class="active">Hồ sơ</li>
		</ol>
	</div>
	<!--/.row-->

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Thông tin chi tiết</div>
				<div class="panel-body">
					<s:url var="currentUrl" includeParams="all" />
					<s:form action="%{currentUrl}" includeContext="false" role="form">
						<div class="col-md-6">

							<div class="form-group">
								<label>Họ tên</label>
								<s:fielderror fieldName="adminUser.name" />
								<s:textfield name="adminUser.name" class="form-control" />
							</div>

							<div class="form-group">
								<label>Email</label>
								<s:fielderror fieldName="adminUser.email" />
								<s:textfield type="email" name="adminUser.email"
									class="form-control" />
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