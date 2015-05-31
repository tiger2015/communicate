package com.provider.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.ProviderDAO;
import com.entity.Provider;
import com.opensymphony.xwork2.ActionSupport;
import com.util.MySplitePage;

public class QueryProvider extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2927943397152508468L;
	private HttpServletRequest request;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String choice = request.getParameter("choice");
		String condition = request.getParameter("condition");
		String currentPage = request.getParameter("currentpage");
		ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
		ProviderDAO dao=(ProviderDAO) context.getBean("providerDAO");
		if (choice == null || choice.equals(""))
			choice = "0";
		if (condition == null)
			condition = "";
		if (currentPage == null || currentPage.equals(""))
			currentPage = "1";
		MySplitePage mySplitePage;
		if (request.getSession().getAttribute("splitepage") == null) {
			mySplitePage = (MySplitePage) context.getBean("splitepage");
		} else {
			mySplitePage = (MySplitePage) request.getSession().getAttribute(
					"splitepage");
		}
		mySplitePage.setTotalRecord(dao.findProviderAmount(
				Integer.parseInt(choice), condition));
		mySplitePage.setCurrentPage(Integer.parseInt(currentPage));
		List<Provider> result = dao.findProviderByCondition(
				Integer.parseInt(choice), condition, mySplitePage);
		request.setAttribute("choice", choice);
		request.setAttribute("condition", condition);
		request.setAttribute("currentpage", currentPage);
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
