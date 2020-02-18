package com.poly.dao;

import java.util.List;

import com.poly.entities.AdminUser;

public interface AdminDAO {

	AdminUser findOne(String email, String password);

	List<AdminUser> findAll();

	AdminUser findOne(int id);

	boolean insert(AdminUser adminUser);

	boolean update(AdminUser adminUser);

	boolean delete(int id);
}
