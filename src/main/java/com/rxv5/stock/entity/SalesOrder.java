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
 * 销售单
 * 
 * @author Rixu
 * 
 */
@Entity
@Table(name = "T_SALES_ORDER")
public class SalesOrder implements Serializable {

	private static final long serialVersionUID = -9070481429429579297L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "SALES_ORDER_ID", length = 32)
	private String id;

	@Column(name = "CLIENT_NAME", length = 50)
	private String clientName;// 客户名称

	@Column(name = "CLIENT_PHONE", length = 50)
	private String clientPhone;// 客户联系电话

	@Column(name = "CLIENT_ADDR", length = 500)
	private String clientAddr;// 客户地址

	@Column(name = "SALES_DATE")
	private Date salesDate;// 销售日期

	@Column(name = "REMARK_", length = 1000)
	private String remark;// 备注

	@Column(name = "TOTAL_NUM", scale = 6, precision = 4)
	private Integer totalNum;//总数量

	@Column(name = "TOTAL_PRICE", scale = 6, precision = 4)
	private Double totalPrice;//总价钱

	@Column(name = "STATE_", length = 2)
	public String state;//状态

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "USER_ID")
	public User user;//销售人

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "FITTER_USER_ID")
	public User fitters;//安装人员

	//非持久化属性
	@Transient
	private String stateStr;//状态名称

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientPhone() {
		return clientPhone;
	}

	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}

	public String getClientAddr() {
		return clientAddr;
	}

	public void setClientAddr(String clientAddr) {
		this.clientAddr = clientAddr;
	}

	public Date getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getFitters() {
		return fitters;
	}

	public void setFitters(User fitters) {
		this.fitters = fitters;
	}

	@Override
	public String toString() {
		return "SalesOrder [id=" + id + ", clientName=" + clientName + ", clientPhone=" + clientPhone + ", clientAddr="
				+ clientAddr + ", salesDate=" + salesDate + ", remark=" + remark + ", totalNum=" + totalNum
				+ ", totalPrice=" + totalPrice + ", state=" + state + ", user=" + user + ", fitters=" + fitters
				+ ", stateStr=" + stateStr + "]";
	}

}
