package com.mgiandia.library.view.reservation;

public class BookSearchViewStub implements BookSearchView {

    private int returnedSearchResult;

    @Override
    public void returnSearchResult(int id) {
        this.returnedSearchResult = id;
    }

    public int getReturnedSearchResult() {
        return returnedSearchResult;
    }
}
