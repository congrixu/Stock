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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * 盘点
 */
@Entity
@Table(name = "T_INVENTORY")
public class Inventory implements Serializable {

	private static final long serialVersionUID = 1371719935646559319L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "INVENTORY_ID", length = 32)
	private String id;

	@Column(name = "FIRST_NUM")
	private Integer firstNum;//原始值

	@Column(name = "NUM_")
	private Integer num;//盘点后的值

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "COMMODITY_ID")
	private Commodity cdy;// 商品 

	@Column(name = "CREATE_DATE")
	private Date createDate;//盘点日期

	@Column(name = "REMARK_", length = 2000)
	private String remark;//备注

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getFirstNum() {
		return firstNum;
	}

	public void setFirstNum(Integer firstNum) {
		this.firstNum = firstNum;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Commodity getCdy() {
		return cdy;
	}

	public void setCdy(Commodity cdy) {
		this.cdy = cdy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
