package com.supplybill.action;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.GoodsDAO;
import com.dao.SupplyBillDAO;
import com.entity.Goods;
import com.entity.SupplyBill;
import com.opensymphony.xwork2.ActionSupport;

public class AddSupplyBill extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2637049794823927561L;
	private SupplyBill supplyBill;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String goodsId = this.supplyBill.getGoods().getGoodsId();
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"config.xml");
		GoodsDAO dao=(GoodsDAO) context.getBean("goodsDAO");
		SupplyBillDAO supplyBillDAO=(SupplyBillDAO) context.getBean("supplybillDAO");
		List<Goods> result =dao.findGoodsByCondition(0, goodsId, null);
		Goods goods =(Goods) context.getBean("goods");
		if (result != null && result.size() > 0) {
			goods = (Goods) result.get(0);
			if (this.supplyBill.getGoodsAmount() < 0) {
				this.addFieldError("supplyBill.goodsAmount", "入库数量应该大于0!");
				return INPUT;
			} else if (goods.getGoodsMaxAmount() < (this.supplyBill
					.getGoodsAmount() + goods.getGoodsAmount())) {
				this.addFieldError("supplyBill.goodsAmount", "数量太大，剩余库存不足!");
				return INPUT;
			}

		}
		supplyBillDAO.saveSupplyBill(supplyBill);
		return SUCCESS;
	}

	public SupplyBill getSupplyBill() {
		return supplyBill;
	}

	public void setSupplyBill(SupplyBill supplyBill) {
		this.supplyBill = supplyBill;
	}
}
