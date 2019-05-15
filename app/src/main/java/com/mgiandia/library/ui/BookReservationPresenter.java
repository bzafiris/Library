package com.mgiandia.library.ui;

import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.memorydao.BookDAOMemory;

public class BookReservationPresenter {

    private BookReservationView view;

    public BookReservationPresenter(BookReservationView view) {
        this.view = view;
    }

    public void search(String title, String author){
        if (title ==  null || author == null){
            view.showError("Παρακαλώ συμπληρώστε ένα από τα πεδία");
        }
        BookDAO dao = new BookDAOMemory();

    }

}
