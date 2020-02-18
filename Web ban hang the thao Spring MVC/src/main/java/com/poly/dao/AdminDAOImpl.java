package com.poly.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.poly.entities.AdminUser;
import com.poly.utils.HibernateUtil;

public class AdminDAOImpl implements AdminDAO {

	public AdminUser findOne(String email, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String hql = "from AdminUser u " + "where u.email = :email " + "and u.password = :password";
		AdminUser user;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("email", email);
			query.setParameter("password", password);
			user = (AdminUser) query.getSingleResult();
			transaction.commit();
			return user;
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

	public List<AdminUser> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<AdminUser> query = builder.createQuery(AdminUser.class);
			query.from(AdminUser.class);
			List<AdminUser> list = session.createQuery(query).getResultList();
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

	public AdminUser findOne(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			AdminUser item = session.get(AdminUser.class, id);
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

	public boolean insert(AdminUser adminUser) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			adminUser.setCreatedAt(new Date());
			session.save(adminUser);
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

	public boolean update(AdminUser adminUser) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			adminUser.setUpdatedAt(new Date());
			session.update(adminUser);
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
			AdminUser object = session.get(AdminUser.class, id);
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
