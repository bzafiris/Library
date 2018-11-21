package com.mgiandia.library.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.mgiandia.library.LibraryException;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.Reservation;

public class ReservationService {

	private EntityManager em;
	
	
	public ReservationService(EntityManager em) {
		super();
		this.em = em;
	}

	/**
	 * Κράτηση βιβλίου
	 * @param bookId
	 * @param borrowerId
	 * @return επιστρέφει null αν αποτύχει η κράτηση
	 */
	public Reservation reserveBook(int bookId, int borrowerId) {
		
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		Book book = em.find(Book.class, bookId);
		if (book == null) {
			return null;
		}
		
		Borrower borrower = em.find(Borrower.class, borrowerId);
		if (borrower == null) {
			return null;
		}

		Reservation reservation = book.reserveFor(borrower);
		
		if (reservation != null) {
			em.persist(reservation);
		}
		
		tx.commit();
		
		return reservation;
	}
	
	
}
