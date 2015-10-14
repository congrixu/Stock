package com.rxv5.stock.bean;

import java.io.Serializable;

/**
 * 销售人员销售情况统计/安装工统计
 * @author congrixu
 */
public class SalesUserBean implements Serializable {

	private static final long serialVersionUID = 3229338069923317780L;

	private Double total;//总售价
	private Double count;//总单数
	private String userId;
	private String userName;

	public Double getTotal() {
		return total;
	}

	public Double getCount() {
		return count;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
