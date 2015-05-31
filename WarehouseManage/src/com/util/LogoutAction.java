package com.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.AdminDAO;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements ServletRequestAware{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3460108464335206253L;
	private HttpServletRequest request;
    
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String adminId=(String) request.getSession().getAttribute("username");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"config.xml");
		AdminDAO dao=(AdminDAO) context.getBean("adminDAO");
		if(adminId!=null)
			dao.setAdminIsOnline(adminId, 0);
		request.getSession().removeAttribute("username");
		request.getSession().removeAttribute("userrole");
		request.getSession().invalidate();
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request=arg0;
	}

}
