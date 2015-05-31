package com.entity;

import java.util.Date;

/**
 * Pickupbill entity. @author MyEclipse Persistence Tools
 */

public class PickupBill implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7252770052143191377L;
	private String ladingBillId;
	private Staff staff;
	private Goods goods;
	private Date pickupDate;
	private Double goodsAmount;

	// Constructors

	/** default constructor */
	public PickupBill() {
	}

	/** minimal constructor */
	public PickupBill(String ladingBillId) {
		this.ladingBillId = ladingBillId;
	}

	/** full constructor */
	public PickupBill(String ladingBillId, Staff staff, Goods goods,
			Date pickupDate, Double goodsAmount) {
		this.ladingBillId = ladingBillId;
		this.staff = staff;
		this.goods = goods;
		this.pickupDate = pickupDate;
		this.goodsAmount = goodsAmount;
	}

	public String getLadingBillId() {
		return ladingBillId;
	}

	public void setLadingBillId(String ladingBillId) {
		this.ladingBillId = ladingBillId;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Date getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	public Double getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(Double goodsAmount) {
		if(goodsAmount==null)
			this.goodsAmount=0.0;
		else
		this.goodsAmount = goodsAmount;
	}

}