package com.mgiandia.library.view.reservation;

import com.mgiandia.library.memorydao.BookDAOMemory;
import androidx.lifecycle.ViewModel;

public class BookSearchViewModel extends ViewModel {

    BookSearchPresenter presenter;

    public BookSearchViewModel() {
        // assemble presenter here
        presenter = createPresenter();
    }

    protected BookSearchPresenter createPresenter() {
        BookSearchPresenter presenter = new BookSearchPresenter();
        BookDAOMemory bookDAOMemory = new BookDAOMemory();
        presenter.setBookDAO(bookDAOMemory);
        return presenter;
    }


    public BookSearchPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // clear view instance to avoid leaking activity
        presenter.clearView();
    }
}
