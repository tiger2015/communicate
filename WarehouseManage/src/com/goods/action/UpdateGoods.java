package com.goods.action;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.GoodsDAO;
import com.entity.Goods;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateGoods extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 745281396922289995L;
	private Goods goods;
	private String choice;
	private String condition;
	private String currentPage;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
		GoodsDAO dao=(GoodsDAO) context.getBean("goodsDAO");
		List<Goods> temp = dao.findGoodsByCondition(0,
				this.goods.getGoodsId(), null);
		if (temp != null && temp.size() > 0) {
			Goods goods = temp.get(0);
			if (goods.getGoodsAmount() > this.goods.getGoodsMaxAmount()) {
				addFieldError("goods.goodsMaxAmount", "最大库存不能小于现有库存!");
				return INPUT;
			}
		}
		if (this.goods.getGoodsMaxAmount() < this.goods.getGoodsMinAmount()) {
			addFieldError("goods.goodsMinAmount", "警戒库存不能大于最大库存!");
			return INPUT;
		}

		dao.updateGoods(goods);
		return SUCCESS;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

}
