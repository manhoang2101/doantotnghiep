package com.poly.action.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
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
import com.poly.dao.CategoryDAOImpl;
import com.poly.dao.DetailImgDAOImpl;
import com.poly.dao.ProDetailDAOImpl;
import com.poly.dao.ProductDAOImpl;
import com.poly.entities.Category;
import com.poly.entities.DetailImg;
import com.poly.entities.ProDetail;
import com.poly.entities.Product;
import com.poly.utils.Convert;
import com.poly.utils.Validate;

@Namespace("/admin/product")
@ResultPath("/content")
@InterceptorRef(value = "authStack")
public class ProductAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private static final long serialVersionUID = 1L;

	private final CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();
	private final ProductDAOImpl productDAOImpl = new ProductDAOImpl();
	private final ProDetailDAOImpl proDetailDAOImpl = new ProDetailDAOImpl();
	private final DetailImgDAOImpl detailImgDAOImpl = new DetailImgDAOImpl();

	private int id; // URL parameter: product ID
	private List<Category> categories;
	private List<Product> products;
	private Product product;
	private ProDetail proDetail;
	private DetailImg detailImg;

	// File upload
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	// Multiple file upload
	private List<File> uploads;
	private List<String> uploadsFileName;
	private List<String> uploadsContentType;

	private HttpServletRequest request; // Request
	private SessionMap<String, Object> session; // Session

	public ProductAction() {
		// set model for view
		// set all row category & product
		categories = categoryDAOImpl.findAll();
		products = productDAOImpl.findAll();
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
	 * Add Product
	 */
	@Action(value = "add", results = {
			@Result(name = SUCCESS, location = "index", type = "redirectAction"),
			@Result(name = INPUT, location = "add.jsp")
	}, interceptorRefs = {
			@InterceptorRef(params = {
					"allowedTypes", "image/jpeg", "maximumSize", "1000000"
			}, value = "fileUpload"), @InterceptorRef("defaultStack"), @InterceptorRef("validation")
	})
	public String add() {
		// if get method POST
		if ("POST".equals(request.getMethod())) {
			if (product != null) {
				// set value for var slug
				String slug = Convert.toSlug(product.getName());
				product.setSlug(slug);
				// set status
				product.setStatus(1);
				// file upload
				if (upload != null) {
					try {
						String filePath = request.getServletContext().getRealPath("/").concat("uploads");
						FileUtils.copyFile(upload, new File(filePath, uploadFileName));
						product.setImages(uploadFileName);
					} catch (IOException e) {
						return SUCCESS;
					}
				}
				// save product
				if (productDAOImpl.insert(product)) {
					// multiple file upload
					if (uploads != null) {
						int i = 0;
						for (File file : uploads) {
							try {
								String filePath = request.getServletContext().getRealPath("/").concat("uploads");
								FileUtils.copyFile(file, new File(filePath, uploadsFileName.get(i)));
								detailImg = new DetailImg();
								detailImg.setImagesUrl(uploadsFileName.get(i));
								detailImg.setProduct(product);
								detailImgDAOImpl.insert(detailImg);
							} catch (IOException e) {
								return SUCCESS;
							}
							i++;
						}
					}
					// save product_detail
					proDetail.setProduct(product);
					proDetailDAOImpl.insert(proDetail);

					session.put("success", "Thêm thành công.");
				} else
					session.put("error", "Thêm thất bại.");

				return SUCCESS;
			}
		}

		return INPUT;
	}

	/*
	 * Edit Product
	 */
	@Action(value = "edit", results = {
			@Result(name = SUCCESS, location = "index", type = "redirectAction"),
			@Result(name = INPUT, location = "edit.jsp")
	})
	public String edit() {
		// create var row
		Product row = null;
		// check row exist
		if ((row = productDAOImpl.findOne(id)) == null) {
			session.put("message", "Sản phẩm không tồn tại.");
			return SUCCESS;
		}

		// if get method POST
		if ("POST".equals(request.getMethod())) {
			if (product != null) {
				// merger
				Validate.merge(row, product);
				// set value for var slug
				String slug = Convert.toSlug(product.getName());
				row.setSlug(slug);
				// file upload
				if (upload != null) {
					try {
						String filePath = request.getServletContext().getRealPath("/").concat("uploads");
						FileUtils.copyFile(upload, new File(filePath, uploadFileName));
						if (!uploadFileName.isEmpty()) {
							row.setImages(uploadFileName);
						}
					} catch (IOException e) {
						return ERROR;
					}
				}
				// update product
				if (productDAOImpl.update(row)) {
					// multiple file upload
					if (uploads != null) {
						int i = 0;
						for (File file : uploads) {
							try {
								String filePath = request.getServletContext().getRealPath("/").concat("uploads");
								FileUtils.copyFile(file, new File(filePath, uploadsFileName.get(i)));
								detailImg = new DetailImg();
								detailImg.setImagesUrl(uploadsFileName.get(i));
								detailImg.setProduct(row);
								detailImgDAOImpl.insert(detailImg);
							} catch (IOException e) {
								return ERROR;
							}
							i++;
						}
					}
					// save product_detail
					row = productDAOImpl.findOne(row.getId());
					ProDetail obj = null;
					for (ProDetail e : row.getProDetails()) {
						obj = e;
					}
					// check product_detail exist?
					if (obj != null) {
						// merger
						Validate.merge(obj, proDetail);
						// set product_id
						obj.setProduct(row);
						proDetailDAOImpl.update(obj);
					} else {// update product_detail
						// set product_id
						proDetail.setProduct(row);
						proDetailDAOImpl.insert(proDetail);
					}

					session.put("success", "Sửa thành công.");
				} else
					session.put("error", "Sửa thất bại.");

				return SUCCESS;
			}
		}

		// if get method POST
		// set model for view
		// set one row product by id
		product = row;
		// set one row product_detail
		for (ProDetail detail : row.getProDetails()) {
			proDetail = detail;
		}

		return INPUT;
	}

	/*
	 * Delete Product
	 */
	@Action(value = "del", results = {
			@Result(name = SUCCESS, location = "index", type = "redirectAction")
	})
	public String del() {
		product = productDAOImpl.findOne(id);
		// check row exist
		if (product == null) {
			session.put("message", "Sản phẩm không tồn tại.");
			return SUCCESS;
		}

		// delete all product_detail by product_id
		for (ProDetail proDetail : product.getProDetails()) {
			proDetailDAOImpl.delete(proDetail.getId());
		}

		// delete all detail_images by product_id
		for (DetailImg detailImg : product.getDetailImgs()) {
			detailImgDAOImpl.delete(detailImg.getId());
		}

		// detele product
		if (productDAOImpl.delete(id)) {
			session.put("success", "Xóa Thành Công");
		} else
			session.put("error", "Xóa Thất Bại");

		return SUCCESS;
	}

	/*
	 * Validate
	 */
	@Override
	public void validate() {
		if (product != null) {
			// check name			
			
			if (product.getName() != null) {
				if (product.getName().isEmpty())
					this.addFieldError("product.name", "Tên sản phẩm không được để trống.");				
			}			
			// check category_id
			if (product.getCategory() != null) {
				if (product.getCategory().getId() == 0
						|| categoryDAOImpl.findOne(product.getCategory().getId()) == null) {
					this.addFieldError("product.category.id", "Check danh mục.");
				}
			}
		}
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@VisitorFieldValidator
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProDetail getProDetail() {
		return proDetail;
	}

	public void setProDetail(ProDetail proDetail) {
		this.proDetail = proDetail;
	}

	public DetailImg getDetailImg() {
		return detailImg;
	}

	public void setDetailImg(DetailImg detailImg) {
		this.detailImg = detailImg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public List<File> getUploads() {
		return uploads;
	}

	public void setUploads(List<File> uploads) {
		this.uploads = uploads;
	}

	public List<String> getUploadsFileName() {
		return uploadsFileName;
	}

	public void setUploadsFileName(List<String> uploadsFileName) {
		this.uploadsFileName = uploadsFileName;
	}

	public List<String> getUploadsContentType() {
		return uploadsContentType;
	}

	public void setUploadsContentType(List<String> uploadsContentType) {
		this.uploadsContentType = uploadsContentType;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setSession(Map<String, Object> session) {
		this.session = (SessionMap<String, Object>) session;
	}
}
