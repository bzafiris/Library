package com.mgiandia.library.ui;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.dao.ReservationDAO;
import com.mgiandia.library.domain.Reservation;
import com.mgiandia.library.memorydao.MemoryInitializer;
import com.mgiandia.library.memorydao.ReservationDAOMemory;
import com.mgiandia.library.ui.reservation.ReservationPresenter;

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
	public void searchForBookByISBN() {

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

	@Test
	public void allowBookReservationWhenNoAvailableItems() {
		presenter.start();

		view.setBookISBN("3");
		view.setBorrowerNo(1);

		presenter.searchBook();
		presenter.searchBorrower();

		Assert.assertTrue(view.isReserveActionEnabled());

		// επιλογή κουμπιού reserve
		presenter.reserveBook();

		// θα πρέπει να εντοπιστεί κράτηση στη βάση δεδομένων
		ReservationDAO reservationDao = new ReservationDAOMemory();
		List<Reservation> reservations = reservationDao.findAll();

		Assert.assertEquals(1, reservations.size());
		Reservation r = reservations.get(0);
		Assert.assertEquals("3", r.getBook().getIsbn().getValue());
		Assert.assertEquals(1, r.getBorrower().getBorrowerNo());
		// έλεγχος ημερομηνίας κράτησης
	}
	
	@Test
	public void denyBookReservationWhenAvailableItems() {
		presenter.start();

		view.setBookISBN("1");
		view.setBorrowerNo(1);

		presenter.searchBook();
		presenter.searchBorrower();

		Assert.assertTrue(view.isReserveActionEnabled());

		// επιλογή κουμπιού reserve
		presenter.reserveBook();

		// θα πρέπει να εντοπιστεί κράτηση στη βάση δεδομένων
		ReservationDAO reservationDao = new ReservationDAOMemory();
		List<Reservation> reservations = reservationDao.findAll();

		Assert.assertEquals(0, reservations.size());
		
	}
	
	

}
