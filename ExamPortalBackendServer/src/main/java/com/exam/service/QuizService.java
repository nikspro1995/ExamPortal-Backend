package com.exam.service;

import java.util.List;
import java.util.Set;

import com.exam.entities.Category;
import com.exam.entities.Quiz;


public interface QuizService {
	
	public Quiz addQuiz(Quiz quiz);
	
	public Quiz updateQuiz(Quiz quiz);
	
	public Set<Quiz> getAllQuizzes();
	
	public Quiz getQuiz(Long quizId);
	
	public void deleteQuiz(Long quizId);
	
	public List<Quiz> getQuizzesByCategory(Long categoryId);
}
