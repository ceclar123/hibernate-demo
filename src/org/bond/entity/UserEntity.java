package org.bond.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name = "g_user")
public class UserEntity {
	private long id;

	@Id
	@javax.persistence.Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private String loginName;

	@Basic
	@javax.persistence.Column(name = "logname", nullable = false, insertable = true, updatable = true, length = 512)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	private String userName;

	@Basic
	@javax.persistence.Column(name = "name", nullable = false, insertable = true, updatable = true, length = 512)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String password;

	@Basic
	@javax.persistence.Column(name = "PSD", nullable = false, insertable = true, updatable = true, length = 512)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String md5psd;

	@Basic
	@javax.persistence.Column(name = "md5psd", nullable = true, insertable = true, updatable = true, length = 512)
	public String getMd5psd() {
		return md5psd;
	}

	public void setMd5psd(String md5psd) {
		this.md5psd = md5psd;
	}

	private short status;

	@Basic
	@javax.persistence.Column(name = "status", nullable = true, insertable = true, updatable = true)
	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

}
