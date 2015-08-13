package com.rxv5.stock.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 供应商
 * @author Rixu
 */
@Entity
@Table(name = "T_SUPPLIER")
public class Supplier implements Serializable {

	private static final long serialVersionUID = -3404651217068868978L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "SUPPLIER_ID", length = 32)
	private String id;

	@Column(name = "NAME_", length = 100)
	private String name;//供应商名称

	@Column(name = "PIN_YIN", length = 20)
	private String py;//拼音

	@Column(name = "ADDR_", length = 1000)
	private String addr;//地址

	@Column(name = "PHONE_", length = 200)
	private String phone;//电话

	@Column(name = "FAX_", length = 200)
	private String fax;//传真

	@Column(name = "MOBILE_", length = 200)
	private String mobile;//手机

	@Column(name = "BANK_INFO", length = 2000)
	private String bankInfo;//银行信息

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

	public String getPy() {
		return py;
	}

	public void setPy(String py) {
		this.py = py;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBankInfo() {
		return bankInfo;
	}

	public void setBankInfo(String bankInfo) {
		this.bankInfo = bankInfo;
	}

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", py=" + py + ", addr=" + addr + ", phone=" + phone
				+ ", fax=" + fax + ", mobile=" + mobile + ", bankInfo=" + bankInfo + "]";
	}

}
