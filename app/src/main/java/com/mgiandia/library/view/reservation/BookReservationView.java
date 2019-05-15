package com.mgiandia.library.view.reservation;

public interface BookReservationView {
    void showError(String msg);

    void showSearchView(String title, String author);

    void showSearchResult(String bookDescription);
}
