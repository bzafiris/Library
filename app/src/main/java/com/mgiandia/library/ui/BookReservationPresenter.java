package com.mgiandia.library.ui;

import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.dao.ReservationDAO;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.Reservation;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.ReservationDAOMemory;

public class BookReservationPresenter {

    private BookReservationView view;
    private Book book;

    public BookReservationPresenter(BookReservationView view) {
        this.view = view;
    }

    public void search(String title, String author){
        if (title.isEmpty() || author.isEmpty()){
            view.showError("Παρακαλώ συμπληρώστε ένα από τα πεδία");
            return;
        }

        view.showSearchView(title, author);

    }

    public void setSearchResult(int bookId) {
        // search book by id
        BookDAO dao = new BookDAOMemory();
        book = dao.find(bookId);

        if (book != null){
            String bookDescription = book.getTitle();
            view.showSearchResult(bookDescription);
        } else {
            view.showError("Ο κωδικός βιβλίου δεν είναι έγκυρος");
        }
    }

    public void submitReservationRequest(int borrowerId) {
        // search borrower by id
        BorrowerDAO dao = new BorrowerDAOMemory();
        Borrower borrower = dao.find(borrowerId);

        if (borrower == null){
            view.showError("Ο κωδικός δανειζομένου δεν υπάρχει");
            return;
        }
        // create reservation
        if (book == null){
            view.showError("Δεν έχει γίνει επιλογή βιβλίου");
            return;
        }

        Reservation r = book.reserve(borrower);
        if (r == null){
            view.showError("Η κράτηση δεν επιτρέπεται");
            return;
        }

        // save reservation with ReservationDAO
        ReservationDAO reservationDAO = new ReservationDAOMemory();
        reservationDAO.save(r);

    }
}
