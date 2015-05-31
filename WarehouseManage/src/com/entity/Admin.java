package com.entity;


/**
 * Admin entity. @author MyEclipse Persistence Tools
 */

public class Admin implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4151288912392642213L;
	private String adminName;
	private String adminPassword;
	private Integer adminRole;
	private Integer adminIsOnline;

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** minimal constructor */
	public Admin(String adminName) {
		this.adminName = adminName;
	}

	/** full constructor */
	public Admin(String adminName, String adminPassword, Integer adminRole,
			Integer adminIsOnline) {
		this.adminName = adminName;
		this.adminPassword = adminPassword;
		this.adminRole = adminRole;
		this.adminIsOnline = adminIsOnline;
	}

	// Property accessors

	public String getAdminName() {
		return this.adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return this.adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public Integer getAdminRole() {
		return this.adminRole;
	}

	public void setAdminRole(Integer adminRole) {
		this.adminRole = adminRole;
	}

	public Integer getAdminIsOnline() {
		return adminIsOnline;
	}

	public void setAdminIsOnline(Integer adminIsOnline) {
		this.adminIsOnline = adminIsOnline;
	}
}