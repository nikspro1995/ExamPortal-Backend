package com.exam.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entities.Category;
import com.exam.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins="*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category category){
		
		System.out.println(category.toString());
		Category category1 = categoryService.addCategory(category);
		
		return ResponseEntity.ok(category1);
	}
	
	@PutMapping("/")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category){
		
		Category category1 = categoryService.updateCategory(category);
		
		return ResponseEntity.ok(category1);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<Category> getCategory(@PathVariable Long categoryId){
		
		Category category1 = categoryService.getCategory(categoryId);
		
		return ResponseEntity.ok(category1);
	}
	
	@GetMapping("/")
	public ResponseEntity<Set<Category>> getCategories(){
		
		Set<Category> categories = categoryService.getAllCategories();
		
		return ResponseEntity.ok(categories);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId){
		
		this.categoryService.deleteCategory(categoryId);
		
		return ResponseEntity.ok("deleted");
	}
	
	
}
