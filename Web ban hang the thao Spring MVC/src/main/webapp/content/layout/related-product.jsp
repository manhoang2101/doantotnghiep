<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="related-products-wrapper">
	<h2 class="related-products-title">Các sản phẩm khác</h2>	
	<div class="related-products-carousel">	
	<s:iterator value="products">
							<div class="single-product">
								<div class="product-f-image" style="height: 285px;width: 235px;">
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