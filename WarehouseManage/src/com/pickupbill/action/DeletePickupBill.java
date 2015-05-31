package com.pickupbill.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.PickupBillDAO;
import com.opensymphony.xwork2.ActionSupport;

public class DeletePickupBill extends ActionSupport implements
		ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4044452475083212528L;
	private HttpServletRequest request;
	private String choice;
	private String condition;
	private String startDate;
	private String endDate;
	private String currentPage;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		this.choice=request.getParameter("choice");
		this.condition=request.getParameter("condition");
		this.startDate=request.getParameter("startdate");
		this.endDate=request.getParameter("enddate");
		this.currentPage=request.getParameter("currentpage");
		String pickupBillId=request.getParameter("pickupbillid");
		ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
		PickupBillDAO dao=(PickupBillDAO) context.getBean("pickupbillDAO");
		boolean result=dao.deletePickupBill(pickupBillId);
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
