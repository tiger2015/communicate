package com.staff.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.StaffDAO;
import com.entity.Staff;
import com.opensymphony.xwork2.ActionSupport;
import com.util.MySplitePage;

public class QueryStaff extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7282152783403655487L;
	private HttpServletRequest request;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String currentPage = request.getParameter("currentpage");
		String condition = request.getParameter("condition");
		String choice = request.getParameter("choice");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"config.xml");
		StaffDAO dao=(StaffDAO) context.getBean("staffDAO");
		if (currentPage == null || "".equals(currentPage.trim())) {
			currentPage = "0";
		}
		if (condition == null)
			condition = "";
		if (choice == null || "".equals(choice.trim())) {
			choice = "0";
		}
		MySplitePage mySplitePage;
		if (request.getSession().getAttribute("splitepage") == null) {
			mySplitePage = (MySplitePage) context.getBean("splitepage");
		} else {
			mySplitePage = (MySplitePage) request.getSession().getAttribute(
					"splitepage");
		}
		mySplitePage.setTotalRecord(dao.findStaffAmountByCondition(
				Integer.parseInt(choice), condition));
		mySplitePage.setCurrentPage(Integer.parseInt(currentPage));
		List<Staff> result =dao.findStaffByCondition(
				Integer.parseInt(choice), condition, mySplitePage);

		request.setAttribute("condition", condition);
		request.setAttribute("choice", choice);
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
