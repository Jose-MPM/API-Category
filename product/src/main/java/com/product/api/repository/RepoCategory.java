package com.product.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.api.entity.Category;

@Repository
public interface RepoCategory extends JpaRepository<Category, Integer>{
	
	// Regresamos todas las categorías activas
	@Query(value="SELECT * FROM category WHERE status = :status",nativeQuery=true)
	List<Category> findByStatus(@Param("status") Integer status);

	// Regresamos una categoría activa que tenga ese id
	@Query(value="SELECT * FROM category WHERE category_id= :category_id AND status = 1",nativeQuery=true)
	Category findByCategoryId(@Param("category_id") Integer category_id);
	
	// Buscamos si existen categorias con este nombre
	@Query(value="SELECT * FROM category WHERE category= :category",nativeQuery=true)
	List<Category> findByCategory(@Param("category") String category);
	
	// Inserta una categoria
	@Query(value="INSERT INTO category (category,status) VALUES(:category,1)",nativeQuery=true)
	Category createCategory(@Param("category") String category);
	
	// Todos los update llevan mas etiquetas
	// Actualiza una categoría, regresa un 0 es porque no actualizo nada, en otro caso si hizo actualizaciones
	@Modifying
	@Transactional
	@Query(value="UPDATE category SET category = :category WHERE category_id = :category_id", nativeQuery=true)
	Integer updateCategory(@Param("category_id") Integer category_id, @Param("category") String category);
	
	/**
	 * Si queremos desactivar una región que ya existe solo debemos desactivarla 
	 **/
	@Modifying
	@Transactional
	@Query(value="UPDATE category SET status = 0 WHERE category_id = :category_id", nativeQuery=true)
	void deleteById(@Param("category_id") Integer category_id);
	
}
