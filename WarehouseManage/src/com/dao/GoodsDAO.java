package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.entity.Goods;
import com.util.BaseHibernateDAO;
import com.util.MySplitePage;

public class GoodsDAO extends BaseHibernateDAO {
	private static final String CLASS_NAME = "Goods";

	public boolean saveGoods(Goods goods) {
		try {
			getSession().beginTransaction();
			getSession().save(goods);
			getSession().getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			closeSession();
		}
	}

	// 查找数量
	public long getGoodsAmountByCondition(int choice, String condition) {
		try {
			String queryString = "";
			if (choice == 0) {
				queryString = "select count(*) from " + CLASS_NAME
						+ " where GOODS_ID='" + condition + "'";
			} else if (choice == 1) {
				queryString = "select count(*) from " + CLASS_NAME
						+ " where GOODS_NAME like '%" + condition + "%'";
			} else if (choice == 2) {
				queryString = "select count(*) from " + CLASS_NAME
						+ " where GOODS_CATEGORY =" + condition;
			} else if (choice == 3) {
				queryString = "select count(*) from " + CLASS_NAME
						+ " where GOODS_WAREHOUSE ='" + condition+"'";
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

	public List<Goods> findGoodsByCondition(int choice, String condition,
			MySplitePage splitePage) {
		try {
			String queryString = "";
			if (choice == 0) {
				queryString = "from " + CLASS_NAME + " where GOODS_ID='"
						+ condition + "'";
			} else if (choice == 1) {
				queryString = "from " + CLASS_NAME
						+ " where GOODS_NAME like '%" + condition + "%'";
			} else if (choice == 2) {
				queryString = "from " + CLASS_NAME + " where GOODS_CATEGORY ="
						+ condition;
			} else if (choice == 3) {
				queryString = "from " + CLASS_NAME
						+ " where GOODS_WAREHOUSE ='" + condition+"'";
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
			List<Goods> result = query.list();
			getSession().getTransaction().commit();
			return result;
		} catch (Exception e) {
			return new ArrayList<Goods>();
		} finally {
			closeSession();
		}
	}

	public boolean updateGoods(Goods goods) {
		try {
			getSession().beginTransaction();
			if (goods != null) {
				Goods temp = (Goods) getSession().get(Goods.class,
						goods.getGoodsId());
				if (temp != null) {
					temp.setCategory(goods.getCategory());
					//temp.setGoodsAmount(goods.getGoodsAmount());
					temp.setGoodsName(goods.getGoodsName());
					temp.setGoodsPrice(goods.getGoodsPrice());
					temp.setGoodsMaxAmount(goods.getGoodsMaxAmount());
					temp.setGoodsMinAmount(goods.getGoodsMinAmount());
					temp.setUnit(goods.getUnit());
					temp.setWarehouse(goods.getWarehouse());
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

	// 删除物品信息
	public boolean deleteGoods(String goodsId) {
		try {
			getSession().beginTransaction();
			Goods goods = (Goods) getSession().get(Goods.class, goodsId);
			if (goods != null)
				getSession().delete(goods);
			getSession().getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			closeSession();
		}
	}

}
