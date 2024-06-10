package com.mgiandia.catalog.service;


import jakarta.enterprise.inject.spi.CDI;
import jakarta.persistence.EntityManager;

import com.mgiandia.catalog.domain.Book;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;

@RequestScoped
public class BookService {
	private Book book;

	public static BookService factoryMethod(Integer bookno) {
		BookService bookService = new BookService();
		EntityManager em = CDI.current().select(EntityManager.class).get();
		bookService.book = em.find(Book.class, bookno);
		return bookService;
	}

	public Book queryBook() {
		return book;
	}
}