package com.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Warehouse entity. @author MyEclipse Persistence Tools
 */

public class Warehouse implements java.io.Serializable {

	// Fields

	private String warehouseId;
	private Staff staff;
	private String warehouseName;
	private Set goodses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Warehouse() {
	}

	/** minimal constructor */
	public Warehouse(String warehouseId, String warehouseName) {
		this.warehouseId = warehouseId;
		this.warehouseName = warehouseName;
	}

	/** full constructor */
	public Warehouse(String warehouseId, Staff staff, String warehouseName,
			Set goodses) {
		this.warehouseId = warehouseId;
		this.staff = staff;
		this.warehouseName = warehouseName;
		this.goodses = goodses;
	}

	// Property accessors

	public String getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getWarehouseName() {
		return this.warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public Set getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set goodses) {
		this.goodses = goodses;
	}

}