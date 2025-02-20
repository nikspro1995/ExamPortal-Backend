package com.exam.service;

import java.util.Set;

import com.exam.entities.Question;
import com.exam.entities.Quiz;

public interface QuestionService {

public Question addQuestion(Question question);
	
	public Question updateQuestion(Question question);
	
	public Set<Question> getAllQuestions();
	
	public Question getQuestion(Long questionId);
	
	public void deleteQuestion(Long questionId);
	
	public Set<Question> getQuestionsByQuizID(Quiz quiz);
}
