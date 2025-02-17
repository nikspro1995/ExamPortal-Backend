package com.exam.controller;

import java.util.List;
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

import com.exam.entities.Quiz;
import com.exam.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin(origins="*")
public class QuizController {

	@Autowired
	private QuizService quizService;
	
	@PostMapping("/")
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
		
		Quiz quiz1 = quizService.addQuiz(quiz);
		
		return ResponseEntity.ok(quiz1);
	}
	
	@PutMapping("/")
	public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz){
		
		Quiz quiz1 = quizService.updateQuiz(quiz);
		
		return ResponseEntity.ok(quiz1);
	}
	
	@GetMapping("/{quizId}")
	public ResponseEntity<Quiz> getQuiz(@PathVariable Long quizId){
		
		Quiz quiz1 = quizService.getQuiz(quizId);
		
		return ResponseEntity.ok(quiz1);
	}
	
	@GetMapping("/")
	public ResponseEntity<Set<Quiz>> getQuizes(){
		
		Set<Quiz> categories = quizService.getAllQuizzes();
		
		return ResponseEntity.ok(categories);
	}
	
	@DeleteMapping("/{quizId}")
	public ResponseEntity<?> deleteQuiz(@PathVariable Long quizId){
		
		this.quizService.deleteQuiz(quizId);
		
		return ResponseEntity.ok(quizId);
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<Quiz>> getQuizes(@PathVariable("categoryId") Long categoryId ){
		
		List<Quiz> quizzes = this.quizService.getQuizzesByCategory(categoryId);
		
		return ResponseEntity.ok(quizzes);
	}
}
