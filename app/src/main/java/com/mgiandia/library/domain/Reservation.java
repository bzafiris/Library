package com.mgiandia.library.domain;

import com.mgiandia.library.util.SimpleCalendar;

public class Reservation {

	SimpleCalendar reservationDate;
	SimpleCalendar expirationDate;
	Book book;
	Borrower borrower;
	
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
	
	
}