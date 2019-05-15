package com.mgiandia.library.ui;

public class BookReservationViewStub implements BookReservationView {

    int errorCount = 0;

    @Override
    public void showError(String msg) {

        errorCount++;
    }

    public int getErrorCount() {
        return errorCount;
    }
}
