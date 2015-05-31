package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.entity.Category;
import com.util.BaseHibernateDAO;

public class CategoryDAO extends BaseHibernateDAO {
	public static final String CLASS_NAME = "Category";
	public static final String TABLE_NAME = "category";

	// 获取所有物品类别
	public List<Category> findAllCategory() {
		try {
			getSession().beginTransaction();
			Query query = getSession().createQuery("from " + CLASS_NAME);
			List<Category> result = query.list();
			getSession().getTransaction().commit();
			return result;
		} catch (Exception e) {
			return null;
		} finally {
			closeSession();
		}
	}

	public Map<Integer, String> findAllCategoryMap() {
		Map<Integer, String> categoryMap = new HashMap<Integer, String>(0);
		List<Category> result = findAllCategory();
		if (result != null) {
			for (int i = 0; i < result.size(); i++) {
				Category temp = (Category) result.get(i);
				categoryMap.put(temp.getCategoryId(), temp.getCategoryName());
			}
		}
		return categoryMap;
	}
}
