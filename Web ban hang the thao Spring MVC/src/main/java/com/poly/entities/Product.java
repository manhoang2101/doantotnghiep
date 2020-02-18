package com.poly.entities;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "products")
public class Product {

	private Integer id;
	private Category category;
	private String name;
	private String slug;
	private String intro;
	private String packet;
	private String promo1;
	private String promo2;
	private String promo3;
	private String images;
	private Float price;
	private String tag;
	private Integer status;
	private String review;
	private Date createdAt;
	private Date updatedAt;
	private Set<DetailImg> detailImgs = new HashSet<DetailImg>(0);
	private Set<ProDetail> proDetails = new HashSet<ProDetail>(0);
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>(0);

	public Product() {
	}

	public Product(Category category) {
		this.category = category;
	}

	public Product(Category category, String name, String slug, String intro, String packet, String promo1,
			String promo2, String promo3, String images, Float price, String tag, Integer status, String review,
			Date createdAt, Date updatedAt, Set<DetailImg> detailImgs, Set<ProDetail> proDetails,
			Set<OrderDetail> orderDetails) {
		this.category = category;
		this.name = name;
		this.slug = slug;
		this.intro = intro;
		this.packet = packet;
		this.promo1 = promo1;
		this.promo2 = promo2;
		this.promo3 = promo3;
		this.images = images;
		this.price = price;
		this.tag = tag;
		this.status = status;
		this.review = review;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.detailImgs = detailImgs;
		this.proDetails = proDetails;
		this.orderDetails = orderDetails;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cat_id", nullable = false)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "slug")
	public String getSlug() {
		return this.slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	@Column(name = "intro")
	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column(name = "packet")
	public String getPacket() {
		return this.packet;
	}

	public void setPacket(String packet) {
		this.packet = packet;
	}

	@Column(name = "promo1")
	public String getPromo1() {
		return this.promo1;
	}

	public void setPromo1(String promo1) {
		this.promo1 = promo1;
	}

	@Column(name = "promo2")
	public String getPromo2() {
		return this.promo2;
	}

	public void setPromo2(String promo2) {
		this.promo2 = promo2;
	}

	@Column(name = "promo3")
	public String getPromo3() {
		return this.promo3;
	}

	public void setPromo3(String promo3) {
		this.promo3 = promo3;
	}

	@Column(name = "images")
	public String getImages() {
		return this.images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	@Column(name = "price", precision = 53, scale = 0)
	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Column(name = "tag")
	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "review")
	public String getReview() {
		return this.review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 23)
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", length = 23)
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<DetailImg> getDetailImgs() {
		return this.detailImgs;
	}

	public void setDetailImgs(Set<DetailImg> detailImgs) {
		this.detailImgs = detailImgs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<ProDetail> getProDetails() {
		return this.proDetails;
	}

	public void setProDetails(Set<ProDetail> proDetails) {
		this.proDetails = proDetails;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
