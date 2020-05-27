package com.mgiandia.library.view.reservation;

import android.util.Log;

import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.dao.ReservationDAO;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.ReservationDAOMemory;

public class BookReservationViewModel extends BaseViewModel<BookReservationPresenter> {

    protected BookReservationPresenter presenter;

    public BookReservationViewModel() {
        super();
        Log.d("ViewModel", "ViewModel created");

        // assemble presenter
        presenter = createPresenter();

    }

    @Override
    protected BookReservationPresenter createPresenter() {
        BookDAO bookDAO = new BookDAOMemory();
        ReservationDAO reservationDAO = new ReservationDAOMemory();
        BorrowerDAO borrowerDAO = new BorrowerDAOMemory();

        BookReservationPresenter presenter = new BookReservationPresenter();
        presenter.setBookDAO(bookDAO);
        presenter.setBorrowerDAO(borrowerDAO);
        presenter.setReservationDAO(reservationDAO);
        return presenter;
    }

    @Override
    public BookReservationPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // avoid leaking activity for any reason
        presenter.clearView();
    }
}
