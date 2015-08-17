package com.rxv5.stock.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * 采购单
 * 
 * @author Rixu
 */
@Entity
@Table(name = "T_PURCHASE_ORDER")
public class PurchaseOrder implements Serializable {

	private static final long serialVersionUID = 4350502839468721905L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "PURCHASE_ORDER_ID", length = 32)
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "SUPPLIER_ID")
	private Supplier supplier;// 供应商

	@Column(name = "CREATE_DATE")
	private Date createDate;//采购日期

	@Column(name = "STATE_", length = 2)
	private String state;//状态 1：在途 2：入库

	@Column(name = "REMARK_", length = 2000)
	private String remark;//备注

	//非持久化属性
	@Transient
	private String stateStr;//状态名称

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateStr() {
		return stateStr;
	}

	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [id=" + id + ", supplier=" + supplier + ", createDate=" + createDate + ", state=" + state
				+ ", remark=" + remark + ", stateStr=" + stateStr + "]";
	}

}
