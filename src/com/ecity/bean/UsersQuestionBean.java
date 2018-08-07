package com.ecity.bean;

public class UsersQuestionBean {
	String question, uid, loc;

	public UsersQuestionBean(String uid, String question, String loc) {
		//System.out.println("Im in Question Bean");
		this.question = question;
		this.loc = loc;
		this.uid = uid;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}
	
}
