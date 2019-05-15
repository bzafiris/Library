package com.mgiandia.library.view.reservation;

import android.content.Intent;

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
        if (title.isEmpty() && author.isEmpty()){
            view.showError("Παρακαλώ συμπληρώστε ένα από τα πεδία");
            return;
        }

        view.showSearchView(title, author);

    }

    public void setSearchResult(int bookId) {
        // search book by id

        if (bookId == -1){
            view.showError("Δεν βρέθηκε βιβλίο");
            return;
        }

        BookDAO dao = new BookDAOMemory();
        book = dao.find(bookId);

        if (book != null){
            String bookDescription = book.getTitle();
            view.showSearchResult(bookDescription);
        } else {
            view.showError("Ο κωδικός βιβλίου δεν είναι έγκυρος");
        }
    }

    public void submitReservationRequest(String borrowerId) {

        int id = -1;
        try {
            id = Integer.parseInt(borrowerId);
        } catch (NumberFormatException e){
            view.showError("Μη έγκυρος κωδικός δανειζομένου");
            return;
        }

        // search borrower by id
        BorrowerDAO dao = new BorrowerDAOMemory();
        Borrower borrower = dao.find(id);

        if (borrower == null){
            view.showError("Ο κωδικός δανειζομένου δεν υπάρχει");
            return;
        }
        // create reservation
        if (book == null){
            view.showError("Δεν έχει γίνει επιλογή βιβλίου");
            return;
        }
        // check if reservation exists
        ReservationDAO reservationDAO = new ReservationDAOMemory();
        Reservation foundReservation = reservationDAO.find(id, book.getId());

        if (foundReservation != null){
            view.showError("Υπάρχει ήδη κράτηση του βιβλίου για τον δανειζόμενο");
            return;
        }

        Reservation r = book.reserve(borrower);
        if (r == null){
            view.showError("Η κράτηση δεν επιτρέπεται");
            return;
        }

        // save reservation with ReservationDAO
        reservationDAO.save(r);

        view.showStatus("Η κράτηση έγινε με επιτυχία");

    }
}
