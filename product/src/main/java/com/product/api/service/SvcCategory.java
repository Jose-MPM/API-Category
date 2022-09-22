package com.product.api.service;

import java.util.List;

import com.product.api.entity.Category;

public interface SvcCategory {
	
	/** Método que regresa todas las categorías **/
	List<Category> getCategories();
	
	/** Método que regresa la categoría correspondiente al id**/
	Category getCategory(Integer category_id);
	
	/** Método que regresa si se ha podido crear una categoria**/
	String createCategory(Category category);
	
	
	String updateCategory(Integer category_id, Category category);
	
	String deleteCategory(Integer category_id);
}
