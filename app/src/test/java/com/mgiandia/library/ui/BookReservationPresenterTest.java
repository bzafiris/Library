package com.mgiandia.library.ui;

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
    }

    @Test
    public void search() {

        presenter.search(null, null);
        assertEquals(1, view.getErrorCount());

    }
}