package com.poly.action.admin;



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
import com.poly.dao.UserDAOImpl;
import com.poly.entities.User;
import com.poly.utils.Validate;


@Namespace("/admin/customer")
@ResultPath("/content")
@InterceptorRef(value = "authStack")
public class CustomerAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private static final long serialVersionUID = 1L;

	private final UserDAOImpl userDAOImpl = new UserDAOImpl();

	private int id; // URL parameter: user ID
	private List<User> users;
	private User user;
	private String password;

	private HttpServletRequest request; // Request
	private SessionMap<String, Object> session; // Session

	public CustomerAction() {
		// set model for view
		// set all row user
		users = userDAOImpl.findAll();
	}

	/*
	 * View
	 */
	@Action(value = "index", results = { @Result(name = SUCCESS, location = "index.jsp") })
	public String view() {
		return SUCCESS;
	}

	
	/*
	 * Delete User
	 */
	@Action(value = "del", results = { @Result(name = SUCCESS, location = "index", type = "redirectAction") })
	public String del() {
		// delete user
		if (userDAOImpl.delete(id)) {
			session.put("success", "Xóa thành công!");
		} else
			session.put("error", "Xóa Thất Bại!");

		return SUCCESS;
	}

	/*
	 * Validate
	 */
	@Override
	public void validate() {
		if (user != null) {
			// check name
			if (user.getName() != null && user.getName().isEmpty()) {
				this.addFieldError("user.name", "Họ tên không được để trống.");
			}
			// check phone
			if (user.getPhone() != null) {
				if (user.getPhone().isEmpty())
					this.addFieldError("user.phone", "Số điện thoại không được để trống.");
				else if (!Validate.checkNumberPhone(user.getPhone()))
					this.addFieldError("user.phone", "Số điện thoại khoog hợp lệ.");
			}
			// check address
			if (user.getAddress() != null && user.getAddress().isEmpty()) {
				this.addFieldError("user.address", "Địa chỉ khách hàng không được để trống.");
			}
			// check email
			if (user.getEmail() != null) {
				User obj = userDAOImpl.findOne(id);
				boolean bool = true;
				if (obj != null && obj.getEmail().equals(user.getEmail()))
					bool = false;

				if (!Validate.isValidEmailAddress(user.getEmail()))
					this.addFieldError("user.email", "Email không hợp lệ.");
				else if (user.getEmail().isEmpty())
					this.addFieldError("user.email", "Email không được để trống.");
				else if (!userDAOImpl.find(user.getEmail()).isEmpty() && bool)
					this.addFieldError("user.email", "Email đã có trong hệ thống.");
			}
			// check password
			if (password != null && password.isEmpty()) {
				this.addFieldError("password", "Mật khẩu không được để trống.");
			}
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setSession(Map<String, Object> session) {
		this.session = (SessionMap<String, Object>) session;
	}
}
