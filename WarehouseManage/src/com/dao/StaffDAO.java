package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.entity.Staff;
import com.util.BaseHibernateDAO;
import com.util.MySplitePage;

public class StaffDAO extends BaseHibernateDAO {
	private static final String CLASS_NAME = "Staff";

	public boolean saveStaff(Staff staff) {
		try {
			getSession().beginTransaction();
			getSession().save(staff);
			getSession().getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			closeSession();
		}
	}

	// 查找数量
	public long findStaffAmountByCondition(int choice, String condition) {
		try {
			String queryString = "";
			if (choice == 0) {
				queryString = "select count(*) from " + CLASS_NAME
						+ " where STAFF_ID='" + condition + "'";
			} else if (choice == 1) {
				queryString = "select count(*) from " + CLASS_NAME
						+ " where STAFF_NAME like'%" + condition + "%'";
			} else if (choice == 2) {
				queryString = "select count(*) from " + CLASS_NAME
						+ " where STAFF_ROLE =" + condition;
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

	// 查找职工信息
	public List<Staff> findStaffByCondition(int choice, String condition,
			MySplitePage splitePage) {
		try {
			String queryString = "";
			if (choice == 0) {
				queryString = "from " + CLASS_NAME + " where STAFF_ID='"
						+ condition + "'";
			} else if (choice == 1) {
				queryString = "from " + CLASS_NAME + " where STAFF_NAME like'%"
						+ condition + "%'";
			} else if (choice == 2) {
				queryString = "from " + CLASS_NAME + " where STAFF_ROLE ="
						+ condition;
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
			return new ArrayList<Staff>();
		} finally {
			closeSession();
		}
	}

	// 更新职工信息
	public boolean updateStaff(Staff staff) {
		try {
			getSession().beginTransaction();
			if (staff != null) {
				Staff temp = (Staff) getSession().get(Staff.class,
						staff.getStaffId());
				if (temp != null) {
					temp.setStaffName(staff.getStaffName());
					temp.setStaffSex(staff.getStaffSex());
					temp.setStaffBirthday(staff.getStaffBirthday());
					temp.setStaffRole(staff.getStaffRole());
					temp.setStaffPhonenumber(staff.getStaffPhonenumber());
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

	// 删除信息
	public boolean deleteStaff(String staffId) {
		try {
			getSession().beginTransaction();
			Staff staff = (Staff) getSession().get(Staff.class, staffId);
			if (staff != null)
				getSession().delete(staff);
			getSession().getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			closeSession();
		}
	}

	// 查找职工号和职工名称的Map
	public Map findStaffMap() {
		Map map = new HashMap<String, String>(0);
		List result = findStaffByCondition(2, "1", null);
		if (result != null) {
			for (int i = 0; i < result.size(); i++) {
				Staff staff = (Staff) result.get(i);
				map.put(staff.getStaffId(), staff.getStaffName());
			}
		}
		return map;
	}
}
