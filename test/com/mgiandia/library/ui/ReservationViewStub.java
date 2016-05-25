package com.mgiandia.library.ui;

import com.mgiandia.library.ui.reservation.ReservationPresenter;
import com.mgiandia.library.ui.reservation.ReservationView;

public class ReservationViewStub implements ReservationView {

	private ReservationPresenter presenter;
	private boolean isOpen = false;
	private int errorCount = 0;
	private int infoCount = 0;
	private String bookISBN = "";
	private String bookTitle = "";
	private String borrowerFirstname = "";
	private String borrowerLastname = "";
	private int borrowerNo;
	private boolean reserveActionEnabled = false;

	public boolean isOpen() {
		return isOpen;
	}

	public boolean isReserveActionEnabled() {
		return reserveActionEnabled;
	}

	@Override
	public void open() {
		isOpen = true;
	}

	@Override
	public void close() {
		isOpen = false;
	}

	@Override
	public void showError(String message) {
		errorCount++;
	}

	@Override
	public void showInfo(String message) {
		infoCount++;
	}

	@Override
	public void setPresenter(ReservationPresenter reservationPresenter) {
		this.presenter = reservationPresenter;
	}

	@Override
	public String getBookISBN() {
		return bookISBN;
	}

	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}

	@Override
	public void setBookTitle(String string) {
		this.bookTitle = string;
	}

	@Override
	public void setReserveActionEnabled(boolean b) {
		this.reserveActionEnabled = b;
	}

	@Override
	public int getBorrowerNo() {
		return borrowerNo;
	}

	public String getBorrowerFirstname() {
		return borrowerFirstname;
	}

	public String getBorrowerLastname() {
		return borrowerLastname;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public int getInfoCount() {
		return infoCount;
	}
	
	public void setBorrowerNo(int borrowerNo) {
		this.borrowerNo = borrowerNo;
	}
	


	@Override
	public void setBorrowerFirstName(String string) {
		this.borrowerFirstname = string;
	}

	@Override
	public void setBorrowerLastName(String string) {
		this.borrowerLastname = string;
	}

}
