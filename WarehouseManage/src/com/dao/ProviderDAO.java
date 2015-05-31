package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.entity.Provider;
import com.util.BaseHibernateDAO;
import com.util.MySplitePage;

public class ProviderDAO extends BaseHibernateDAO {
	private static final String CLASS_NAME = "Provider";

	// 获取所有物品供应商
	public List<Provider> findAllProvider() {
		try {
			getSession().beginTransaction();
			Query query = getSession().createQuery("from " + CLASS_NAME);
			List<Provider> result = query.list();
			getSession().getTransaction().commit();
			return result;
		} catch (Exception e) {
			return null;
		} finally {
			closeSession();
		}
	}

	public Map<String, String> findAllProviderMap() {
		Map<String, String> providerMap = new HashMap<String, String>(0);
		List<Provider> result = findAllProvider();
		if (result != null) {
			for (int i = 0; i < result.size(); i++) {
				Provider temp = (Provider) result.get(i);
				providerMap.put(temp.getProviderId(), temp.getProviderName());
			}
		}
		return providerMap;
	}

	// 添加新的供应商
	public boolean saveProvider(Provider provider) {
		try {
			getSession().beginTransaction();
			if (provider != null)
				getSession().save(provider);
			getSession().getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			closeSession();
		}
	}

	// 查询供应商
	public long findProviderAmount(int choice, String condition) {
		try {
			String queryString = "";
			if (choice == 0) {
				queryString = "select count(*) from " + CLASS_NAME
						+ " where PROVIDER_ID='" + condition + "'";
			} else if (choice == 1) {
				queryString = "select count(*) from " + CLASS_NAME
						+ " where PROVIDER_NAME like '%" + condition + "%'";
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

	public List<Provider> findProviderByCondition(int choice, String condition,
			MySplitePage splitePage) {
		try {
			String queryString = "";
			if (choice == 0) {
				queryString = "from " + CLASS_NAME + " where PROVIDER_ID='"
						+ condition + "'";
			} else if (choice == 1) {
				queryString = "from " + CLASS_NAME
						+ " where PROVIDER_NAME like '%" + condition + "%'";
			} else {
				queryString = "from " + CLASS_NAME;
			}
			getSession().beginTransaction();
			Query query = getSession().createQuery(queryString);
			List<Provider> result = query.list();
			getSession().getTransaction().commit();
			return result;
		} catch (Exception e) {
			return new ArrayList<Provider>();
		} finally {
			closeSession();
		}
	}

	// 更新
	public boolean updateProvider(Provider provider) {
		try {
			getSession().beginTransaction();
			if (provider != null) {
				Provider temp = (Provider) getSession().get(Provider.class,
						provider.getProviderId());
				if (temp != null) {
					temp.setProviderName(provider.getProviderName());
					temp.setProviderContact(provider.getProviderContact());
					temp.setProviderAddress(provider.getProviderAddress());
					temp.setProviderEmail(provider.getProviderEmail());
					temp.setProviderPhonenumber(provider
							.getProviderPhonenumber());
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
	//删除
	public boolean deleteProvider(String providerId){
		try{
			getSession().beginTransaction();
			Provider provider=(Provider) getSession().get(Provider.class, providerId);
			if(provider!=null)
				getSession().delete(provider);
			getSession().getTransaction().commit();
			return true;
		}catch(Exception e){
			return false;
		}finally{
			closeSession();
		}
	}
}
