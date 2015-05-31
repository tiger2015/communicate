package com.pickupbill.action;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.GoodsDAO;
import com.dao.PickupBillDAO;
import com.entity.Goods;
import com.entity.PickupBill;
import com.opensymphony.xwork2.ActionSupport;

public class AddPickupBill extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6215085205058711610L;
	private PickupBill pickupBill;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
		GoodsDAO dao=(GoodsDAO) context.getBean("goodsDAO");
		PickupBillDAO pickupBillDAO=(PickupBillDAO) context.getBean("pickupbillDAO");
		String goodsId = this.pickupBill.getGoods().getGoodsId();
		List<Goods> result = dao.findGoodsByCondition(0, goodsId,
				null);
		Goods goods =(Goods) context.getBean("goods");
		if (result != null && result.size() > 0) {
			goods = (Goods) result.get(0);
			if (this.pickupBill.getGoodsAmount() < 0) {
				this.addFieldError("pickupBill.goodsAmount", "提货数量必须大于0!");
				return INPUT;
			} else if (goods.getGoodsAmount() < this.pickupBill
					.getGoodsAmount()) {
				this.addFieldError("pickupBill.goodsAmount", "提货数量不能超过库存!");
				return INPUT;
			}
		}
		pickupBillDAO.savePickupBill(pickupBill);
		return SUCCESS;
	}

	public PickupBill getPickupBill() {
		return pickupBill;
	}

	public void setPickupBill(PickupBill pickupBill) {
		this.pickupBill = pickupBill;
	}
}
