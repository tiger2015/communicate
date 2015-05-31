package com.warehouse.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.WarehouseDAO;
import com.entity.Warehouse;
import com.opensymphony.xwork2.ActionSupport;

public class ExportWarehouse extends ActionSupport implements
		ServletRequestAware {
	private HttpServletRequest request;
	private String fileName;
	private InputStream inputStream;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String choice = request.getParameter("choice");
		String condition = request.getParameter("condition");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"config.xml");
		WarehouseDAO dao=(WarehouseDAO) context.getBean("warehouseDAO");
		if (choice == null || "".equals(choice.trim())) {
			choice = "0";
		}
		if (condition == null)
			condition = "";
		List<Warehouse> result = dao.findWarehouseByCondition(
				Integer.parseInt(choice), condition, null);
		StringBuilder builder = new StringBuilder();
		builder.append("仓库编号\t仓库名称\t管理员工号\t管理员姓名\t管理员联系电话\n");
		for (Warehouse warehouse : result) {
			builder.append(warehouse.getWarehouseId() + "\t"
					+ warehouse.getWarehouseName() + "\t"
					+ warehouse.getStaff().getStaffId() + "\t"
					+ warehouse.getStaff().getStaffName() + "\t"
					+ warehouse.getStaff().getStaffPhonenumber() + "\n");
		}
		inputStream=new ByteArrayInputStream(builder.toString().getBytes("GBK"), 0, builder.toString().getBytes("GBK").length);
		return SUCCESS;
	}

	public String getFileName() {
		Calendar calendar = Calendar.getInstance();
		this.fileName = calendar.getTimeInMillis() + ".xls";
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

}
