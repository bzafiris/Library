package com.mgiandia.library.view.reservation;

import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.domain.Book;

import java.util.HashSet;
import java.util.Set;

public class BookSearchPresenter extends BasePresenter<BookSearchView> {

    private BookDAO bookDAO;
    private Set<Book> searchResults = new HashSet<>();
    private String titleCriterion = "", authorNameCriterion = "";

    public Set<Book> searchBooks(String title, String authorName){

        boolean criteriaChanged = isCriteriaChanged(title, authorName);

        if (!criteriaChanged) {
            return searchResults;
        }

        searchResults.clear();

        if (!this.titleCriterion.isEmpty()){
            searchResults.addAll(bookDAO.findByTitle(title));
        }
        if (!this.authorNameCriterion.isEmpty()){
            searchResults.addAll(bookDAO.findByAuthorName(authorName));
        }
        return searchResults;
    }

    private boolean isCriteriaChanged(String title, String authorName) {
        boolean criteriaChanged = false;
        if (!this.titleCriterion.equals(title)){
             criteriaChanged = true;
             this.titleCriterion = title == null ? "" : title;
        }
        if (!this.authorNameCriterion.equals(authorName)){
            criteriaChanged = true;
            this.authorNameCriterion = authorName == null ? "" : authorName;
        }
        return criteriaChanged;
    }

    public void onBookSelected(Book b){
        view.returnSearchResult(b.getId());
    }

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
}
