package com.poly.action.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.poly.dao.OrderDAOImpl;
import com.poly.dao.OrderDetailDAOImpl;
import com.poly.entities.Order;
import com.poly.entities.OrderDetail;
import com.poly.utils.Validate;

@Namespace("/admin/order")
@ResultPath("/content")
@InterceptorRef(value = "authStack")
public class OrderAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private static final long serialVersionUID = 1L;

	private final OrderDAOImpl orderDAOImpl = new OrderDAOImpl();
	private final OrderDetailDAOImpl orderDetailDAOImpl = new OrderDetailDAOImpl();

	private int id; // URL parameter: order ID
	private List<Order> orders;
	private Order order;
	private List<OrderDetail> orderDetails;

	private HttpServletRequest request; // Request
	private SessionMap<String, Object> session; // Session

	public OrderAction() {
		orders = orderDAOImpl.findAll();
	}

	/*
	 * View
	 */
	@Action(value = "index", results = {
			@Result(name = SUCCESS, location = "index.jsp")
	})
	public String view() {
		return SUCCESS;
	}

	/*
	 * Detail Order
	 */
	@Action(value = "detail", results = {
			@Result(name = INPUT, location = "detail.jsp")
	})
	public String detail() {
		// create var row
		Order row = null;
		// check row exist
		if ((row = orderDAOImpl.findOne(id)) == null) {
			session.put("error", "Giỏ hàng không tồn tại.");
			return SUCCESS;
		}

		// if get method POST
		if ("POST".equals(request.getMethod())) {
			if (order != null) {
				// merge
				Validate.merge(row, order);
				// update order
				if (orderDAOImpl.update(row)) {
					session.put("success", "Thành công");
				} else
					session.put("error", "Thất bại");
			}
		}

		// if get method POST
		// set model for view
		// set one row order by id
		order = row;
		// set all row detail_order by order_id
		orderDetails = new ArrayList<OrderDetail>(row.getOrderDetails());

		return INPUT;
	}

	/*
	 * Delete Order
	 */
	@Action(value = "del", results = {
			@Result(name = SUCCESS, location = "index", type = "redirectAction")
	})
	public String del() {
		order = orderDAOImpl.findOne(id);
		// check row exist
		if (order == null) {
			session.put("error", "Giỏ hàng không tồn tại.");
			return SUCCESS;
		}

		// delete all order_detail by order_id
		for (OrderDetail orderDetail : order.getOrderDetails()) {
			orderDetailDAOImpl.delete(orderDetail.getId());
		}

		// delete order
		if (orderDAOImpl.delete(order.getId())) {
			session.put("success", "Xóa thành công.");
		} else
			session.put("error", "Xóa thất bại.");

		return SUCCESS;
	}

	private String URL;

	public String getURL() {
		return URL;
	}

	/*
	 * Delete Detail Order
	 */
	@Action(value = "del-pro", results = {
			@Result(name = SUCCESS, location = "${URL}", type = "redirect"),
	})
	public String del_pro() {
		// set back url detail_order
		URL = request.getHeader("referer");

		// delete detail_order
		if (orderDetailDAOImpl.delete(id)) {
			session.put("success", "Xóa thành công.");
		} else
			session.put("error", "Xóa thất bại.");

		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setSession(Map<String, Object> session) {
		this.session = (SessionMap<String, Object>) session;
	}
}
