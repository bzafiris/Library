package com.mgiandia.library.memorydao;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.domain.Book;

public class BookDAOMemory implements BookDAO {

	static List<Book> entities = new ArrayList<>();
	
	/* (non-Javadoc)
	 * @see com.mgiandia.library.memorydao.BookDAO#find(java.lang.String)
	 */
	@Override
	public Book find(String bookIsbn){
		
		for(Book b: entities){
			if (b.getIsbn().getValue().equals(bookIsbn)){
				return b;
			}
		}
		return null;
		
	}

	@Override
	public void save(Book book) {
		
		entities.add(book);
		
	}
	
}
