package com.mgiandia.library.view.reservation;

import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.dao.ReservationDAO;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.ReservationDAOMemory;
import com.mgiandia.library.view.framework.BaseViewModel;

public class BookReservationViewModel extends BaseViewModel<BookReservationPresenter> {

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

}
