package com.rxv5.stock.bean;

import java.io.Serializable;

public class TotalBean implements Serializable {

	private static final long serialVersionUID = -6317194094856122501L;

	private Long totalSalesCount = 0l;//销售单数
	private Long totalSalesNum = 0l;//销售总量
	private Double totalSalesPrice = 0d;//总销售价

	private Long totalPurchaseCount = 0l;//采购单数
	private Long totalPurchaseNum = 0l;//总采购数量
	private Double totalPurchasePrice = 0d;//总采购价

	private Long giftCount = 0l;//赠送单数
	private Long giftNum = 0l;//赠送商品数量
	private Double totalGiftPurchasePrice = 0d;//赠送商品总采购价格

	public Long getTotalSalesCount() {
		return totalSalesCount;
	}

	public void setTotalSalesCount(Long totalSalesCount) {
		this.totalSalesCount = totalSalesCount;
	}

	public Long getTotalSalesNum() {
		return totalSalesNum;
	}

	public void setTotalSalesNum(Long totalSalesNum) {
		this.totalSalesNum = totalSalesNum;
	}

	public Double getTotalSalesPrice() {
		return totalSalesPrice;
	}

	public void setTotalSalesPrice(Double totalSalesPrice) {
		this.totalSalesPrice = totalSalesPrice;
	}

	public Long getTotalPurchaseCount() {
		return totalPurchaseCount;
	}

	public void setTotalPurchaseCount(Long totalPurchaseCount) {
		this.totalPurchaseCount = totalPurchaseCount;
	}

	public Long getTotalPurchaseNum() {
		return totalPurchaseNum;
	}

	public void setTotalPurchaseNum(Long totalPurchaseNum) {
		this.totalPurchaseNum = totalPurchaseNum;
	}

	public Double getTotalPurchasePrice() {
		return totalPurchasePrice;
	}

	public void setTotalPurchasePrice(Double totalPurchasePrice) {
		this.totalPurchasePrice = totalPurchasePrice;
	}

	public Long getGiftCount() {
		return giftCount;
	}

	public void setGiftCount(Long giftCount) {
		this.giftCount = giftCount;
	}

	public Long getGiftNum() {
		return giftNum;
	}

	public void setGiftNum(Long giftNum) {
		this.giftNum = giftNum;
	}

	public Double getTotalGiftPurchasePrice() {
		return totalGiftPurchasePrice;
	}

	public void setTotalGiftPurchasePrice(Double totalGiftPurchasePrice) {
		this.totalGiftPurchasePrice = totalGiftPurchasePrice;
	}

}
