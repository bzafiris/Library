package com.mgiandia.library.dao;

import com.mgiandia.library.domain.Book;

public interface BookDAO {

	public Book find(String bookIsbn);
	
	public void save(Book book);

}