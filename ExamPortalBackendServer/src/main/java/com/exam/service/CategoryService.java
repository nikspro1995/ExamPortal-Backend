package com.exam.service;

import java.util.Set;

import com.exam.entities.Category;

public interface CategoryService {

	public Category addCategory(Category category);
	
	public Category updateCategory(Category category);
	
	public Set<Category> getAllCategories();
	
	public Category getCategory(Long categoryId);
	
	public void deleteCategory(Long categoryId);
}
