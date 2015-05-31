package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.entity.SupplyBill;
import com.util.BaseHibernateDAO;
import com.util.MySplitePage;

public class SupplyBillDAO extends BaseHibernateDAO {
	private static final String CLASS_NAME = "SupplyBill";

	public boolean saveSupplyBill(SupplyBill supplyBill) {
		try {
			getSession().beginTransaction();
			getSession().save(supplyBill);
			getSession().getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			closeSession();
		}
	}

	// 数量
	public long getSupplyBillAmountByCondition(int choice, String condition,
			String startDate, String endDate) {
		try {
			String queryString = "";
			switch (choice) {
			case 0:
				queryString = "select count(*) from " + CLASS_NAME
						+ " where GOODS_ID='" + condition + "'";
				break;
			case 1:
				queryString = "select count(*) from " + CLASS_NAME
						+ " where PROVIDER_ID='" + condition + "'";
				break;
			case 2:
				queryString = "select count(*) from " + CLASS_NAME
						+ " where SUPPLYBILL_ID='" + condition + "'";
				break;
			case 3:
				queryString = "select count(*) from " + CLASS_NAME
						+ " where SUPPLYDATE between  to_date('" + startDate
						+ "','yyyy-MM-dd')" + " and to_date('" + endDate
						+ "','yyyy-MM-dd')";
				break;
			case 4:
				queryString = "select count(*) from " + CLASS_NAME
				+ " where goods.warehouse.warehouseId='" + condition + "'";
				break;
			default:
				queryString = "select count(*) from " + CLASS_NAME;
				break;
			}
			getSession().beginTransaction();
			Query query = (Query) getSession().createQuery(queryString);
			long result = (long) query.uniqueResult();
			getSession().getTransaction().commit();
			return result;
		} catch (Exception e) {
			return 0;
		} finally {
			closeSession();
		}
	}

	public List<SupplyBill> findSupplyBillByCondition(int choice, String condition,
			String startDate, String endDate, MySplitePage splitePage) {
		try {
			String queryString = "";
			switch (choice) {
			case 0:
				queryString = "from " + CLASS_NAME + " where GOODS_ID='"
						+ condition + "' order by supplydate desc";
				break;
			case 1:
				queryString = "from " + CLASS_NAME + " where PROVIDER_ID='"
						+ condition + "' order by supplydate desc";
				break;
			case 2:
				queryString = "from " + CLASS_NAME + " where SUPPLYBILL_ID='"
						+ condition + "' order by supplydate desc";
				break;
			case 3:
				queryString = "from " + CLASS_NAME
						+ " where SUPPLYDATE between  to_date('" + startDate
						+ "','yyyy-MM-dd')" + " and to_date('" + endDate
						+ "','yyyy-MM-dd') order by supplydate desc";
				break;
			case 4:
				queryString = "from " + CLASS_NAME + " where  goods.warehouse.warehouseId='"
						+ condition + "' order by supplydate desc";
				break;
			default:
				queryString = "from " + CLASS_NAME + " order by supplydate desc";
				break;
			}
			getSession().beginTransaction();
			Query query = (Query) getSession().createQuery(queryString);
			if (splitePage != null) {
				query.setMaxResults(MySplitePage.getPageSize());
				query.setFirstResult(MySplitePage.getPageSize()
						* (splitePage.getCurrentPage() - 1));
			}
			List result = query.list();
			getSession().getTransaction().commit();
			return result;
		} catch (Exception e) {
			return new ArrayList<SupplyBill>();
		} finally {
			closeSession();
		}
	}

	// 删除
	public boolean deleteSupplyBill(String supplyBillId) {
		try {
			getSession().beginTransaction();
			SupplyBill supplyBill = (SupplyBill) getSession().get(
					SupplyBill.class, supplyBillId);
			if (supplyBill != null)
				getSession().delete(supplyBill);
			getSession().getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			closeSession();
		}
	}
}
