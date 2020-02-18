<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%--/.header --%>
<s:include value="layout/header.jsp" />
<%--/.menu --%>
<s:include value="layout/menu.jsp" />
<!-- main -->
<div class="single-product-area">
	<div class="zigzag-bottom"></div>
	<div class="container">
		<div class="row">
			<%--/.left product --%>
			<s:include value="layout/left-product.jsp" />
			<div class="col-md-9">
				<div class="product-content-right">
					<div class="product-breadcroumb">
						<a href="#">Trang chủ</a> 
						<s:a value="products?id=%{product.category.id}">
						<s:property value="%{product.category.name}" />
						</s:a>			
						<a><s:property value="%{product.name}" /></a>
					</div>

					<div class="row">
						<div class="col-sm-6">
							<div class="product-images">
								<div class="product-main-img">
									<img src="uploads/<s:property value="%{product.images}" />"
										alt="<s:property value="%{product.images}" />">
								</div>

								<div class="product-gallery">
									<s:iterator value="product.detailImgs">
										<img src="uploads/<s:property value="%{imagesUrl}" />"
											alt="<s:property value="%{imagesUrl}" />">
									</s:iterator>
								</div>
							</div>
						</div>

						<div class="col-sm-6">
							<div class="product-inner">
								<h2 class="product-name">
									<s:property value="%{product.name}" />
								</h2>
								<div class="product-inner-price">
									<ins>
										<s:property value="getText('{0,number,#,##0}',{product.price})"/> VNĐ
									</ins>
								</div>
								
								
								
								<s:url var="currentUrl">
									<s:param name="id" value="%{id}" />
								</s:url>
								<s:form action="%{currentUrl}" includeContext="false"
									class="cart">
									<div class="quantity">
										<input type="number" size="4" class="input-text qty text"
											title="Số lượng" value="1" name="qty" min="1" step="1">
									</div>
									<button class="add_to_cart_button" type="submit">Thêm vào giỏ hàng</button>
								</s:form>
								
								

								<div class="product-inner-category">
									<p>
										Loại sản phẩm:
										<s:a value="products?id=%{product.category.id}">
											<s:property value="%{product.category.name}" />
										</s:a>
										. Tags:
										<s:a value="products?id=%{product.id}">
											<s:property value="%{product.tag}" />
										</s:a>
										.

									</p>
								</div>

								<div role="tabpanel">
									<ul class="product-tab" role="tablist">
										<li role="presentation" class="active"><a href="#home"
											aria-controls="home" role="tab" data-toggle="tab">Mô tả</a></li>
										<li role="presentation"><a href="#detail"
											aria-controls="detail" role="tab" data-toggle="tab">Cấu hình chi tiết</a></li>
										<li role="presentation"><a href="#profile"
											aria-controls="profile" role="tab" data-toggle="tab">Đánh giá</a></li>
									</ul>
									<div class="tab-content">
										<div role="tabpanel" class="tab-pane fade in active" id="home">
											<h2>Mô tả sản phẩm</h2>
											<p>
												<s:property value="%{product.review}" />
											</p>
										</div>
										<div role="tabpanel" class="tab-pane fade" id="detail">
											<h2>Cấu hình chi tiết của sản phẩm</h2>
											<table class="table table-striped">
												<s:iterator value="%{product.proDetails}">
													<tr>
														<td>Kích Thước</td>
														<td><s:property value="%{cpu}" /></td>
													</tr>
													<tr>
														<td>Màu Sắc</td>
														<td><s:property value="%{ram}" /></td>
													</tr>
													<tr>
														<td>Số Lượng</td>
														<td><s:property value="%{storage}" /></td>
													</tr>
												</s:iterator>
											</table>
										</div>
										<div role="tabpanel" class="tab-pane fade" id="profile">
											<h2>Đánh giá sản phẩm</h2>
											<div class="submit-review">
												<p>
													<label for="name">Họ và tên</label> <input name="name"
														type="text">
												</p>
												<p>
													<label for="email">E-Mail</label> <input name="email"
														type="email">
												</p>
												<div class="rating-chooser">
													<p>Chất lượng</p>

													<div class="rating-wrap-post">
														<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
															class="fa fa-star"></i> <i class="fa fa-star"></i> <i
															class="fa fa-star"></i>
													</div>
												</div>
												<p>
													<label for="review">Đánh giá</label>
													<textarea name="review" id="" cols="30" rows="10"></textarea>
												</p>
												<p>
													<input type="submit" value="Đánh giá">
												</p>
											</div>
										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- End main content area -->
<%--/.footer --%>
<s:include value="layout/footer.jsp" />