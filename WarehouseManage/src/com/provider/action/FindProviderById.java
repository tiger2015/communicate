package com.provider.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.ProviderDAO;
import com.entity.Provider;
import com.opensymphony.xwork2.ActionSupport;

public class FindProviderById extends ActionSupport implements
		ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3326298275343098064L;
	private HttpServletRequest request;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String choice = request.getParameter("choice");
		String condition = request.getParameter("condition");
		String currentPage = request.getParameter("currentpage");
		String providerId = request.getParameter("providerid");
		ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
		ProviderDAO dao=(ProviderDAO) context.getBean("providerDAO");
		List<Provider> list = dao.findProviderByCondition(0, providerId,
				null);
		Provider provider = (Provider) context.getBean("provider");
		if (list != null && list.size() > 0) {
			provider = (Provider) list.get(0);
		}
		request.setAttribute("choice", choice);
		request.setAttribute("condition", condition);
		request.setAttribute("currentpage", currentPage);
		request.setAttribute("provider", provider);
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

}
