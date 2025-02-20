package com.exam.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exam.entities.Question;
import com.exam.entities.Quiz;
import com.exam.repo.QuestionRepository;
import com.exam.service.QuestionService;

@Component
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionRepository questionRepository;
	
	@Override
	public Question addQuestion(Question question) {
		
		return this.questionRepository.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {

		return this.questionRepository.save(question);
	}

	@Override
	public Set<Question> getAllQuestions() {
		
		return new HashSet<>(this.questionRepository.findAll());
	}

	@Override
	public Question getQuestion(Long questionId) {

		return this.questionRepository.findById(questionId).get();
	}

	@Override
	public void deleteQuestion(Long questionId) {

		this.questionRepository.deleteById(questionId);
		
	}

	@Override
	public Set<Question> getQuestionsByQuizID(Quiz quiz) {
		
		return this.questionRepository.findByQuiz(quiz);
	}

}
