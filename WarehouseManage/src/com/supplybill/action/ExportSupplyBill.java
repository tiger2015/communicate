package com.supplybill.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.SupplyBillDAO;
import com.entity.SupplyBill;
import com.opensymphony.xwork2.ActionSupport;
import com.util.MySplitePage;

public class ExportSupplyBill extends ActionSupport implements
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6587290133516142117L;
	private HttpServletRequest request;
	private InputStream excelStream;
	private String fileName;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String choice = request.getParameter("choice");
		String condition = request.getParameter("condition");
		String startDate = request.getParameter("startdate");
		String endDate = request.getParameter("enddate");
		String currentPage = request.getParameter("currentPage");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"config.xml");
		SupplyBillDAO dao=(SupplyBillDAO) context.getBean("supplybillDAO");
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
		if (currentPage == null || "".equals(currentPage.trim())) {
			currentPage = "1";
		}
		List<SupplyBill> result = dao
				.findSupplyBillByCondition(Integer.parseInt(choice), condition,
						startDate, endDate, null);
		StringBuilder strBuilder = new StringBuilder();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 表格头部
		strBuilder
				.append("记录编号\t供应商编号\t供应商名称\t物品编号\t物品类别\t物品名称\t供货数量\t物品单位\t供货日期\n");
		for (SupplyBill supplyBill : result) {
			strBuilder.append(supplyBill.getSupplyBillId() + "\t"
					+ supplyBill.getProvider().getProviderId() + "\t"
					+ supplyBill.getProvider().getProviderName() + "\t"
					+ supplyBill.getGoods().getGoodsId() + "\t"
					+ supplyBill.getGoods().getCategory().getCategoryName()+"\t"
					+ supplyBill.getGoods().getGoodsName() + "\t"
					+ supplyBill.getGoodsAmount() + "\t"
					+ supplyBill.getGoods().getUnit().getUnitName() + "\t"
					+ format.format(supplyBill.getSupplyDate()) + "\n");
		}
		excelStream = new ByteArrayInputStream(strBuilder.toString().getBytes(
				"GBK"), 0, strBuilder.toString().getBytes("GBK").length);
		/*request.setAttribute("startdate", startDate);
		request.setAttribute("enddate", endDate);
		request.setAttribute("choice", choice);
		request.setAttribute("condition", condition);
		request.setAttribute("result", result);
		request.getSession().setAttribute("splitepage", splitePage);*/
		return SUCCESS;

	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getFileName() throws UnsupportedEncodingException {
		// 用时间作为文件的民称
		Calendar calendar = Calendar.getInstance();
		this.fileName = calendar.getTimeInMillis() + ".xls";
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

}
