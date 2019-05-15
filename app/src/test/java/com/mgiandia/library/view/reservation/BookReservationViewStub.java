package com.mgiandia.library.view.reservation;

import com.mgiandia.library.view.reservation.BookReservationView;

public class BookReservationViewStub implements BookReservationView {

    int errorCount = 0;
    private String title;
    private String author;
    private String searchResult;

    @Override
    public void showError(String msg) {

        errorCount++;
    }

    @Override
    public void showStatus(String msg) {

    }

    @Override
    public void showSearchView(String title, String author) {

        this.title = title;
        this.author = author;

    }

    @Override
    public void showSearchResult(String bookDescription) {
        this.searchResult = bookDescription;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getSearchResult() {
        return searchResult;
    }
}
