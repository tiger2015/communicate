package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.entity.Unit;
import com.util.BaseHibernateDAO;

public class UnitDAO extends BaseHibernateDAO {
	public static final String CLASS_NAME = "Unit";
	public static final String TABLE_NAME = "unit";
   //查询所有物品单位
	public List findAllUnit() {
		try {
			getSession().beginTransaction();
			Query query = getSession().createQuery("from " + CLASS_NAME);
			List result = query.list();
			getSession().getTransaction().commit();
			return result;
		} catch (Exception e) {
			return null;
		} finally {
			closeSession();
		}
	}
	
	public Map findAllUnitMap(){
		Map unitMap=new HashMap<Integer,String>(0);
		List result=findAllUnit();
		if(result!=null){
			for(int i=0;i<result.size();i++){
				Unit temp=(Unit) result.get(i);
				unitMap.put(temp.getUnitId(), temp.getUnitName());
			}
		}
		return unitMap;
	}
}
