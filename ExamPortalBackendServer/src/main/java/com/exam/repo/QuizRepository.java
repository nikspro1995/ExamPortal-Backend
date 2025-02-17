package com.exam.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entities.Category;
import com.exam.entities.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long>{
	
	List<Quiz> findByCategory(Category category);

}
