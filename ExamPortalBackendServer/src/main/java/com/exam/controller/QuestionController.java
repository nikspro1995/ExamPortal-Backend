package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
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

import com.exam.entities.Question;
import com.exam.entities.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;

@RestController
@RequestMapping("/question")
@CrossOrigin(origins="*")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuizService quizService;
	
	@PostMapping("/")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question){
		
		Question question1 = questionService.addQuestion(question);
		
		return ResponseEntity.ok(question1);
	}
	
	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
		
		Question question1 = questionService.updateQuestion(question);
		
		return ResponseEntity.ok(question1);
	}
	
	@GetMapping("/{questionId}")
	public ResponseEntity<Question> getQuestion(@PathVariable Long questionId){
		
		Question question1 = questionService.getQuestion(questionId);
		
		return ResponseEntity.ok(question1);
	}
	
	@GetMapping("/")
	public ResponseEntity<Set<Question>> getQuestions(){
		
		Set<Question> categories = questionService.getAllQuestions();
		
		return ResponseEntity.ok(categories);
	}
	
	@DeleteMapping("/{questionId}")
	public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId){
		
		this.questionService.deleteQuestion(questionId);
		
		return ResponseEntity.ok(questionId);
	}
	
	
	@GetMapping("/quiz/{quizId}")
	public ResponseEntity<List<Question>> getQuestionByQuiz(@PathVariable Long quizId){
		
//		Question question1 = questionService.getQuestion(questionId);
		
		Quiz quiz = this.quizService.getQuiz(quizId);
		Set<Question> questions = this.questionService.getQuestionsByQuizID(quiz);
		System.out.println("Questions :" + questions);
		List<Question> list = new ArrayList<>(questions);
		System.out.println("Questions :" + list);
		
		if(list.size()> quiz.getNoOfQuestions())
		{
			list = list.subList(0, quiz.getNoOfQuestions());
		}
		
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/quiz/all/{quizId}")
	public ResponseEntity<Set<Question>> getQuestionByQuizAdmin(@PathVariable Long quizId){
		
//		Question question1 = questionService.getQuestion(questionId);
		Quiz quiz = new Quiz();
		quiz.setQuizId(quizId);
		
		Set<Question> questions = this.questionService.getQuestionsByQuizID(quiz);
		
		return ResponseEntity.ok(questions);
	}
	
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions)
	{
		System.out.println("inside eval quiz");
		//System.out.println(questions);
		
		for (Question question : questions) {
			System.out.println(question.getGivenAnswer());
		}
		
		return ResponseEntity.ok("got the questions");
	}
}
