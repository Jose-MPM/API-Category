/**
 * Clase que nos permitira representar el primer endpoint de categorías.
 * 
 * @version 7th September
 * @author Pedro Méndez José Manuel 
 */

package com.product;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.entity.Category;

@RestController
@RequestMapping("/category")
public class CtrlCategory {
    
	private ArrayList<Category> categories = new ArrayList<Category>();
	
	@PostConstruct
	public void onInit() {
		Category r1 = new Category();
		r1.setCategory_id(1);
		r1.setCategory("Abarrotes");
		r1.setStatus(1);
		Category r2 = new Category();
		r2.setCategory_id(2);  
		r2.setCategory("Electronica");
		r2.setStatus(1);
		//ArrayList<Category> list = new ArrayList<Category>();
		categories.add(r1);
		categories.add(r2);
	}
	
    // http://localhost:8081/category
	@GetMapping
    public ResponseEntity<List<Category>> getCategories(){
		return new ResponseEntity<>(categories, HttpStatus.OK);
    }
	
	@GetMapping("/{category_id}")
	public ResponseEntity<Integer> getCategory(@PathVariable int category_id){
		Category r1 = new Category();
		r1.setCategory_id(category_id);
		r1.setCategory("");
		r1.setStatus(1);
		return new ResponseEntity<>(category_id, HttpStatus.OK);
	}
	
	@PostMapping 
	public ResponseEntity<String> createCategory(@RequestBody Category category){
		
		String result = "";
		boolean in = false;
		for(Category elem: categories) {
			int auxId = (int) elem.getCategory_id();
            if(auxId == category.getCategory_id()){
            	in = true;
            }
		}
		if(in) {
			result= "category already exist";
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		} else {
			result = "category created";
			categories.add(category);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		 
	}
	@PutMapping("/{category_id}")
	public ResponseEntity<String> updateCategory(@PathVariable int category_id, @RequestBody Category category){
		String result = "";
		boolean in = false;
		for(Category elem: categories) {
			int auxId = (int) elem.getCategory_id();
            if(auxId == category_id){
            	in = true;
            }
		}
		if(in) {
			result= "Category updated";
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result = "Category does not exist";
			//categories.add(category);
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{category_id}")
	public ResponseEntity<String> deleteCategory(@PathVariable int category_id){
		String result = "";
		boolean in = false;
		for(Category elem: categories) {
			int auxId = (int) elem.getCategory_id();
            if(auxId == category_id){
            	in = true;
            }
		}
		if(in) {
			result= "Category removed";
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result = "Category does not exist";
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
