package com.staff.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.StaffDAO;
import com.entity.Staff;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateStaff extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4987292171373982749L;
	private String choice = "0";
	private String condition = "";
	private String currentPage = "1";
	private Staff staff;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"config.xml");
		StaffDAO dao=(StaffDAO) context.getBean("staffDAO");
		dao.updateStaff(staff);
		return SUCCESS;
	}

	public String getChoice() {
		if (choice == null || "".equals(choice))
			choice = "0";
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getCondition() {
		if (condition == null)
			condition = "";
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

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
}
