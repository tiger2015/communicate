package com.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.AdminDAO;
import com.entity.Admin;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = -6434128483294080524L;
	private Admin admin;
	private HttpServletRequest request;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return validateAdmin();
	}

	public String validateAdmin() {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"config.xml");
		AdminDAO dao=(AdminDAO) context.getBean("adminDAO");
		if (this.admin.getAdminName().trim().length() >= 6
				&& this.admin.getAdminName().trim().length() <= 16) {
			Admin admin = dao.findAdminByAdminName(this.admin
					.getAdminName());
			if (admin == null) {
				//System.out.println(this.admin.getAdminName());
				addFieldError("admin.adminName", "用户名不存在!");
				return INPUT;
			} else if (!admin.getAdminPassword().equals(
					this.admin.getAdminPassword())) {
				addFieldError("admin.adminPassword", "密码错误!");
				return INPUT;
			} else {
				request.getSession().setAttribute("username",
						admin.getAdminName());
				request.getSession().setAttribute("userrole",
						admin.getAdminRole());
				dao.setAdminIsOnline(admin.getAdminName(), 1);
				request.getSession().setAttribute(
						"onlineUserBindingListener",
						new OnlineUser(admin.getAdminName(), request
								.getSession().getId()));
			}
		}
		return SUCCESS;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}
}
