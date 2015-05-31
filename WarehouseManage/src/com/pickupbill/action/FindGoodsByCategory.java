package com.pickupbill.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.GoodsDAO;
import com.entity.Goods;
import com.opensymphony.xwork2.ActionSupport;

public class FindGoodsByCategory extends ActionSupport implements
		ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -348056200136217603L;
	private HttpServletRequest request;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String category=request.getParameter("category");
		String staff=request.getParameter("staff");
		ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
		GoodsDAO dao=(GoodsDAO) context.getBean("goodsDAO");
		String unit="";
		Double goodsAmount=0.0;
		if(category==null||"".equals(category.trim()))
			category="1";		
		List<Goods> result=dao.findGoodsByCondition(2, category, null);
		Map<String,String> goodsMap=new HashMap<String,String>(0);
		if(result!=null&&result.size()>0){
			for(int i=0;i<result.size();i++)
			{
			
				Goods temp=(Goods) result.get(i);
				goodsMap.put(temp.getGoodsId(), temp.getGoodsName());
			}
			Goods index = (Goods) result.get(0);
			Iterator<String> iterator = goodsMap.keySet().iterator();
			/*while(iterator.hasNext()){
				System.out.println(iterator.next());
			}*/
			String goodsId = "";
			if (iterator.hasNext()) {
				goodsId = (String) iterator.next();
			} else {
				goodsId = index.getGoodsId();
			}
			
			for (Goods goods : result) {
				if (goods.getGoodsId().equals(goodsId)) {
					unit = goods.getUnit().getUnitName();
					goodsAmount = goods.getGoodsAmount();
					break;
				}
			}
		}	
		request.getSession().setAttribute("goods", goodsMap);
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
