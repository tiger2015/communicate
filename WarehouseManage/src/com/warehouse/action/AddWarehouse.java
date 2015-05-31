package com.warehouse.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.WarehouseDAO;
import com.entity.Warehouse;
import com.opensymphony.xwork2.ActionSupport;

public class AddWarehouse extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7113720079084561274L;
	private Warehouse warehouse;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"config.xml");
		WarehouseDAO dao=(WarehouseDAO) context.getBean("warehouseDAO");
		dao.saveWarehouse(warehouse);
		return SUCCESS;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

}
