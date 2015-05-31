package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.entity.PickupBill;
import com.entity.SupplyBill;
import com.util.BaseHibernateDAO;
import com.util.MySplitePage;

public class PickupBillDAO extends BaseHibernateDAO {
	private static final String CLASS_NAME = "PickupBill";

	public boolean savePickupBill(PickupBill pickupBill) {
		try {
			getSession().beginTransaction();
			getSession().save(pickupBill);
			getSession().getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			closeSession();
		}
	}

	// 数量
	public long getPickupBillAmountByCondition(int choice, String condition,
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
						+ " where STAFF_ID='" + condition + "'";
				break;
			case 2:
				queryString = "select count(*) from " + CLASS_NAME
						+ " where LADINGBILL_ID='" + condition + "'";
				break;
			case 3:
				queryString = "select count(*) from " + CLASS_NAME
						+ " where PICKUPDATE between  to_date('" + startDate
						+ "','yyyy-MM-dd')" + " and to_date('" + endDate
						+ "','yyyy-MM-dd')";
				break;
			case 4:
				queryString = "select count(*) from " + CLASS_NAME
						+ " where goods.warehouse.warehouseId='" + condition
						+ "'";
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

	public List<PickupBill> findPickupBillByCondition(int choice, String condition,
			String startDate, String endDate, MySplitePage splitePage) {
		try {
			String queryString = "";
			switch (choice) {
			case 0:
				queryString = "from " + CLASS_NAME + " where GOODS_ID='"
						+ condition + "' order by PICKUPDATE desc";
				break;
			case 1:
				queryString = "from " + CLASS_NAME + " where STAFF_ID='"
						+ condition + "' order by PICKUPDATE desc";
				break;
			case 2:
				queryString = "from " + CLASS_NAME + " where LADINGBILL_ID='"
						+ condition + "' order by PICKUPDATE desc";
				break;
			case 3:
				queryString = "from " + CLASS_NAME
						+ " where PICKUPDATE between  to_date('" + startDate
						+ "','yyyy-MM-dd')" + " and to_date('" + endDate
						+ "','yyyy-MM-dd') order by PICKUPDATE desc";
				break;
			case 4:
				queryString = "from " + CLASS_NAME
						+ " where goods.warehouse.warehouseId='" + condition
						+ "'";
				break;
			default:
				queryString = "from " + CLASS_NAME + " order by PICKUPDATE desc";
				break;
			}
			getSession().beginTransaction();
			Query query = (Query) getSession().createQuery(queryString);
			if (splitePage != null) {
				query.setMaxResults(MySplitePage.getPageSize());
				query.setFirstResult(MySplitePage.getPageSize()
						* (splitePage.getCurrentPage() - 1));
			}
			List<PickupBill> result = query.list();
			getSession().getTransaction().commit();
			return result;
		} catch (Exception e) {
			return new ArrayList<PickupBill>();
		} finally {
			closeSession();
		}
	}

	// 删除
	public boolean deletePickupBill(String pickupBillId) {
		try {
			getSession().beginTransaction();
			PickupBill pickupBill = (PickupBill) getSession().get(
					PickupBill.class, pickupBillId);
			if (pickupBill != null) {
				getSession().delete(pickupBill);
			}
			getSession().getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			closeSession();
		}
	}
}
