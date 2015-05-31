package com.staff.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.StaffDAO;
import com.entity.Staff;
import com.opensymphony.xwork2.ActionSupport;

public class ExportStaff extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6141803286305097919L;
	private HttpServletRequest request;
	private String fileName;
	private InputStream inputStream;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String condition = request.getParameter("condition");
		String choice = request.getParameter("choice");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"config.xml");
		StaffDAO dao = (StaffDAO) context.getBean("staffDAO");
		if (condition == null)
			condition = "";
		if (choice == null || "".equals(choice.trim())) {
			choice = "0";
		}
		List<Staff> result = dao.findStaffByCondition(Integer.parseInt(choice),
				condition, null);
		StringBuilder builder = new StringBuilder();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		builder.append("职工号\t姓名\t性别\t出生日期\t职位\t联系电话\n");
		for (Staff staff : result) {
			builder.append(staff.getStaffId() + "\t" + staff.getStaffName()
					+ "\t");
			int sex = staff.getStaffSex();
			if (sex == 0) {
				builder.append("男\t");
			} else {
				builder.append("女\t");
			}
			builder.append(format.format(staff.getStaffBirthday()) + "\t");
			int role = staff.getStaffRole();
			if (role == 0) {
				builder.append("提货员\t");
			} else {
				builder.append("仓库管理员\t");
			}
			builder.append(staff.getStaffPhonenumber() + "\n");
		}
		inputStream = new ByteArrayInputStream(builder.toString().getBytes(
				"GBK"), 0, builder.toString().getBytes("GBK").length);

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
