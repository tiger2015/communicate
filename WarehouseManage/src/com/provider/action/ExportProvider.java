package com.provider.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.ProviderDAO;
import com.entity.Provider;
import com.opensymphony.xwork2.ActionSupport;

public class ExportProvider extends ActionSupport implements
		ServletRequestAware {
	private HttpServletRequest request;
	private String fileName;
	private InputStream inputStream;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String choice = request.getParameter("choice");
		String condition = request.getParameter("condition");
		ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
		ProviderDAO dao=(ProviderDAO) context.getBean("providerDAO");
		if (choice == null || choice.equals(""))
			choice = "0";
		if (condition == null)
			condition = "";
		List<Provider> result =dao.findProviderByCondition(
				Integer.parseInt(choice), condition, null);
		StringBuilder builder = new StringBuilder();
		builder.append("供应商编号\t供应商名称\t地址\t联系电话\t电子邮件\t联系人\n");
		for (Provider provider : result) {
			builder.append(provider.getProviderId() + "\t"
					+ provider.getProviderName() + "\t"
					+ provider.getProviderAddress() + "\t"
					+ provider.getProviderPhonenumber() + "\t"
					+ provider.getProviderEmail() + "\t"
					+ provider.getProviderContact() + "\n");

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
