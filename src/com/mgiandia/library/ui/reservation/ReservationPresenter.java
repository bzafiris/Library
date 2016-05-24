package com.mgiandia.library.ui.reservation;

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
	private ReservationView view;

	public ReservationPresenter(ReservationView view) {
		this.view = view;
	}

	public void start(){
		view.setPresenter(this);
		view.open();
	}
	
	public void cancel(){
		view.close();
	}
	
	public void searchBook() {
		String isbn = view.getBookISBN();
		BookDAO bookDaoMemory = new BookDAOMemory();
		book = bookDaoMemory.find(isbn);
	
		if (book == null) {
			bookFound = false;
			view.setBookTitle("");
			view.showError("Book not found");
		} else {
			bookFound = true;
			view.setBookTitle(book.getTitle());
		}
	
		if (borrowerFound && bookFound) {
			view.setReserveActionEnabled(true);
		} else {
			view.setReserveActionEnabled(false);
		}
	}


	void searchBorrower() {
	
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