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
 * 采购单明细
 * 
 * @author Rixu
 */
@Entity
@Table(name = "T_PURCHASE_ITEM")
public class PurchaseItem implements Serializable {

	private static final long serialVersionUID = -6555580411718305496L;
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "PURCHASE_ITEM_ID", length = 32)
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "COMMODITY_ID")
	private Commodity cdy;// 商品

	@Column(name = "CLOTHES_")
	private Integer clothes;// 件数

	@Column(name = "NUM_")
	private Integer num;// 采购数量

	@Column(name = "PRICE_", scale = 6, precision = 4)
	private Double price;// 单价

	@Column(name = "TOTAL_PRICE", scale = 6, precision = 4)
	private Double totalPrice;// 总金额

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "PURCHASE_ORDER_ID")
	private PurchaseOrder purchaseOrder;//采购单

	@Column(name = "REMARK_", length = 2000)
	private String remark;//备注

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Commodity getCdy() {
		return cdy;
	}

	public void setCdy(Commodity cdy) {
		this.cdy = cdy;
	}

	public Integer getClothes() {
		return clothes;
	}

	public void setClothes(Integer clothes) {
		this.clothes = clothes;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "PurchaseItem [id=" + id + ", cdy=" + cdy + ", clothes=" + clothes + ", num=" + num + ", price=" + price
				+ ", totalPrice=" + totalPrice + ", purchaseOrder=" + purchaseOrder + ", remark=" + remark + "]";
	}

}
