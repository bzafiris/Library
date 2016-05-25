package com.mgiandia.library.ui;

import org.junit.Before;
import org.junit.Test;

import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.memorydao.MemoryInitializer;
import com.mgiandia.library.ui.reservation.ReservationPresenter;

import org.junit.Assert;

public class ReservationPresenterTest {

	ReservationViewStub view;
	ReservationPresenter presenter;

	@Before
	public void setup() {
		Initializer initializer = new MemoryInitializer();
		initializer.prepareData();
		view = new ReservationViewStub();
		presenter = new ReservationPresenter(view);
	}

	@Test
	public void startOpensTheView() {
		presenter.start();
		Assert.assertTrue(view.isOpen());
		Assert.assertFalse(view.isReserveActionEnabled());
	}

	@Test
	public void searchForBorrowerById() {

		presenter.start();

		// εισάγω δεδομένα στο view
		view.setBorrowerNo(1);
		// πατήθηκε το κουμπί search
		presenter.searchBorrower();

		// το view πρέπει να περιλαμβάνει τα στοιχεία του borrower
		Assert.assertEquals(view.getBorrowerFirstname(), "Μανόλης");
		Assert.assertEquals(view.getBorrowerLastname(), "Γιακουμάκης");
		Assert.assertFalse(view.isReserveActionEnabled());
	}

	@Test
	public void searchForΒookById() {

		presenter.start();

		// εισάγω δεδομένα στο view
		view.setBookISBN("1");
		// πατήθηκε το κουμπί search
		presenter.searchBook();

		// το view πρέπει να περιλαμβάνει τα στοιχεία του borrower
		Assert.assertEquals(view.getBookTitle(), "The Unified Modeling Language User Guide");
		Assert.assertFalse(view.isReserveActionEnabled());
	}

	@Test
	public void successfulSearchActivatesReservationButton() {
		presenter.start();

		// εισάγω δεδομένα στο view
		view.setBookISBN("1");
		// πατήθηκε το κουμπί search
		presenter.searchBook();

		// εισάγω δεδομένα στο view
		view.setBorrowerNo(1);
		// πατήθηκε το κουμπί search
		presenter.searchBorrower();
		
		Assert.assertTrue(view.isReserveActionEnabled());

	}

}
