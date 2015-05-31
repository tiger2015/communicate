package com.supplybill.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.SupplyBillDAO;
import com.entity.SupplyBill;
import com.opensymphony.xwork2.ActionSupport;
import com.util.MySplitePage;

public class QuerySupplyBill extends ActionSupport implements
		ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -98452333333817136L;
	private HttpServletRequest request;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String choice=request.getParameter("choice");
		String condition=request.getParameter("condition");
		String startDate=request.getParameter("startdate");
		String endDate=request.getParameter("enddate");
		String currentPage=request.getParameter("currentPage");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"config.xml");
		SupplyBillDAO dao=(SupplyBillDAO) context.getBean("supplybillDAO");
		if(choice==null||"".equals(choice.trim()))
			choice="0";
		if(condition==null)
			condition="";
		if(startDate!=null&&startDate.length()>10){
			startDate=startDate.substring(0, 10);
		}
		if(endDate!=null&&endDate.length()>10){
			endDate=endDate.substring(0, 10);
		}
		if(currentPage==null||"".equals(currentPage.trim())){
			currentPage="1";
		}
		MySplitePage splitePage;
		if(request.getSession().getAttribute("splitepage")==null){
			splitePage=(MySplitePage) context.getBean("splitepage");
		}else{
			splitePage=(MySplitePage) request.getSession().getAttribute("splitepage");
		}
		splitePage.setTotalRecord(dao.getSupplyBillAmountByCondition(Integer.parseInt(choice), condition, startDate, endDate));
		splitePage.setCurrentPage(Integer.parseInt(currentPage));
		List<SupplyBill> result=dao.findSupplyBillByCondition(Integer.parseInt(choice), condition, startDate, endDate, splitePage);
		/*SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		if(startDate.length()>0&&endDate.length()>0){
		Date start=dateFormat.parse(startDate);
		Date end=dateFormat.parse(endDate);
		request.setAttribute("startdate", start);
		request.setAttribute("enddate", end);
		}*/
		request.setAttribute("startdate", startDate);
		request.setAttribute("enddate", endDate);
		request.setAttribute("choice", choice);
		request.setAttribute("condition", condition);	
		request.setAttribute("result", result);
		request.getSession().setAttribute("splitepage", splitePage);
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

}
