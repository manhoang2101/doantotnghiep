package com.poly.dao;

import java.util.List;

import com.poly.entities.OrderDetail;

public interface OrderDetailDAO {

	List<OrderDetail> findAll();

	OrderDetail findOne(int id);

	boolean insert(OrderDetail orderDetail);

	boolean update(OrderDetail orderDetail);

	boolean delete(int id);
}
