package com.mgiandia.library.ui.reservation;

import java.awt.event.ActionEvent;

import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;

public class ReservationPresenter {
	public Borrower borrower;
	public boolean borrowerFound;
	public Book book;
	public boolean bookFound;
	private ReservationJFrame view;

	public ReservationPresenter(ReservationJFrame view) {
		this.view = view;
	}

	public void start(){
		view.setPresenter(this);
		view.open();
	}
	
	public void cancel(){
		view.close();
	}
	
	public void searchBook(ReservationJFrame reservationJFrame) {
		String isbn = reservationJFrame.getBookISBN();
		BookDAO bookDaoMemory = new BookDAOMemory();
		book = bookDaoMemory.find(isbn);
	
		if (book == null) {
			bookFound = false;
			reservationJFrame.setBookTitle("");
			reservationJFrame.showError("Book not found");
		} else {
			bookFound = true;
			reservationJFrame.setBookTitle(book.getTitle());
		}
	
		if (borrowerFound && bookFound) {
			reservationJFrame.setReserveActionEnabled(true);
		} else {
			reservationJFrame.setReserveActionEnabled(false);
		}
	}

	void searchBorrower(ReservationJFrame view, ActionEvent evt) {
		searchBorrower(view);
	}

	void searchBorrower(ReservationJFrame view) {
	
		Integer borrowerNo = view.getBorrowerNo();
		// use borrowerNo for searching in the database
		BorrowerDAO borrowerDao = new BorrowerDAOMemory();
		borrower = borrowerDao.find(borrowerNo);
	
		if (borrower == null) {
			borrowerFound = false;
			view.setBorrowerFirstName("");
			view.setBorrowerLastName("");
			view.showError("Borrower not found");
		} else {
			borrowerFound = true;
			view.setBorrowerFirstName(borrower.getFirstName());
			view.setBorrowerLastName(borrower.getLastName());
		}
	
		if (borrowerFound && bookFound) {
			view.setReserveActionEnabled(true);
		} else {
			view.setReserveActionEnabled(false);
		}
	
	}
}