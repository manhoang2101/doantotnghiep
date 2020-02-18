package com.poly.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pro_details")
public class ProDetail {

	private Integer id;
	private Product product;
	private String cpu;
	private String ram;
	private String screen;
	private String vga;
	private String storage;
	private String extenMemmory;
	private String cam1;
	private String cam2;
	private String sim;
	private String connect;
	private String pin;
	private String os;
	private String note;
	private Date createdAt;
	private Date updatedAt;

	public ProDetail() {
	}

	public ProDetail(Product product) {
		this.product = product;
	}

	public ProDetail(Product product, String cpu, String ram, String screen, String vga, String storage,
			String extenMemmory, String cam1, String cam2, String sim, String connect, String pin, String os,
			String note, Date createdAt, Date updatedAt) {
		this.product = product;
		this.cpu = cpu;
		this.ram = ram;
		this.screen = screen;
		this.vga = vga;
		this.storage = storage;
		this.extenMemmory = extenMemmory;
		this.cam1 = cam1;
		this.cam2 = cam2;
		this.sim = sim;
		this.connect = connect;
		this.pin = pin;
		this.os = os;
		this.note = note;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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
	@JoinColumn(name = "pro_id", nullable = false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "cpu")
	public String getCpu() {
		return this.cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	@Column(name = "ram")
	public String getRam() {
		return this.ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	@Column(name = "screen")
	public String getScreen() {
		return this.screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	@Column(name = "vga")
	public String getVga() {
		return this.vga;
	}

	public void setVga(String vga) {
		this.vga = vga;
	}

	@Column(name = "storage")
	public String getStorage() {
		return this.storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	@Column(name = "exten_memmory")
	public String getExtenMemmory() {
		return this.extenMemmory;
	}

	public void setExtenMemmory(String extenMemmory) {
		this.extenMemmory = extenMemmory;
	}

	@Column(name = "cam1")
	public String getCam1() {
		return this.cam1;
	}

	public void setCam1(String cam1) {
		this.cam1 = cam1;
	}

	@Column(name = "cam2")
	public String getCam2() {
		return this.cam2;
	}

	public void setCam2(String cam2) {
		this.cam2 = cam2;
	}

	@Column(name = "sim")
	public String getSim() {
		return this.sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	@Column(name = "connect")
	public String getConnect() {
		return this.connect;
	}

	public void setConnect(String connect) {
		this.connect = connect;
	}

	@Column(name = "pin")
	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	@Column(name = "os")
	public String getOs() {
		return this.os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	@Column(name = "note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
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

}
