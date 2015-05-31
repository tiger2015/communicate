package com.goods.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.GoodsDAO;
import com.entity.Goods;
import com.opensymphony.xwork2.ActionSupport;

public class ExportGoods extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5000867608102257122L;
	private String fileName;
	private InputStream inputStream;
	private HttpServletRequest request;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String condition = request.getParameter("condition");
		String choice = request.getParameter("choice");
		if (condition == null)
			condition = "";
		if (choice == null || "".equals(choice.trim())) {
			choice = "0";
		}
		ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
		GoodsDAO dao=(GoodsDAO) context.getBean("goodsDAO");
		List<Goods> result = dao.findGoodsByCondition(
				Integer.parseInt(choice), condition, null);
		StringBuilder builder = new StringBuilder();
		builder.append("物品编号\t物品名称\t物品类别\t物品库存\t物品单位\t物品单价\t所在仓库\t最大库存\t警戒库存\n");
		for (Goods goods : result) {
			builder.append(goods.getGoodsId() + "\t" + goods.getGoodsName()
					+ "\t" + goods.getCategory().getCategoryName() + "\t"
					+ goods.getGoodsAmount() + "\t"
					+ goods.getUnit().getUnitName() + "\t"
					+ goods.getGoodsPrice() + "\t"
					+ goods.getWarehouse().getWarehouseName() + "\t"
					+ goods.getGoodsMaxAmount() + "\t"
					+ goods.getGoodsMinAmount() + "\n");
		}
		inputStream = new ByteArrayInputStream(builder.toString().getBytes(
				"GBK"), 0, builder.toString().getBytes().length);
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
