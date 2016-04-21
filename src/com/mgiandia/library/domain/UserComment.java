package com.mgiandia.library.domain;

public class UserComment {

	private int rating;
	private String comment;
	private String user;

	private Book book;
	
	public UserComment(int rating, String comment, String user) {
		super();
		this.rating = rating;
		this.comment = comment;
		this.user = user;
	}
	
	
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}


	public void setBook(Book book) {
		if (this.book != null){
			this.book.friendComments().remove(this);
		}
		this.book = book;
		if (this.book != null){
			this.book.friendComments().add(this);
		}
	}


	public Book getBook() {
		// TODO Auto-generated method stub
		return book;
	}
	
	
	
	
}
