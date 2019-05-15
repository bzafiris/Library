package com.mgiandia.library.ui;

import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.memorydao.BookDAOMemory;

public class BookReservationPresenter {

    private BookReservationView view;

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
        Book book = dao.find(bookId);

        if (book != null){
            String bookDescription = book.getTitle();
            view.showSearchResult(bookDescription);
        } else {
            view.showError("Ο κωδικός βιβλίου δεν είναι έγκυρος");
        }
    }
}
