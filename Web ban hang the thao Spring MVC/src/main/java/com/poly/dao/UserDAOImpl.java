package com.poly.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.poly.entities.User;
import com.poly.utils.HibernateUtil;

public class UserDAOImpl implements UserDAO {

	@SuppressWarnings("unchecked")
	public List<User> find(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String hql = "from User u " + "where u.email = :email ";
		List<User> list = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("email", email);
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

	public User findOne(String email, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String hql = "from User u " + "where u.email = :email " + "and u.password = :password";
		Object obj = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("email", email);
			query.setParameter("password", password);
			obj = query.getSingleResult();
			transaction.commit();
			return (User) obj;
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

	public List<User> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			query.from(User.class);
			List<User> list = session.createQuery(query).getResultList();
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

	public User findOne(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			User item = session.get(User.class, id);
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

	public boolean insert(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			user.setCreatedAt(new Date());
			session.save(user);
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

	public boolean update(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			user.setUpdatedAt(new Date());
			session.update(user);
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
			User object = session.get(User.class, id);
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
