package com.warehouse.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.GoodsDAO;
import com.entity.Goods;
import com.opensymphony.xwork2.ActionSupport;
import com.util.MySplitePage;

public class QueryWarehouseGoods extends ActionSupport implements
		ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7196126355022266173L;
	private HttpServletRequest request;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String currentPage = request.getParameter("currentpage");
		String warehouseId = request.getParameter("warehouseid");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"config.xml");
		GoodsDAO dao=(GoodsDAO) context.getBean("goodsDAO");
		if (currentPage == null || "".equals(currentPage.trim())) {
			currentPage = "1";
		}
		if (warehouseId == null)
			warehouseId = "";
		MySplitePage mySplitePage;
		if (request.getSession().getAttribute("splitepage") == null) {
			mySplitePage = (MySplitePage) context.getBean("splitepage");
		} else {
			mySplitePage = (MySplitePage) request.getSession().getAttribute(
					"splitepage");
		}
		mySplitePage.setTotalRecord(dao.getGoodsAmountByCondition(3, warehouseId));
		mySplitePage.setCurrentPage(Integer.parseInt(currentPage));
		List<Goods> result = dao.findGoodsByCondition(3, warehouseId, mySplitePage);
		request.setAttribute("warehouseid", warehouseId);
		request.setAttribute("result", result);
		request.getSession().setAttribute("splitepage", mySplitePage);
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

}
