package com.rxv5.stock.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * 商品信息
 * @author Rixu
 */
@Entity
@Table(name = "T_COMMODITY")
public class Commodity implements Serializable {

	private static final long serialVersionUID = -2947903896019511402L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "COMMODITY_ID", length = 32)
	private String id;

	@Column(name = "NAME_", length = 100)
	private String name;//品名

	@Column(name = "TYPE_", length = 100)
	private String type;//型号

	@Column(name = "PIN_YIN", length = 20)
	private String py;//拼音

	@Column(name = "REMARK_", length = 2000)
	private String remark;//备注

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "SUPPLIER_ID")
	private Supplier supplier;//供应商

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPy() {
		return py;
	}

	public void setPy(String py) {
		this.py = py;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@Override
	public String toString() {
		return "Commodity [id=" + id + ", name=" + name + ", type=" + type + ", py=" + py + ", remark=" + remark
				+ ", supplier=" + supplier + "]";
	}

}
