package com.mgiandia.library.presenter;

import com.mgiandia.library.domain.Book;

public interface BookReservationView {
    void showError(String errorMsg);

    void showBookDetails(Book selectedBook);
}
