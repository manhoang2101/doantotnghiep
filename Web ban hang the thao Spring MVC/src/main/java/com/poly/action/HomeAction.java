package com.poly.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.poly.dao.CategoryDAOImpl;
import com.poly.dao.ProductDAOImpl;
import com.poly.dao.UserDAOImpl;
import com.poly.entities.Category;
import com.poly.entities.Product;
import com.poly.entities.User;
import com.poly.utils.Cart;

@Namespace("/")
@ResultPath("/content")
public class HomeAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private static final long serialVersionUID = 1L;

	private final CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();
	private final ProductDAOImpl productDAOImpl = new ProductDAOImpl();

	
	private final UserDAOImpl userDAOImpl = new UserDAOImpl();
	private User user;
	
	
	private int id; // URL parameter: product ID
	private int qty; // URL parameter: qty
	private List<Product> products;
	private Category category;
	private Product product;

	private HttpServletRequest request; // Request
	private SessionMap<String, Object> session; // Session

	/*
	 * Home Page
	 */
	@Action(value = "index", results = { @Result(name = SUCCESS, location = "index.jsp") })
	public String index() {
		products = productDAOImpl.findAll();
		return SUCCESS;
	}
	@Action(value = "profile", results = {
			@Result(name = SUCCESS, location = "profile.jsp"),
			@Result(name = ERROR, location = "index", type = "redirectAction")
	})
	public String profile() {
		User obj = (User) session.get("user");
		user = userDAOImpl.findOne(obj.getId());
		if (user == null) {
			return ERROR;
		}
		return SUCCESS;
	}

	/*
	 * Detail Product
	 */
	@Action(value = "detail", results = { @Result(name = INPUT, location = "detail.jsp"),
			@Result(name = ERROR, location = "index", type = "redirectAction") })
	public String detail() {
		// check row exist
		if ((product = productDAOImpl.findOne(id)) == null) {
			return ERROR;
		}

		// if get method POST
		if ("POST".equals(request.getMethod())) {
			Cart cart = (Cart) session.get("cart");

			if (cart == null)
				cart = new Cart();

			if (product != null) {
				cart.update(product, qty);
				// cart.add(product);
				// save session
				session.put("cart", cart);
				session.put("success", "Thêm thành công vào giỏ hàng.");
			}
		}

		return INPUT;
	}

	/*
	 * List Product
	 */
	@Action(value = "products", results = { @Result(name = SUCCESS, location = "products.jsp"),
			@Result(name = ERROR, location = "index", type = "redirectAction") })
	public String list() {
		
		if ((category = categoryDAOImpl.findOne(id)) == null) {
			return ERROR;
		}

		// set list product for view
		products = new ArrayList<Product>(category.getProducts());

		return SUCCESS;
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setSession(Map<String, Object> session) {
		this.session = (SessionMap<String, Object>) session;
	}
}
