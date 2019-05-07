package com.mgiandia.library.view.reservation;

import com.mgiandia.library.domain.Book;

public interface BookReservationView {
    void showError(String errorMsg);

    void showBookDetails(Book selectedBook);
}
