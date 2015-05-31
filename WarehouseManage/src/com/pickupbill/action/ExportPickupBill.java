package com.pickupbill.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.PickupBillDAO;
import com.dao.SupplyBillDAO;
import com.entity.PickupBill;
import com.entity.SupplyBill;
import com.opensymphony.xwork2.ActionSupport;

public class ExportPickupBill extends ActionSupport implements
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7794374045851130989L;
	private String fileName;
	private InputStream inputStream;
	private HttpServletRequest request;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String choice = request.getParameter("choice");
		String condition = request.getParameter("condition");
		String startDate = request.getParameter("startdate");
		String endDate = request.getParameter("enddate");
		ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
		PickupBillDAO dao=(PickupBillDAO) context.getBean("pickupbillDAO");
		if (choice == null || "".equals(choice.trim()))
			choice = "0";
		if (condition == null)
			condition = "";
		if (startDate != null && startDate.length() > 10) {
			startDate = startDate.substring(0, 10);
		}
		if (endDate != null && endDate.length() > 10) {
			endDate = endDate.substring(0, 10);
		}
		List<PickupBill> result = dao
				.findPickupBillByCondition(Integer.parseInt(choice), condition,
						startDate, endDate, null);

		StringBuilder builder = new StringBuilder();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		builder.append("记录编号\t提货员编号\t提货员姓名\t物品编号\t物品类别\t物品名称\t提货数量\t物品单位\t提货日期\n");
		for (PickupBill pickupBill : result) {
			builder.append(pickupBill.getLadingBillId() + "\t"
					+ pickupBill.getStaff().getStaffId() + "\t"
					+ pickupBill.getStaff().getStaffName() + "\t"
					+ pickupBill.getGoods().getGoodsId() + "\t"
					+ pickupBill.getGoods().getCategory().getCategoryName()
					+ "\t" + pickupBill.getGoods().getGoodsName() + "\t"
					+ pickupBill.getGoodsAmount() + "\t"
					+ pickupBill.getGoods().getUnit().getUnitName() + "\t"
					+ dateFormat.format(pickupBill.getPickupDate()) + "\n");

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
