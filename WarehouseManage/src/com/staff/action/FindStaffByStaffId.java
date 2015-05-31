package com.staff.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.StaffDAO;
import com.entity.Staff;
import com.opensymphony.xwork2.ActionSupport;

public class FindStaffByStaffId extends ActionSupport implements
		ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6891441781675721714L;
	private HttpServletRequest request;
    
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String staffId=request.getParameter("staffid");
		String choice=request.getParameter("choice");
		String condition=request.getParameter("condition");
		String currentPage=request.getParameter("currentpage");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"config.xml");
		StaffDAO dao=(StaffDAO) context.getBean("staffDAO");
		List<Staff> result= dao.findStaffByCondition(0, staffId, null);
		Staff staff=(Staff) context.getBean("staff");
		if(result!=null&&result.size()>0)
			staff=(Staff) result.get(0);
		request.setAttribute("staff", staff);
		request.setAttribute("choice", choice);
		request.setAttribute("condition", condition);
		request.setAttribute("currentpage",currentPage);
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

}
