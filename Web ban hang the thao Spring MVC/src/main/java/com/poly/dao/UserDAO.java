package com.poly.dao;

import java.util.List;

import com.poly.entities.User;

public interface UserDAO {

	List<User> findAll();

	User findOne(int id);

	boolean insert(User user);

	boolean update(User user);

	boolean delete(int id);
}
