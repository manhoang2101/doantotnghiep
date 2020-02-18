package com.poly.dao;

import java.util.List;

import com.poly.entities.Category;

public interface CategoryDAO {

	List<Category> findAll();

	Category findOne(int id);

	boolean insert(Category category);

	boolean update(Category category);

	boolean delete(int id);
}
