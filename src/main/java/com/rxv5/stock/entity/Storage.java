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
 * 库存信息
 * @author congrixu
 */
@Entity
@Table(name = "T_STORAGE")
public class Storage implements Serializable {

	private static final long serialVersionUID = -2293333532391730447L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "STORAGE_ID", length = 32)
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "COMMODITY_ID")
	private Commodity cdy;// 商品 

	@Column(name = "NUM_")
	private Integer num;// 库存数量

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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
