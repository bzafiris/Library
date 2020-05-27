package com.mgiandia.library.view.reservation;

import com.mgiandia.library.memorydao.BookDAOMemory;
import androidx.lifecycle.ViewModel;

public class BookSearchViewModel extends BaseViewModel<BookSearchPresenter> {

    protected BookSearchPresenter presenter;

    public BookSearchViewModel() {
        // assemble presenter here
        presenter = createPresenter();
    }

    @Override
    protected BookSearchPresenter createPresenter() {
        BookSearchPresenter presenter = new BookSearchPresenter();
        BookDAOMemory bookDAOMemory = new BookDAOMemory();
        presenter.setBookDAO(bookDAOMemory);
        return presenter;
    }


    @Override
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
