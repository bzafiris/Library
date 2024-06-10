package com.mgiandia.library.persistence;


import java.util.List;

import com.mgiandia.catalog.domain.Book;

import jakarta.enterprise.context.RequestScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

@RequestScoped
public class BookRepository  implements PanacheRepositoryBase<Book, Integer> {

	public List<Book> search(String title) {
		if (title == null) {
			return listAll();
		}
		
		return find("select book from Book book where book.title like :bookTitle" ,
				Parameters.with("bookTitle", title + "%").map())
				.list();
	}
}
