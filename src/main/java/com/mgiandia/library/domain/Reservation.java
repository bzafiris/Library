package com.mgiandia.library.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mgiandia.library.util.SimpleCalendar;
import com.mgiandia.library.util.SystemDate;


@Entity
@Table(name = "reservations")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@org.hibernate.annotations.Type(
            type="com.mgiandia.library.persistence.SimpleCalendarCustomType")
    @Column(name="reservation_date")
    private SimpleCalendar reservationDate = SystemDate.now();
	
	@org.hibernate.annotations.Type(
            type="com.mgiandia.library.persistence.SimpleCalendarCustomType")
    @Column(name="expiration_date")
    private SimpleCalendar expirationDate = null;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="book_id")
	private Book book;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="borrower_id")
	private Borrower borrower;

	public Reservation() {
		// TODO Auto-generated constructor stub
	}
	
	
	public SimpleCalendar getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(SimpleCalendar reservationDate) {
		this.reservationDate = reservationDate;
	}

	public SimpleCalendar getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(SimpleCalendar expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	public Long getId() {
		return id;
	}
	
	
	
}
