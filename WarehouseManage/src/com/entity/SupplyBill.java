package com.entity;

import java.util.Date;

/**
 * Supplybill entity. @author MyEclipse Persistence Tools
 */

public class SupplyBill implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6151787327297659888L;
	private String supplyBillId;
	private Provider provider;
	private Goods goods;
	private Date supplyDate;
	private Double goodsAmount;

	// Constructors

	/** default constructor */
	public SupplyBill() {
	}

	/** minimal constructor */
	public SupplyBill(String supplyBillId) {
		this.supplyBillId = supplyBillId;
	}

	/** full constructor */
	public SupplyBill(String supplyBillId, Provider provider, Goods goods,
			Date supplyDate, Double goodsAmount) {
		this.supplyBillId = supplyBillId;
		this.provider = provider;
		this.goods = goods;
		this.supplyDate = supplyDate;
		this.goodsAmount = goodsAmount;
	}

	public String getSupplyBillId() {
		return supplyBillId;
	}

	public void setSupplyBillId(String supplyBillId) {
		this.supplyBillId = supplyBillId;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Date getSupplyDate() {
		return supplyDate;
	}

	public void setSupplyDate(Date supplyDate) {
		this.supplyDate = supplyDate;
	}

	public Double getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(Double goodsAmount) {
		if (goodsAmount == null)
			this.goodsAmount = 0.0;
		else
			this.goodsAmount = goodsAmount;
	}

}