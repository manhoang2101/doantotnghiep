package com.poly.action.admin;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.poly.dao.AdminDAOImpl;
import com.poly.dao.CategoryDAOImpl;
import com.poly.entities.AdminUser;
import com.poly.entities.Category;

@Namespace("/admin")
@ResultPath("/content")
@InterceptorRef(value = "authStack")
public class HomeAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;

	private final AdminDAOImpl adminDAOImpl = new AdminDAOImpl();
	private final CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();

	private List<Category> categories;
	private AdminUser adminUser;

	private SessionMap<String, Object> session; // Session

	/*
	 * Home Page
	 */
	@Action(value = "index", results = {
			@Result(name = SUCCESS, location = "index.jsp")
	})
	public String view() {
		// set model for view
		// set all row category
		categories = categoryDAOImpl.findAll();
		return SUCCESS;
	}

	/*
	 * Home Page
	 */
	@Action(value = "profile", results = {
			@Result(name = SUCCESS, location = "profile.jsp"),
			@Result(name = ERROR, location = "index", type = "redirectAction")
	})
	public String profile() {
		AdminUser obj = (AdminUser) session.get("a_user");
		adminUser = adminDAOImpl.findOne(obj.getId());
		if (adminUser == null) {
			return ERROR;
		}
		return SUCCESS;
	}

	@Override
	public void validate() {
			}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public void setSession(Map<String, Object> session) {
		this.session = (SessionMap<String, Object>) session;
	}
}
