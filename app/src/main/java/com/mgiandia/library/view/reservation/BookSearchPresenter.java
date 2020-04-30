package com.mgiandia.library.view.reservation;

import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.memorydao.BookDAOMemory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookSearchPresenter {

    private BookSearchView view;
    private BookDAO bookDAO;

    public BookSearchPresenter() {
    }

    public Set<Book> searchBooks(String title, String authorName){

        List<Book> resultA = new ArrayList<>();
        List<Book> resultB = new ArrayList<>();

        if (!title.isEmpty()){
            resultA.addAll(bookDAO.findByTitle(title));
        }

        if (!authorName.isEmpty()){
            resultB.addAll(bookDAO.findByAuthorName(authorName));
        }

        Set<Book> result = new HashSet<>();
        result.addAll(resultA);
        result.addAll(resultB);
        return result;

    }

    public void onBookSelected(Book b){
        view.returnSearchResult(b.getId());
    }

    public void setView(BookSearchView view) {
        this.view = view;
    }

    public void clearView(){
        this.view = null;
    }

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
}
