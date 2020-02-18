package com.poly.action.admin;

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
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.poly.dao.AdminDAOImpl;
import com.poly.entities.AdminUser;

@Namespace("/admin")
@ResultPath("/content")
public class AuthAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private static final long serialVersionUID = 1L;

	private final AdminDAOImpl adminDAOImpl = new AdminDAOImpl();

	private AdminUser adminUser;

	private HttpServletRequest request; // Request
	private SessionMap<String, Object> session;// Session

	/*
	 * Login
	 */
	@Action(value = "login", results = {
			@Result(name = SUCCESS, location = "index", type = "redirectAction"),
			@Result(name = INPUT, location = "login.jsp")
	}, interceptorRefs = {
			@InterceptorRef(value = "authStack")
	})
	public String login() {
		// if get method POST
		if ("POST".equals(request.getMethod())) {
			adminUser = adminDAOImpl.findOne(adminUser.getEmail(),adminUser.getPassword());
			if (adminUser != null) {
				// save in session
				session.put("a_user", adminUser);
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
		session.remove("a_user");
		return SUCCESS;
	}

	/*
	 * Password Reset
	 */
	@Action(value = "password/reset", results = {
			@Result(name = SUCCESS, location = "login.jsp")
	})
	public String password_reset() {
		return SUCCESS;
	}

	/*
	 * Validate
	 */
	@Override
	public void validate() {
		if (adminUser != null) {
			// check email
			if (adminUser.getEmail() != null && adminUser.getEmail().isEmpty()) {
				this.addFieldError("adminUser.email", "Email không được để trống.");
			}
			// check password
			if (adminUser.getPassword() != null && adminUser.getPassword().isEmpty()) {
				this.addFieldError("adminUser.password", "Password không được để trống.");
			}
			// check user exist?
			if (adminUser.getEmail() != null && adminUser.getPassword() != null) {
				if (!adminUser.getPassword().isEmpty())
					if (adminDAOImpl.findOne(adminUser.getEmail(), adminUser.getPassword()) == null)
						this.addFieldError("adminUser", "Tài khoản hoặc mật khẩu không chính xác.");
			}
		}
	}

	@VisitorFieldValidator
	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setSession(Map<String, Object> session) {
		this.session = (SessionMap<String, Object>) session;
	}
}
