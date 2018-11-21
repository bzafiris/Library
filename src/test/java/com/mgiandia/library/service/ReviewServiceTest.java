package com.mgiandia.library.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Review;
import com.mgiandia.library.persistence.Initializer;
import com.mgiandia.library.persistence.JPAUtil;

public class ReviewServiceTest {
	
	EntityManager em;
	@Before
	public void setup() {
		Initializer initializer = new Initializer();
		initializer.prepareData();
		
		em = JPAUtil.getCurrentEntityManager();
		
	}
	
	//@Test
	public void submitReview() {

		int bookId = 1;
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Book book = em.find(Book.class, bookId);
		assertNotNull(book);
		
		Review r = new Review("abc", "abc", 5, book);
		
		em.persist(r);
		tx.commit();
		
		assertNotNull(r.getId());
		
		Query query = em.createQuery("select r from Review r");
		List<Review> reviews = query.getResultList();
		assertEquals(1, reviews.size());
		
		
		
	}
	
	@After
	public void tearDown() {
		
		em.close();
		
	}

}
