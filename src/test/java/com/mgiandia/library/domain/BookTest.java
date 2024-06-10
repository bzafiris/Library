package com.mgiandia.library.domain;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mgiandia.catalog.domain.Author;
import com.mgiandia.catalog.domain.Book;
import com.mgiandia.catalog.service.BookService;
import com.mgiandia.library.service.ItemService;



public class BookTest {

    Book book;
    Item item;
    
    @BeforeEach
    public void setUp() {
        book = new Book();
        item = new Item();
        item.setItemNumber(1);
        item.available();
        ItemService itemService = ItemService.factoryMethod(book.getId());
		itemService.addItem(item, book.getId());
    }
    
    
    
    @Test
    public void succefulSetUp() {
        itemBidirectionalAssociationInvariant(book);
        expectedItemsSize(book,1);
        Assertions.assertEquals(ItemState.AVAILABLE, item.getState());
        successfullAdditionofItem(book,item);
    }
    
    
    @Test
    public void addNullAsItem() {
        expectedItemsSize(book,1);
        ItemService itemService = ItemService.factoryMethod(book.getId());
		itemService.addItem(null, book.getId());
        expectedItemsSize(book,1);
        itemBidirectionalAssociationInvariant(book);
    }
        
    @Test
    public void addNullAsAuthor() {
        book.addAuthor(null);
        Assertions.assertEquals(0, book.getAuthors().size());
        authorBidirectionalAssociationInvariant(book);
    }
    
    
    @Test
    public void addItem() {        
        expectedItemsSize(book,1);        
        item = new Item();
        item.setItemNumber(2);
        ItemService itemService = ItemService.factoryMethod(book.getId());
		itemService.addItem(item, book.getId());
        successfullAdditionofItem(book,item);
        expectedItemsSize(book,2);
        itemBidirectionalAssociationInvariant(book);
    }

    @Test
    public void addAuthor() {
        Author author = new Author();
        book.addAuthor(author);
        Assertions.assertEquals(1,book.getAuthors().size());
        authorBidirectionalAssociationInvariant(book);
    }
    
    
    @Test
    public void removeNullAsItem() {
        expectedItemsSize(book,1);
        ItemService itemService = ItemService.factoryMethod(book.getId());
		itemService.removeItem(null);
        expectedItemsSize(book,1);
        itemBidirectionalAssociationInvariant(book);        
    }
    
    @Test
    public void removeNullAsAuthor() {
        Author author = new Author();
        book.addAuthor(author);
        book.removeAuthor(null);
        Assertions.assertEquals(1, book.getAuthors().size());
        authorBidirectionalAssociationInvariant(book);
    }
    
    
    @Test
    public void removeItem() {
        itemBidirectionalAssociationInvariant(book);
        expectedItemsSize(book,1);
        ItemService itemService = ItemService.factoryMethod(book.getId());
		itemService.removeItem(item);
        successfullRemovalofItem(book,item);
        expectedItemsSize(book,0);        
        itemBidirectionalAssociationInvariant(book);
    }

    @Test
    public void removeAuthor() {
        Author author = new Author();
        book.addAuthor(author);
        authorBidirectionalAssociationInvariant(book);
        Assertions.assertEquals(1, book.getAuthors().size());
        book.removeAuthor(author);
        authorBidirectionalAssociationInvariant(book);
        Assertions.assertEquals(0, book.getAuthors().size());
    }
    
    
    @Test
    public void addingSameItemIntoTwoBooks() {
        Book book2 = new Book();
        ItemService itemService = ItemService.factoryMethod(book2.getId());
		itemService.addItem(item, book2.getId());
        successfullAdditionofItem(book2,item);
        itemBidirectionalAssociationInvariant(book);
        itemBidirectionalAssociationInvariant(book2);        
    }
    
    
    private void itemBidirectionalAssociationInvariant(Book book) {
        ItemService itemService = ItemService.factoryMethod(book.getId());
		for(Item item : itemService.getItems()) {
        	BookService bookService = BookService.factoryMethod(item.getBookno());
			Assertions.assertSame(book, bookService.queryBook());
        }
    }
    
    private void successfullAdditionofItem(Book book, Item item){
    	ItemService itemService = ItemService.factoryMethod(book.getId());
		Assertions.assertTrue(itemService.getItems().contains(item));
    	BookService bookService = BookService.factoryMethod(item.getBookno());
		Assertions.assertSame(book, bookService.queryBook());
    }
    
    private void successfullRemovalofItem(Book book, Item item) {
    	ItemService itemService = ItemService.factoryMethod(book.getId());
		Assertions.assertFalse(itemService.getItems().contains(item));
    	BookService bookService = BookService.factoryMethod(item.getBookno());
		Assertions.assertNull(bookService.queryBook());
    }
    
    private void expectedItemsSize(Book book, int expectedSize) {
    	ItemService itemService = ItemService.factoryMethod(book.getId());
		Assertions.assertEquals(expectedSize, itemService.getItems().size());
    }
    
    private void authorBidirectionalAssociationInvariant(Book book) {
        for(Author author : book.getAuthors()) {
        	Assertions.assertTrue(author.getBooks().contains(book));            
        }    
    }
}
