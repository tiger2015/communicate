package com.pickupbill.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.GoodsDAO;
import com.entity.Goods;
import com.opensymphony.xwork2.ActionSupport;

public class FindGoodsById extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3568872781834513928L;
	private HttpServletRequest request;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String category = request.getParameter("category");
		String staff = request.getParameter("staff");
		String goods = request.getParameter("goods");
		String unit = "";
		Double goodsAmount=0.0;
		ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
		GoodsDAO dao=(GoodsDAO) context.getBean("goodsDAO");
		if (category == null || "".equals(category.trim()))
			category = "1";
		List<Goods> result = dao.findGoodsByCondition(2, category, null);
		Map<String, String> goodsMap = new HashMap<String, String>();
		if (result != null) {
			for (int i = 0; i < result.size(); i++) {

				Goods temp = (Goods) result.get(i);
				if (temp.getGoodsId().equals(goods)) {
					unit = temp.getUnit().getUnitName();
					goodsAmount=temp.getGoodsAmount();
				}
				goodsMap.put(temp.getGoodsId(), temp.getGoodsName());
			}
		}
		request.getSession().setAttribute("goods", goodsMap);
		request.getSession().setAttribute("selectgoods", goods);
		request.getSession().setAttribute("category", category);
		request.getSession().setAttribute("staff", staff);
		request.getSession().setAttribute("unit", unit);
		request.getSession().setAttribute("goodsamount", goodsAmount);
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

}
