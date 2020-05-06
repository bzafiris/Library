package com.mgiandia.library.view.reservation;

import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.MemoryInitializer;
import com.mgiandia.library.memorydao.ReservationDAOMemory;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookReservationPresenterTest {

    BookReservationPresenter presenter;
    BookReservationViewStub view;

    @Before
    public void setup(){

        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        view = new BookReservationViewStub();
        presenter = new BookReservationPresenter();
        presenter.setView(view);
        presenter.setBorrowerDAO(new BorrowerDAOMemory());
        presenter.setBookDAO(new BookDAOMemory());
        presenter.setReservationDAO(new ReservationDAOMemory());

        // the following code should move to Initializer.eraseData()
        ReservationDAOMemory.reset();
    }

    @Test
    public void showSearchResult_onSuccessfulSearch() {

        // check interaction with view
        presenter.search("UML", "Fowler");
        assertEquals(0, view.getErrorCount());
        assertEquals("UML", view.getTitle());
        assertEquals("Fowler", view.getAuthor());

        // set search result
        int bookId = 2;
        presenter.setSearchResult(bookId);
        assertEquals("The Odyssey", view.getSearchResult());

    }

    @Test
    public void testSuccessfulReservation(){

        // resuse test code
        makeReservationRequest(2, "2");
        ReservationDAOMemory dao = new ReservationDAOMemory();
        assertEquals(1, dao.findAll().size());
    }

    private void makeReservationRequest(int bookId, String borrowerId){
        // set search result
        presenter.setSearchResult(bookId);
        assertEquals("The Odyssey", view.getSearchResult());

        // enter borrower id and press reserve
        presenter.submitReservationRequest(borrowerId);
        assertEquals(0, view.getErrorCount());
    }

    @Test
    public void denyDuplicateReservations(){

        makeReservationRequest(2, "2");
        // make again the same reservation
        presenter.submitReservationRequest("2");
        assertEquals(1, view.getErrorCount());
        ReservationDAOMemory dao = new ReservationDAOMemory();
        assertEquals(1, dao.findAll().size());
    }
}