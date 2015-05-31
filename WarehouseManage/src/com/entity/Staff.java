package com.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Staff entity. @author MyEclipse Persistence Tools
 */

public class Staff implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4967852067148287750L;
	// Fields

	private String staffId;
	private String staffName;
	private Integer staffSex;
	private Date staffBirthday;
	private String staffPhonenumber;
	private Integer staffRole;
	private Set pickupBills = new HashSet(0);
	private Set warehouses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Staff() {
	}

	/** minimal constructor */
	public Staff(String staffId, String staffName, Date staffBirthday,
			String staffPhonenumber) {
		this.staffId = staffId;
		this.staffName = staffName;
		this.staffBirthday = staffBirthday;
		this.staffPhonenumber = staffPhonenumber;
	}

	/** full constructor */
	public Staff(String staffId, String staffName, Integer staffSex,
			Date staffBirthday, String staffPhonenumber, Integer staffRole,
			Set pickupBills, Set warehouses) {
		this.staffId = staffId;
		this.staffName = staffName;
		this.staffSex = staffSex;
		this.staffBirthday = staffBirthday;
		this.staffPhonenumber = staffPhonenumber;
		this.staffRole = staffRole;
		this.pickupBills = pickupBills;
		this.warehouses = warehouses;
	}

	// Property accessors

	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Integer getStaffSex() {
		return this.staffSex;
	}

	public void setStaffSex(Integer staffSex) {
		this.staffSex = staffSex;
	}

	public Date getStaffBirthday() {
		return this.staffBirthday;
	}

	public void setStaffBirthday(Date staffBirthday) {
		this.staffBirthday = staffBirthday;
	}

	public String getStaffPhonenumber() {
		return this.staffPhonenumber;
	}

	public void setStaffPhonenumber(String staffPhonenumber) {
		this.staffPhonenumber = staffPhonenumber;
	}

	public Integer getStaffRole() {
		return this.staffRole;
	}

	public void setStaffRole(Integer staffRole) {
		this.staffRole = staffRole;
	}

	

	public Set getPickupBills() {
		return pickupBills;
	}

	public void setPickupBills(Set pickupBills) {
		this.pickupBills = pickupBills;
	}

	public Set getWarehouses() {
		return this.warehouses;
	}

	public void setWarehouses(Set warehouses) {
		this.warehouses = warehouses;
	}

}