package com.poly.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.poly.action.admin.AuthAction;
import com.poly.dao.AdminDAOImpl;
import com.poly.entities.AdminUser;

public class Auth implements Interceptor {

	private static final long serialVersionUID = 1L;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub

	}

	public String intercept(ActionInvocation invocation) throws Exception {
		// get session user
		Object user = ActionContext.getContext().getSession().get("a_user");
		if (user != null) {
			int id = ((AdminUser) user).getId();
			AdminUser dbUser = new AdminDAOImpl().findOne(id);
			if (dbUser != null) {
				// in login page
				// if loged redirect to admin home page
				if (invocation.getAction() instanceof AuthAction)
					return ActionSupport.SUCCESS;

				return invocation.invoke();
			}
		}

		// skip redirect in login
		if (invocation.getAction() instanceof AuthAction)
			return invocation.invoke();

		// redirect login global
		return ActionSupport.LOGIN;
	}
}
