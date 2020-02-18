   <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Đăng nhập</title>

<!-- Fonts -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css"
	integrity="sha384-XdYbMnZ/QjLh6iI4ogqCTaIjrFk87ip+ekIjefZch0Y+PvJ8CDYtEs1ipDmPorQ+"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700">

<!-- Styles -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<style>
body {
	font-family: 'Lato';
}

.fa-btn {
	margin-right: 6px;
}
</style>
</head>
<body id="app-layout">
	<nav class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<s:a action="index" namespace="" class="navbar-brand">
				<span class="glyphicon glyphicon-chevron-left"></span> Quay về trang chủ</s:a>
			</a>
		</div>
	</div>
	</nav>

	<div class="container">
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<div class="panel panel-default">
					<div class="panel-heading">Đăng nhập Admin</div>
					<div class="panel-body">
						<s:fielderror />
						<s:form action="login" class="form-horizontal">
							<div class="form-group">
								<label for="email" class="col-md-4 control-label">E-Mail
									Address</label>

								<div class="col-md-6">
									<s:textfield name="adminUser.email" type="email"
									 class="form-control" />

								</div>
							</div>

							<div class="form-group">
								<label for="password" class="col-md-4 control-label">Mật
									khẩu</label>

								<div class="col-md-6">
									<s:textfield name="adminUser.password" type="password"
										 class="form-control" />

								</div>
							</div>

							<div class="form-group">
								<div class="col-md-6 col-md-offset-4">
									<div class="checkbox">
										<label> <input type="checkbox" name="remember">
											Ghi nhớ
										</label>
									</div>
								</div>
							</div>

							<div class="form-group">
								<div class="col-md-6 col-md-offset-4">
									<button type="submit" class="btn btn-primary">
										<i class="fa fa-btn fa-sign-in"></i> Đăng nhập
									</button>

									<s:a action="password/reset" class="btn btn-link">Bạn đã
										quên mật khẩu?</s:a>
								</div>
							</div>
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- JavaScripts -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.3/jquery.min.js"
		integrity="sha384-I6F5OKECLVtK/BL+8iSLDEHowSAfUo76ZL9+kGAgTRdiByINKJaqTPH/QVNS1VDb"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js"
		integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
		crossorigin="anonymous"></script>


</body>
</html>