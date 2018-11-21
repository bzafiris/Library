package com.mgiandia.library.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.mgiandia.library.util.SystemDate;

@Entity
@Table(name = "reviews")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "reviewer", length = 100, nullable = false)
	private String email;

	@Column(name = "comments", length = 2000, nullable = false)
	private String comments;

	@Column(name = "rating", nullable = false)
	private int rating;

	@Temporal(TemporalType.DATE)
	@Column(name = "review_date", nullable = false)
	private Date reviewDate;

	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;

	public Review() {
	}
	
	

	public Review(String email, String comments, int rating, Book book) {
		super();
		this.email = email;
		this.comments = comments;
		this.rating = rating;
		this.book = book;
		this.reviewDate = new Date(SystemDate.now().getJavaCalendar().getTimeInMillis());
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public Long getId() {
		return id;
	}

}
