package com.mgiandia.library.view.reservation;

import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.dao.ReservationDAO;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.Reservation;

public class BookReservationPresenter extends BasePresenter<BookReservationView> {

    private Book book;
    private BookDAO bookDAO;
    private ReservationDAO reservationDAO;
    private BorrowerDAO borrowerDAO;

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

        book = bookDAO.find(bookId);

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
        Borrower borrower = borrowerDAO.find(id);

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

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public void setReservationDAO(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
        this.borrowerDAO = borrowerDAO;
    }
}
