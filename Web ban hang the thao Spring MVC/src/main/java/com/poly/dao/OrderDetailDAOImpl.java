package com.poly.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.poly.entities.OrderDetail;
import com.poly.utils.HibernateUtil;

public class OrderDetailDAOImpl implements OrderDetailDAO {

	public List<OrderDetail> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<OrderDetail> query = builder.createQuery(OrderDetail.class);
			query.from(OrderDetail.class);
			List<OrderDetail> list = session.createQuery(query).getResultList();
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

	public OrderDetail findOne(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			OrderDetail item = session.get(OrderDetail.class, id);
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

	public boolean insert(OrderDetail orderDetail) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			orderDetail.setCreatedAt(new Date());
			session.save(orderDetail);
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

	public boolean update(OrderDetail orderDetail) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			orderDetail.setUpdatedAt(new Date());
			session.update(orderDetail);
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
			OrderDetail object = session.get(OrderDetail.class, id);
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
