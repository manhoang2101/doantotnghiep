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
import com.poly.dao.CategoryDAOImpl;
import com.poly.entities.Category;
import com.poly.utils.Convert;
import com.poly.utils.Validate;


@Namespace("/admin/category")
@ResultPath("/content")
@InterceptorRef(value = "authStack")
public class CategoryAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private static final long serialVersionUID = 1L;

	private final CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();

	private int id; // URL parameter: category ID
	private List<Category> categories;
	private Category category;

	private HttpServletRequest request; // Request
	private SessionMap<String, Object> session; // Session

	public CategoryAction() {
		// set model for view
		// set all row category
		categories = categoryDAOImpl.findAll();
	}

	/*
	 * View Category
	 */
	@Action(value = "index", results = {
			@Result(name = SUCCESS, location = "index.jsp")
	})
	public String view() {
		return SUCCESS;
	}

	/*
	 * Add Category
	 */
	@Action(value = "add", results = {
			@Result(name = SUCCESS, location = "index", type = "redirectAction"),
			@Result(name = INPUT, location = "add.jsp")
	})
	public String add() {
		// if get method POST
		if ("POST".equals(request.getMethod())) {
			if (category != null) {
				// set value for var slug
				String slug = Convert.toSlug(category.getName());
				category.setSlug(slug);
				// save category
				if (categoryDAOImpl.insert(category)) {
					session.put("success", "Thêm thành công.");
				} else
					session.put("error", "Thêm thất bại.");

				return SUCCESS;
			}
		}

		return INPUT;
	}

	/*
	 * Edit Category
	 */
	@Action(value = "edit", results = {
			@Result(name = SUCCESS, location = "index", type = "redirectAction"),
			@Result(name = INPUT, location = "edit.jsp")
	})
	public String edit() {
		// create var row
		Category row = null;
		// check row exist
		if ((row = categoryDAOImpl.findOne(id)) == null) {
			session.put("error", "Danh mục không tồn tại.");
			return SUCCESS;
		}

		// if get method POST
		if ("POST".equals(request.getMethod())) {
			if (category != null) {
				// merge
				Validate.merge(row, category);
				// set value for var slug
				String slug = Convert.toSlug(category.getName());
				row.setSlug(slug);
				// update category
				if (categoryDAOImpl.update(row)) {
					session.put("success", "Sửa thành công.");
				} else
					session.put("error", "Sửa thất bại.");

				return SUCCESS;
			}
		}

		// if not get method POST
		// set model for view
		// set one row category
		category = row;

		return INPUT;
	}

	/*
	 * Delete Category
	 */
	@Action(value = "del", results = {
			@Result(name = SUCCESS, location = "index", type = "redirectAction")
	})
	public String del() {
		// delete category
		if (categoryDAOImpl.delete(id)) {
			session.put("success", "Xóa thành công.");
		} else
			session.put("error", "Xóa thất bại.");

		return SUCCESS;
	}

	/*
	 * Validate
	 */
	@Override
	public void validate() {
		if (category != null) {
			// check name
			if (category.getName() != null) {
				if (category.getName().isEmpty())
					this.addFieldError("category.name", "Tên danh mục không được để trống.");
				else if (!categoryDAOImpl.find(category.getName()).isEmpty())
					this.addFieldError("category.name", "Tên danh mục không tồn tại ");
			}
		}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setSession(Map<String, Object> session) {
		this.session = (SessionMap<String, Object>) session;
	}
}
