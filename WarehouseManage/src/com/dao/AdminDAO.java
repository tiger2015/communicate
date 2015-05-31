package com.dao;

import com.entity.Admin;
import com.util.BaseHibernateDAO;

public class AdminDAO extends BaseHibernateDAO {
	//private static final String CLASS_NAME = "Admin";

	// 通过用户名查找
	public Admin findAdminByAdminName(String adminName) {
		try {
			getSession().beginTransaction();
			Admin admin = (Admin) getSession().get(Admin.class, adminName);
			getSession().getTransaction().commit();
			return admin;
		} catch (Exception e) {
			return null;
		} finally {
			closeSession();
		}
	}

	public boolean setAdminIsOnline(String adminId, int isOnline) {
		try {
			getSession().beginTransaction();
			Admin admin = (Admin) getSession().get(Admin.class, adminId);
			if (admin != null) {
				admin.setAdminIsOnline(isOnline);
				getSession().update(admin);
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
