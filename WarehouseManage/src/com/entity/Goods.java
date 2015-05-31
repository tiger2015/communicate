package com.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */

public class Goods implements java.io.Serializable {

	// Fields

	private String goodsId;
	private Warehouse warehouse;
	private Unit unit;
	private Category category;
	private String goodsName;
	private Double goodsMaxAmount;
	private Double goodsMinAmount;
	private Double goodsAmount;
	private Double goodsPrice;
	private Set pickupBills = new HashSet(0);
	private Set supplyBills = new HashSet(0);

	// Constructors

	/** default constructor */
	public Goods() {
	}

	/** minimal constructor */
	public Goods(String goodsId, String goodsName) {
		this.goodsId = goodsId;
		this.goodsName = goodsName;
	}

	/** full constructor */
	public Goods(String goodsId, Warehouse warehouse, Unit unit,
			Category category, String goodsName, Double goodsMaxAmount,
			Double goodsMinAmount, Double goodsAmount, Double goodsPrice,
			Set pickupBills, Set supplyBills) {
		this.goodsId = goodsId;
		this.warehouse = warehouse;
		this.unit = unit;
		this.category = category;
		this.goodsName = goodsName;
		this.goodsMaxAmount = goodsMaxAmount;
		this.goodsMinAmount = goodsMinAmount;
		this.goodsAmount = goodsAmount;
		this.goodsPrice = goodsPrice;
		this.pickupBills = pickupBills;
		this.supplyBills = supplyBills;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Double getGoodsMaxAmount() {
		return goodsMaxAmount;
	}

	public void setGoodsMaxAmount(Double goodsMaxAmount) {
		this.goodsMaxAmount = goodsMaxAmount;
	}

	public Double getGoodsMinAmount() {
		return goodsMinAmount;
	}

	public void setGoodsMinAmount(Double goodsMinAmount) {
		this.goodsMinAmount = goodsMinAmount;
	}

	public Double getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(Double goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	public Double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Set getPickupBills() {
		return pickupBills;
	}

	public void setPickupBills(Set pickupBills) {
		this.pickupBills = pickupBills;
	}

	public Set getSupplyBills() {
		return supplyBills;
	}

	public void setSupplyBills(Set supplyBills) {
		this.supplyBills = supplyBills;
	}

	
}