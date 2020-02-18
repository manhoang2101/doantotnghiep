package com.poly.action;



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
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.poly.dao.UserDAOImpl;
import com.poly.entities.User;


@Namespace("/")
@ResultPath("/content")
public class LoginAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private static final long serialVersionUID = 1L;

	private final UserDAOImpl userDAOImpl = new UserDAOImpl();

	private User user;

	private HttpServletRequest request; // Request
	private SessionMap<String, Object> session;// Session

	/*
	 * Login
	 */
	@Action(value = "login", results = {
			@Result(name = SUCCESS, location = "index", type = "redirectAction"),
			@Result(name = INPUT, location = "login.jsp")
	})
	public String login() {
		// if get method POST
		if ("POST".equals(request.getMethod())) {
			user = userDAOImpl.findOne(user.getEmail(), user.getPassword());
			if (user != null) {
				// save in session
				session.put("user", user);
				return SUCCESS;
			}
		}

		return INPUT;
	}

	/*
	 * Logout
	 */
	@Action(value = "logout", results = {
			@Result(name = SUCCESS, location = "login", type = "redirectAction")
	})
	public String logout() {
		// remove session
		session.remove("user");
		return SUCCESS;
	}

	/*
	 * Validate
	 */
	@Override
	public void validate() {
		if (user != null) {
			// check email
			if (user.getEmail() != null && user.getEmail().isEmpty()) {
				this.addFieldError("user.email", "Email không được để trống.");
			}
			// check password
			if (user.getPassword() != null && user.getPassword().isEmpty()) {
				this.addFieldError("user.password", "Password không được để trống.");
			}
			// check user exist?
			if (user.getEmail() != null && user.getPassword() != null) {
				if (!user.getPassword().isEmpty())
					if (userDAOImpl.findOne(user.getEmail(),user.getPassword()) == null)
						this.addFieldError("user", "Tài khoản hoặc mật khẩu không chính xác.");
			}
		}
	}

	@VisitorFieldValidator
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setSession(Map<String, Object> session) {
		this.session = (SessionMap<String, Object>) session;
	}
}
