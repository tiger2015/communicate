package com.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Unit entity. @author MyEclipse Persistence Tools
 */

public class Unit implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7623825945666051949L;
	private Integer unitId;
	private String unitName;
	private Set goodses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Unit() {
	}

	/** minimal constructor */
	public Unit(Integer unitId, String unitName) {
		this.unitId = unitId;
		this.unitName = unitName;
	}

	/** full constructor */
	public Unit(Integer unitId, String unitName, Set goodses) {
		this.unitId = unitId;
		this.unitName = unitName;
		this.goodses = goodses;
	}

	// Property accessors

	public Integer getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Set getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set goodses) {
		this.goodses = goodses;
	}

}