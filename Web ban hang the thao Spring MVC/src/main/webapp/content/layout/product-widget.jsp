<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="product-widget-area">
	<div class="zigzag-bottom"></div>
	<div class="container">
	<div class="row">		
	<s:iterator value="products">
				<div class="col-md-3 col-sm-6">				
					<div class="single-shop-product">
					<div class="maincard">
						<div class="product-thumb">
							<img src="uploads/<s:property value="%{images}" />" alt="">
						</div>	
						<div>
							<s:a action="detail?id=%{id}">
								<s:property value="%{name}" />
							</s:a>
						</div>
						<div class="product-wid-rating">
							<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
								class="fa fa-star"></i> <i class="fa fa-star"></i> <i
								class="fa fa-star"></i>
						</div>
						<div class="product-wid-price">
							<s:property value="getText('{0,number,#,##0}',{price})"/> VNĐ
						</div>
					</div>
					</div>
					</div>							
	</s:iterator>
		</div>
	</div>
</div>
<!-- End product widget area -->