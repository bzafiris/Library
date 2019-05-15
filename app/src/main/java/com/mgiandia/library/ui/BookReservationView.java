package com.mgiandia.library.ui;

interface BookReservationView {
    void showError(String msg);

    void showSearchView(String title, String author);

    void showSearchResult(String bookDescription);
}
