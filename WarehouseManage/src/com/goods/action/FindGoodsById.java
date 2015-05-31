package com.goods.action;

import java.util.List;

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
	private static final long serialVersionUID = 4607233045789545918L;
	private HttpServletRequest request;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String choice=request.getParameter("choice");
		String condition=request.getParameter("condition");
		String currentPage=request.getParameter("currentpage");
		String goodsId=request.getParameter("goodsid");
		ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
		GoodsDAO dao=(GoodsDAO) context.getBean("goodsDAO");
		List<Goods> result=dao.findGoodsByCondition(0, goodsId, null);
		Goods goods=(Goods) context.getBean("goods");
		if(result!=null&&result.size()>0)
			goods=(Goods) result.get(0);
		request.setAttribute("choice", choice);
		request.setAttribute("condition", condition);
		request.setAttribute("currentpage", currentPage);
		request.setAttribute("goods", goods);
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

}
