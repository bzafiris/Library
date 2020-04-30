package com.mgiandia.library.view.reservation;

import androidx.lifecycle.ViewModel;

public class BookSearchViewModel extends ViewModel {

    BookSearchPresenter presenter;

    public BookSearchViewModel() {

        // assemble presenter here
        presenter = new BookSearchPresenter();
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
