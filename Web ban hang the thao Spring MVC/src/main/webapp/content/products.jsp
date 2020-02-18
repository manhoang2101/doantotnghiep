<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%--/.header --%>
<s:include value="layout/header.jsp" />
<%--/.menu --%>
<s:include value="layout/menu.jsp" />
<!-- main -->								
<div class="single-product-area">	
	<div class="container" >
	<div class="row">
	
<div class="col-md-3 mancategory">
<h2>DANH MỤC</h2>
<h3>---------------------------</h3>
<ul style="list-style-type:circle;">
  <li><s:a action="products?id=1" namespace="">Adidas</s:a></li>
  <li><s:a action="products?id=2" namespace="">Nike</s:a></li>
  <li><s:a action="products?id=3" namespace="">Converse</s:a></li>
  <li><s:a action="products?id=4" namespace="">Puma's</s:a></li>
  <li><s:a action="products?id=11" namespace="">Vans</s:a></li>
  <li><s:a action="products?id=12" namespace="">Gucci</s:a></li>
  <li><s:a action="products?id=13" namespace="">Balenciaga</s:a></li>
  <li><s:a action="products?id=14" namespace="">New Balance</s:a></li>
</ul>
		</div>		
		
			<s:iterator value="products">
					<div class="col-md-3 manproduct">				
					<div class="single-shop-product custom-product">
					<div class="card">
					 <div class="product-upper">
							<img src="uploads/<s:property value="%{images}" />" alt="">
					 </div>
						<h2>
							<s:a action="detail?id=%{id}">
								<s:property value="%{name}" />
							</s:a>
						</h2>
						<div class="product-carousel-price">
							<ins>
							<s:property value="getText('{0,number,#,##0}',{price})"/> VNĐ
							</ins>
						 </div>

						<div class="product-option-shop custom-button">
							<s:a class="add_to_cart_button" data-quantity="1"
								data-product_sku="" data-product_id="70" rel="nofollow"
								action="add?id=%{id}" namespace="/cart">Thêm vào giỏ hàng</s:a>
						</div>
					</div>
					</div>
					
				</div>
			</s:iterator>			
		</div>
</div>
</div>
<!-- End main content area -->
<%--/.footer --%>
<s:include value="layout/footer.jsp" />