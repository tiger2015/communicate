package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.entity.Warehouse;
import com.util.BaseHibernateDAO;
import com.util.MySplitePage;

public class WarehouseDAO extends BaseHibernateDAO {
	private static final String CLASS_NAME = "Warehouse";

	public List findAllWarehouse() {
		try {
			getSession().beginTransaction();
			Query query = getSession().createQuery(" from " + CLASS_NAME);
			List result = query.list();
			getSession().getTransaction().commit();
			return result;
		} catch (Exception e) {
			return null;
		} finally {
			closeSession();
		}
	}

	public Map findAllWarehouseMap() {
		Map map = new HashMap<String, String>(0);
		List list = findAllWarehouse();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Warehouse warehouse = (Warehouse) list.get(i);
				map.put(warehouse.getWarehouseId(),
						warehouse.getWarehouseName());
			}
		}
		return map;
	}

	// 新增仓库
	public boolean saveWarehouse(Warehouse warehouse) {
		try {
			getSession().beginTransaction();
			getSession().save(warehouse);
			getSession().getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			closeSession();
		}
	}

	// 条件查找数量
	public long getWarehouseAmountByCondition(int choice, String condition) {
		try {
			String queryString = "";
			if (choice == 0) {
				queryString = "select count(*) from " + CLASS_NAME
						+ " where WAREHOUSE_ID='" + condition + "'";
			} else if (choice == 1) {
				queryString = "select count(*) from " + CLASS_NAME
						+ " where WAREHOUSE_NAME like'%" + condition + "%'";
			} else if (choice == 2) {
				queryString = "select count(*) from " + CLASS_NAME
						+ " where STAFF_ID='" + condition + "'";
			} else {
				queryString = "select count(*) from " + CLASS_NAME;
			}
			getSession().beginTransaction();
			Query query = getSession().createQuery(queryString);
			long result = (long) query.uniqueResult();
			getSession().getTransaction().commit();
			return result;
		} catch (Exception e) {
			return 0;
		} finally {
			closeSession();
		}
	}

	// 条件分页查询
	public List<Warehouse> findWarehouseByCondition(int choice, String condition,
			MySplitePage splitePage) {
		try {
			String queryString = "";
			if (choice == 0) {
				queryString = "from " + CLASS_NAME + " where WAREHOUSE_ID='"
						+ condition + "'";
			} else if (choice == 1) {
				queryString = "from " + CLASS_NAME
						+ " where WAREHOUSE_NAME like'%" + condition + "%'";
			} else if (choice == 2) {
				queryString = "from " + CLASS_NAME + " where STAFF_ID='"
						+ condition + "'";
			} else {
				queryString = "from " + CLASS_NAME;
			}
			getSession().beginTransaction();
			Query query = getSession().createQuery(queryString);
			if (splitePage != null) {
				query.setMaxResults(MySplitePage.getPageSize());
				query.setFirstResult(MySplitePage.getPageSize()
						* (splitePage.getCurrentPage() - 1));
			}
			List result = query.list();
			getSession().getTransaction().commit();
			return result;

		} catch (Exception e) {
			return new ArrayList<Warehouse>();
		} finally {
			closeSession();
		}
	}

	// 更新
	public boolean updateWarehouse(Warehouse warehouse) {
		try {
			getSession().beginTransaction();
			if (warehouse != null) {
				Warehouse temp = (Warehouse) getSession().get(Warehouse.class,
						warehouse.getWarehouseId());
				if (temp != null) {
					temp.setWarehouseName(warehouse.getWarehouseName());
					temp.setStaff(warehouse.getStaff());
					getSession().update(temp);
				}
			}
			getSession().getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			closeSession();
		}
	}

	// 删除
	public boolean deleteWarehouse(String warehouseId) {
		try {
			getSession().beginTransaction();
			Warehouse warehouse = (Warehouse) getSession().get(Warehouse.class,
					warehouseId);
			if (warehouse != null)
				getSession().delete(warehouse);
			getSession().getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			closeSession();
		}
	}
}
