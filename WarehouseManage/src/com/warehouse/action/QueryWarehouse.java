package com.warehouse.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.WarehouseDAO;
import com.entity.Warehouse;
import com.opensymphony.xwork2.ActionSupport;
import com.util.MySplitePage;

public class QueryWarehouse extends ActionSupport implements
		ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8986395038606798922L;
	private HttpServletRequest request;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String choice = request.getParameter("choice");
		String condition = request.getParameter("condition");
		String currentPage = request.getParameter("currentpage");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"config.xml");
		WarehouseDAO dao=(WarehouseDAO) context.getBean("warehouseDAO");
		if (choice == null || "".equals(choice.trim())) {
			choice = "0";
		}
		if (condition == null)
			condition = "";
		if (currentPage == null || "".equals(currentPage.trim())) {
			currentPage = "1";
		}
		MySplitePage mySplitePage;
		if (request.getSession().getAttribute("splitepage") == null) {
			mySplitePage = (MySplitePage) context.getBean("splitepage");
		} else {
			mySplitePage = (MySplitePage) request.getSession().getAttribute(
					"splitepage");
		}
		mySplitePage.setTotalRecord(dao
				.getWarehouseAmountByCondition(Integer.parseInt(choice),
						condition));
		mySplitePage.setCurrentPage(Integer.parseInt(currentPage));
		List<Warehouse> result=dao.findWarehouseByCondition(Integer.parseInt(choice), condition, mySplitePage);
		request.setAttribute("result", result);
		request.setAttribute("choice", choice);
		request.setAttribute("condition", condition);
		request.getSession().setAttribute("splitepage", mySplitePage);		
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

}
