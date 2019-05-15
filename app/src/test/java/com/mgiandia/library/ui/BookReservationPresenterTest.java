package com.mgiandia.library.ui;

import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.memorydao.MemoryInitializer;

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
        int bookId = 3;
        presenter.setSearchResult(bookId);
        assertEquals("UML Distilled", view.getSearchResult());

    }
}