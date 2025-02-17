package com.exam.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long questionId;
	private String questionContent;
	private String questionImage;
	
	private String questionOption1;
	private String questionOption2;
	private String questionOption3;
	private String questionOption4;
	
	@Transient
	private String givenAnswer;
	
	private String answer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Quiz quiz;
	
	
}