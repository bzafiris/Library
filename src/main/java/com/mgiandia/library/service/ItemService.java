package com.mgiandia.library.service;


import java.util.HashSet;
import java.util.Set;

import com.mgiandia.catalog.domain.Book;
import com.mgiandia.library.domain.Item;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.persistence.EntityManager;

@RequestScoped
public class ItemService {
	private Set<Item> items = new HashSet<Item>();

	/**
	* Μη ενθυλακωμένη συλλογή των αντιτύπων του βιβλίου.
	* @return  Τα αντίτυπα του βιβλίου
	*/
	public Set<Item> friendItems() {
		return items;
	}

	/**
	* Απομάκρυνση ενός αντιτύπου ( {@link Item} ) από τη συλλογή αντιτύπων του βιβλίου.
	* @param item  Το αντίτυπο
	*/
	public void removeItem(Item item) {
		if (item != null) {
			item.setBookno(null);
		}
	}

	/**
	* Προσθήκη ενός αντιτύπου ( {@link Item} ) στη συλλογή αντιτύπων του βιβλίου.
	* @param item  Το αντίτυπο
	*/
	public void addItem(Item item, Integer thisId) {
		if (item != null) {
			item.setBookno(thisId);
		}
	}

	/**
	* Επιστρέφει τα αντίτυπα ( {@link Item} ) για κάποιο βιβλίο. Η συλλογή των αντιτύπων είναι αντίγραφο. Για την προσθήκη κάποιου αντιτύπου στη συλλογή χρησιμοποιείστε τη μέθοδο  {@link Book#addItem(Item)} και για την απομάκρυνση ενός αντιτύπου τη μέθοδο  {@link Book#removeItem(Item)} .
	* @return  Αντίγραφο της συλλογής των αντιτύπων του βιβλίου
	*/
	public Set<Item> getItems() {
		return new HashSet<Item>(items);
	}

	public static ItemService factoryMethod(Integer bookno) {
		ItemService itemService = new ItemService();
		EntityManager em = CDI.current().select(EntityManager.class).get();
		itemService.items = new HashSet<Item>(
				em.createQuery("select i from Item i where i.bookno = :bookno", Item.class)
						.setParameter("bookno", bookno).getResultList());
		return itemService;
	}
}