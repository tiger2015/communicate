package com.warehouse.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.WarehouseDAO;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteWarehouse extends ActionSupport implements
		ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7196653443165951303L;
	private HttpServletRequest request;
	private String choice;
	private String condition;
	private String currentPage;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		this.choice=request.getParameter("choice");
		this.condition=request.getParameter("condition");
		this.currentPage=request.getParameter("currentpage");
		String warehouseId=request.getParameter("warehouseid");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"config.xml");
		WarehouseDAO dao=(WarehouseDAO) context.getBean("warehouseDAO");
		boolean result=dao.deleteWarehouse(warehouseId);
		if(result)
			return SUCCESS;
		else
			return ERROR;
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

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

}
