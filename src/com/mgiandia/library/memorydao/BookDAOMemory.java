package com.mgiandia.library.memorydao;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.domain.Book;

public class BookDAOMemory {

	static List<Book> entities = new ArrayList<>();
	
	public Book find(String bookIsbn){
		
		for(Book b: entities){
			if (b.getIsbn().getValue().equals(bookIsbn)){
				return b;
			}
		}
		return null;
		
	}
	
}
