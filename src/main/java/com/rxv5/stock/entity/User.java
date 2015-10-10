package com.rxv5.stock.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_USER")
public class User implements Serializable {

	private static final long serialVersionUID = -1528002124715572765L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "ID", length = 32)
	private String id;

	@Column(name = "USER_ID", length = 20)
	private String userId;//用户账号

	@Column(name = "p_w_d", length = 20)
	private String pwd;

	@Column(name = "NAME_", length = 20)
	private String name;

	@Column(name = "PHONE_", length = 50)
	private String phone;//电话

	@Column(name = "ADDR_", length = 2000)
	private String addr;//家庭住址

	@Column(name = "ID_CARD", length = 20)
	private String idCard;//身份证

	@Column(name = "ENTRY_DATE")
	private Date entryDate;//入职时间

	@Column(name = "RESIGN_DATE")
	private Date resignDate;//离职时间

	@Column(name = "STATE_", length = 2)
	private String state;//1:在职，0：离职

	@Column(name = "BIRTHDAY_")
	private Date birthday;

	//非持久化属性
	@Transient
	private String stateStr;//状态名称

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddr() {
		return addr;
	}

	public String getIdCard() {
		return idCard;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public String getState() {
		return state;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Date getResignDate() {
		return resignDate;
	}

	public void setResignDate(Date resignDate) {
		this.resignDate = resignDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
