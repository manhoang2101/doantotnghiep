<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%-- header --%>
<!--nva  -->
<s:include value="layout/header.jsp" />
<!-- menu -->
<s:include value="layout/menu.jsp" />
<!-- slide -->
<s:include value="layout/slide.jsp" />
<!-- promo -->
<s:include value="layout/promo.jsp" />
<!-- main -->
<div class="maincontent-area">
	<div class="zigzag-bottom"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="latest-product">
					<h2 class="section-title" style="font-family:serif;">Danh sách sản phẩm</h2>
					
					<div class="product-carousel">						
						<s:iterator value="products">
							<div class="single-product">
								<div class="product-f-image" style="height: 250px; width:100%;">
									<img src="uploads/<s:property value="%{images}" />" alt="">
									<div class="product-hover">
										<s:a action="add?id=%{id}" namespace="/cart"
											class="add-to-cart-link">
											<i class="fa fa-shopping-cart"></i> Thêm vào giỏ hàng
										</s:a>
										<s:a action="detail?id=%{id}" class="view-details-link">
											<i class="fa fa-link"></i> Xem chi tiết
										</s:a>
									</div>
								</div>

								<h2>
									<s:property value="%{name}" />
								</h2>

								<div class="product-carousel-price">
									<ins>
										<s:property value="getText('{0,number,#,##0}',{price})"/> VNĐ
									</ins>
								</div>
							</div>
						</s:iterator>
					</div>
					
					
				</div>
			</div>
		</div>
	</div>
</div>
<!-- End main content area -->
<!-- brand -->
<s:include value="layout/brand.jsp" />
<!-- product-widget -->
<s:include value="layout/product-widget.jsp" />
<!-- footer -->
<s:include value="layout/footer.jsp" />