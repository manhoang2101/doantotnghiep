package com.poly.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.poly.dao.ProductDAOImpl;
import com.poly.entities.Product;
import com.poly.utils.Cart;

@Namespace("/cart")
@ResultPath("/content")
public class CartAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;

	private int id; // URL parameter: product ID
	private int qty; // URL parameter: new quantity
	private ProductDAOImpl productDAOImpl = new ProductDAOImpl();

	private SessionMap<String, Object> session; // Session

	/*
	 * View
	 */
	@Action(value = "index", results = {
			@Result(name = SUCCESS, location = "cart.jsp")
	})
	public String view() {
		Cart cart = (Cart) session.get("cart");

		if (cart == null || cart.getCount() == 0) {
			session.put("error", "Không có sản phẩm nào trong giỏ hàng!");
		}

		return SUCCESS;
	}

	/*
	 * Add Cart Page
	 */
	@Action(value = "add", results = {
			@Result(name = SUCCESS, location = "index", type = "redirectAction"),
			@Result(name = ERROR, location = "index", type = "redirectAction")
	})
	public String add() {
		Cart cart = (Cart) session.get("cart");

		if (cart == null) {
			cart = new Cart();
		}

		Product product = productDAOImpl.findOne(id);
		if (product == null) {
			return ERROR;
		} else {
			cart.add(product);
			session.put("cart", cart);
			return SUCCESS;
		}
	}

	/*
	 * Edit Category Page
	 */
	@Action(value = "edit", results = {
			@Result(name = SUCCESS, location = "index", type = "redirectAction"),
			@Result(name = ERROR, location = "index", type = "redirectAction")
	})
	public String edit() {
		Cart cart = (Cart) session.get("cart");

		if (cart == null || cart.getCount() == 0) {
			session.put("error", "Your cart is empty");
			return ERROR;
		} else {
			Product product = productDAOImpl.findOne(id);
			if (product == null) {
				return ERROR;
			} else {
				cart.update(product, qty);
				session.put("cart", cart);
				session.put("success", "Update cart successfully.");
				return SUCCESS;
			}
		}
	}

	/*
	 * Delete Category Page
	 */
	@Action(value = "del", results = {
			@Result(name = SUCCESS, location = "index", type = "redirectAction"),
			@Result(name = ERROR, location = "index", type = "redirectAction")
	})
	public String del() {
		Cart cart = (Cart) session.get("cart");

		if (cart == null || cart.getCount() == 0) { // Empty cart
			session.put("error", "Không có sản phẩm nào trong giỏ hàng!");
			return ERROR;
		} else {
			Product product = productDAOImpl.findOne(id);
			if (product == null) { // Product not found
				return ERROR;
			} else {
				cart.remove(id);
				session.put("cart", cart);
				return SUCCESS;
			}
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public void setSession(Map<String, Object> session) {
		this.session = (SessionMap<String, Object>) session;
	}
}
