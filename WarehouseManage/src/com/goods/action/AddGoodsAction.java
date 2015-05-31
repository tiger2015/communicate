package com.goods.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.GoodsDAO;
import com.entity.Goods;
import com.opensymphony.xwork2.ActionSupport;

public class AddGoodsAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7857657279187738433L;
	private Goods goods;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		goods.setGoodsAmount(0.0);
		ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
		GoodsDAO dao=(GoodsDAO) context.getBean("goodsDAO");
		dao.saveGoods(goods);
		return SUCCESS;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

}
