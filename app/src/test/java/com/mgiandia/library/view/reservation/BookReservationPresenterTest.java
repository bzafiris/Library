package com.mgiandia.library.view.reservation;

import com.mgiandia.library.dao.Initializer;
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
        view = new BookReservationViewStub();
        presenter = new BookReservationPresenter(view);
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();
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

        // set search result
        int bookId = 2;
        presenter.setSearchResult(bookId);
        assertEquals("The Odyssey", view.getSearchResult());

        // enter borrower id and press reserve
        String borrowerId = "2";
        presenter.submitReservationRequest(borrowerId);
        assertEquals(0, view.getErrorCount());

        // check saved reservation

        ReservationDAOMemory dao = new ReservationDAOMemory();
        assertEquals(1, dao.findAll().size());
    }
}