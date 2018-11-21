package com.mgiandia.library.service;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityTransaction;

import org.junit.Test;

import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.domain.Reservation;
import com.mgiandia.library.persistence.Initializer;

public class ReservationServiceTest extends LibraryServiceTest {
	
	@Test
	public void testSuccessfulReservation() {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Item i = em.find(Item.class, Initializer.UML_REFACTORING_ID);
		Book book = i.getBook();
		
		tx.commit();
		
		int bookId = book.getId();
		 /// ----------------------
		
		ReservationService rs = new ReservationService(em);
		
		Reservation r = rs.reserveBook(bookId, Initializer.DIAMANTIDIS_ID);
		
		assertNotNull(r);
		
		
		
		
	}
	
}
