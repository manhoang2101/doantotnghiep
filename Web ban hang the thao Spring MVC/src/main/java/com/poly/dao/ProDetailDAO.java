package com.poly.dao;

import java.util.List;

import com.poly.entities.ProDetail;

public interface ProDetailDAO {

	List<ProDetail> findAll();

	ProDetail findOne(int id);

	boolean insert(ProDetail proDetail);

	boolean update(ProDetail proDetail);

	boolean delete(int id);
}
