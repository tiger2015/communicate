package com.warehouse.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.WarehouseDAO;
import com.entity.Warehouse;
import com.opensymphony.xwork2.ActionSupport;

public class FindWarehouseById extends ActionSupport implements
		ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2059456197731424355L;
	private HttpServletRequest request;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String choice = request.getParameter("choice");
		String condition = request.getParameter("condition");
		String currentPage = request.getParameter("currentpage");
		String warehouseId = request.getParameter("warehouseid");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"config.xml");
		WarehouseDAO dao=(WarehouseDAO) context.getBean("warehouseDAO");
		List<Warehouse> list = dao.findWarehouseByCondition(0, warehouseId,
				null);
		Warehouse warehouse = (Warehouse) context.getBean("warehouse");
		if (list != null && list.size() > 0) {
			warehouse = (Warehouse) list.get(0);
		}
		request.setAttribute("choice", choice);
		request.setAttribute("condition", condition);
		request.setAttribute("currentpage", currentPage);
		request.setAttribute("warehouse", warehouse);
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

}
