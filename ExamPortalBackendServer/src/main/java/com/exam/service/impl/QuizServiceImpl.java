package com.exam.service.impl;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exam.entities.Category;
import com.exam.entities.Quiz;
import com.exam.repo.QuizRepository;
import com.exam.service.QuizService;

@Component
public class QuizServiceImpl implements QuizService{

	@Autowired
	private QuizRepository quizRepository;
	
	@Override
	public Quiz addQuiz(Quiz quiz) {
		
		return this.quizRepository.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		
		return this.quizRepository.save(quiz);
	}

	@Override
	public Set<Quiz> getAllQuizzes() {

		return new HashSet<>(this.quizRepository.findAll());
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		
		return this.quizRepository.findById(quizId).get();
	}

	@Override
	public void deleteQuiz(Long quizId) {

		this.quizRepository.deleteById(quizId);
		
	}

	@Override
	public List<Quiz> getQuizzesByCategory(Long categoryId) {
		
		Category category = new Category();
		category.setCategoryId(categoryId);
		return this.quizRepository.findByCategory(category);
	}

}
