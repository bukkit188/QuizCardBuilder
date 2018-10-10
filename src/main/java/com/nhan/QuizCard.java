package com.nhan;

public class QuizCard {
	String question;
	String answer;
	public QuizCard(String ques, String ans) {
		question = ques;
		answer = ans;
	}
	public String getQuestion() {
		return question;
	}
	public String getAnswer() {
		return answer;
	}
}
