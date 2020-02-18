package com.poly.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.poly.dao.OrderDAOImpl;
import com.poly.dao.OrderDetailDAOImpl;
import com.poly.dao.UserDAOImpl;
import com.poly.entities.Order;
import com.poly.entities.OrderDetail;
import com.poly.entities.User;
import com.poly.utils.Cart;

@Namespace("/cart")
@ResultPath("/content")
public class CheckoutAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;

	private final OrderDAOImpl orderDAOImpl = new OrderDAOImpl();
	private final OrderDetailDAOImpl orderDetailDAOImpl = new OrderDetailDAOImpl();
	private final UserDAOImpl userDAOImpl = new UserDAOImpl();

	private int id; // URL parameter: product ID
	private int qty[]; // URL parameter: qty

	private SessionMap<String, Object> session; // Session

	/*
	 * View
	 */
	@Action(value = "checkout", results = { @Result(name = SUCCESS, location = "index", type = "redirectAction"),
			@Result(name = ERROR, location = "/login.html", type = "redirect"),
			@Result(name = INPUT, location = "cart.jsp") })
	public String view() {
		Cart cart = (Cart) session.get("cart");
		User user = (User) session.get("user");

		
		// test user
		// user = new UserDAOImpl().findOne(1);

		if (cart == null || cart.getCount() == 0) {
			session.put("error", "Không có sản phẩm nào trong giỏ hàng! ");
			return ERROR;
		} else if (user == null || userDAOImpl.findOne(user.getId()) == null) {
			session.put("error", "Vui lòng đăng nhập trước khi mua hàng! ");
			return ERROR;
		} else {
			// update
			for (OrderDetail orderDetail : cart.getOrderDetails()) {
				cart.update(orderDetail.getProduct(), qty[cart.getOrderDetails().indexOf(orderDetail)]);
				
			}
			Order order = new Order();
			order.setUser(user);
			order.setTotal(Float.valueOf(cart.getTotal()));
			// save order
			if (orderDAOImpl.insert(order)) {
				for (OrderDetail orderDetail : cart.getOrderDetails()) {
					orderDetail.setOrder(order);
					orderDetailDAOImpl.insert(orderDetail);
				}
				session.remove("cart");
				session.put("success", " Mua hàng thành công! Chúng tôi sẽ liên hệ với bạn sớm nhất.");
			}
		}

		return SUCCESS;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int[] getQty() {
		return qty;
	}

	public void setQty(int[] qty) {
		this.qty = qty;
	}

	public void setSession(Map<String, Object> session) {
		this.session = (SessionMap<String, Object>) session;
	}
}
