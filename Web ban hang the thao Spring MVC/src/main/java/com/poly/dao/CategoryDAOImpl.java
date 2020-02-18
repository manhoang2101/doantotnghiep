package com.poly.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.poly.entities.Category;
import com.poly.entities.User;
import com.poly.utils.HibernateUtil;

public class CategoryDAOImpl implements CategoryDAO {
	public List<Category> find(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String hql = "from Category u " + "where u.name = :name ";
		List<Category> list = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("name", name);
			list = query.getResultList();
			transaction.commit();
			return list;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	public List<Category> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Category> query = builder.createQuery(Category.class);
			query.from(Category.class);
			List<Category> list = session.createQuery(query).getResultList();
			transaction.commit();
			return list;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return null;
	}

	public Category findOne(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Category item = session.get(Category.class, id);
			transaction.commit();
			return item;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return null;
	}

	public boolean insert(Category category) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			category.setCreatedAt(new Date());
			session.save(category);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return false;
	}

	public boolean update(Category category) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			category.setUpdatedAt(new Date());
			session.update(category);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return false;
	}

	public boolean delete(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Category object = session.get(Category.class, id);
			session.delete(object);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return false;
	}
}
