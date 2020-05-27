package com.mgiandia.library.view.reservation;

import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.view.framework.BaseViewModel;

public class BookSearchViewModel extends BaseViewModel<BookSearchPresenter> {

    @Override
    protected BookSearchPresenter createPresenter() {
        BookSearchPresenter presenter = new BookSearchPresenter();
        BookDAOMemory bookDAOMemory = new BookDAOMemory();
        presenter.setBookDAO(bookDAOMemory);
        return presenter;
    }

}
