package com.mgiandia.library.view.reservation;

import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.MemoryInitializer;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class BookSearchPresenterTest {

    BookSearchPresenter presenter;
    BookSearchViewStub view;
    @Before
    public void setup(){

        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        view = new BookSearchViewStub();
        presenter = new BookSearchPresenter();
        presenter.setBookDAO(new BookDAOMemory());
        presenter.setView(view);
    }

    @Test
    public void testSearch(){

        presenter.searchBooks("UML", "");
        Set<Book> books = presenter.getResults();
        assertEquals(2, books.size());

        Book selected = books.iterator().next();
        presenter.onBookSelected(selected);
        assertEquals(selected.getId(), view.getReturnedSearchResult());

    }

}
