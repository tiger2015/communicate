package com.staff.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.StaffDAO;
import com.entity.Staff;
import com.opensymphony.xwork2.ActionSupport;

public class AddStaff extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8114275725091924249L;
	private Staff staff;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
		StaffDAO dao=(StaffDAO) context.getBean("staffDAO");
		dao.saveStaff(staff);
		return SUCCESS;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

}
