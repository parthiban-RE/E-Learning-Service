package com.elearning.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class AnswerOption {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable = false, updatable = false)
   
	private long id;   
    private String answerText;    
    private boolean correct;

    
    @ManyToOne (fetch = FetchType.LAZY)
   	@JoinColumn(name="question_id")
   	private Question  question ;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getAnswerText() {
		return answerText;
	}


	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}


	public boolean isCorrect() {
		return correct;
	}


	public void setCorrect(boolean correct) {
		this.correct = correct;
	}


	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
		this.question = question;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
    
}