package com.poly.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.poly.entities.ProDetail;
import com.poly.utils.HibernateUtil;

public class ProDetailDAOImpl implements ProDetailDAO {

	public List<ProDetail> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<ProDetail> query = builder.createQuery(ProDetail.class);
			query.from(ProDetail.class);
			List<ProDetail> list = session.createQuery(query).getResultList();
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

	public ProDetail findOne(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			ProDetail item = session.get(ProDetail.class, id);
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

	public boolean insert(ProDetail proDetail) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			proDetail.setCreatedAt(new Date());
			session.save(proDetail);
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

	public boolean update(ProDetail proDetail) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			proDetail.setUpdatedAt(new Date());
			session.update(proDetail);
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
			ProDetail object = session.get(ProDetail.class, id);
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
