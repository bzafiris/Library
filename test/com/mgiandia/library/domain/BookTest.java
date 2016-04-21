package com.mgiandia.library.domain;

import org.junit.*;

import com.mgiandia.library.LibraryException;
import com.mgiandia.library.domain.Author;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.domain.ItemState;
import com.mgiandia.library.util.SystemDate;

public class BookTest {

	Book book;
	Item item;

	@Before
	public void setUp() {
		book = new Book();
		item = new Item();
		item.setItemNumber(1);
		item.available();
		book.addItem(item);
	}

	@Test
	public void succefulSetUp() {
		itemBidirectionalAssociationInvariant(book);
		expectedItemsSize(book, 1);
		Assert.assertEquals(ItemState.AVAILABLE, item.getState());
		successfullAdditionofItem(book, item);
	}

	@Test
	public void addNullAsItem() {
		expectedItemsSize(book, 1);
		book.addItem(null);
		expectedItemsSize(book, 1);
		itemBidirectionalAssociationInvariant(book);
	}

	@Test
	public void addNullAsAuthor() {
		book.addAuthor(null);
		Assert.assertEquals(0, book.getAuthors().size());
		authorBidirectionalAssociationInvariant(book);
	}

	@Test
	public void addItem() {
		expectedItemsSize(book, 1);
		item = new Item();
		item.setItemNumber(2);
		book.addItem(item);
		successfullAdditionofItem(book, item);
		expectedItemsSize(book, 2);
		itemBidirectionalAssociationInvariant(book);
	}

	@Test
	public void addAuthor() {
		Author author = new Author();
		book.addAuthor(author);
		Assert.assertEquals(1, book.getAuthors().size());
		authorBidirectionalAssociationInvariant(book);
	}

	@Test
	public void removeNullAsItem() {
		expectedItemsSize(book, 1);
		book.removeItem(null);
		expectedItemsSize(book, 1);
		itemBidirectionalAssociationInvariant(book);
	}

	@Test
	public void removeNullAsAuthor() {
		Author author = new Author();
		book.addAuthor(author);
		book.removeAuthor(null);
		Assert.assertEquals(1, book.getAuthors().size());
		authorBidirectionalAssociationInvariant(book);
	}

	@Test
	public void removeItem() {
		itemBidirectionalAssociationInvariant(book);
		expectedItemsSize(book, 1);
		book.removeItem(item);
		successfullRemovalofItem(book, item);
		expectedItemsSize(book, 0);
		itemBidirectionalAssociationInvariant(book);
	}

	@Test
	public void removeAuthor() {
		Author author = new Author();
		book.addAuthor(author);
		authorBidirectionalAssociationInvariant(book);
		Assert.assertEquals(1, book.getAuthors().size());
		book.removeAuthor(author);
		authorBidirectionalAssociationInvariant(book);
		Assert.assertEquals(0, book.getAuthors().size());
	}

	@Test
	public void addingSameItemIntoTwoBooks() {
		Book book2 = new Book();
		book2.addItem(item);
		successfullAdditionofItem(book2, item);
		itemBidirectionalAssociationInvariant(book);
		itemBidirectionalAssociationInvariant(book2);
	}

	private void itemBidirectionalAssociationInvariant(Book book) {
		for (Item item : book.getItems()) {
			Assert.assertSame(book, item.getBook());
		}
	}

	private void successfullAdditionofItem(Book book, Item item) {
		Assert.assertTrue(book.getItems().contains(item));
		Assert.assertSame(book, item.getBook());
	}

	private void successfullRemovalofItem(Book book, Item item) {
		Assert.assertFalse(book.getItems().contains(item));
		Assert.assertNull(item.getBook());
	}

	private void expectedItemsSize(Book book, int expectedSize) {
		Assert.assertEquals(expectedSize, book.getItems().size());
	}

	private void authorBidirectionalAssociationInvariant(Book book) {
		for (Author author : book.getAuthors()) {
			Assert.assertTrue(author.getBooks().contains(book));
		}
	}

	@Test
	public void addUserComment() {

		String commentText = "great", user = "bill";
		int rating = 10;

		UserComment comment = commentBook(commentText, user, rating);

		Assert.assertTrue(book.getComments().contains(comment));
		Assert.assertNotNull(comment.getBook());

		for (UserComment c : book.getComments()) {
			Assert.assertSame(book, c.getBook());
		}
	}

	private UserComment commentBook(String commentText, String user, int rating) {

		UserComment comment = new UserComment(rating, commentText, user);
		book.addComment(comment);
		return comment;

	}

	@Test
	public void removeUserComment() {
		UserComment comment = new UserComment(10, "Great!", "bill");
		book.addComment(comment);

		Assert.assertTrue(book.getComments().contains(comment));
		Assert.assertNotNull(comment.getBook());

		for (UserComment c : book.getComments()) {
			Assert.assertSame(book, c.getBook());
		}

		book.removeComment(comment);

		Assert.assertFalse(book.getComments().contains(comment));
		Assert.assertNull(comment.getBook());

	}

	@Test
	public void testAvgComment_onCommentedBook() {

		UserComment comment = new UserComment(10, "Great!", "bill");
		UserComment comment2 = new UserComment(5, "Great!", "bill");

		book.addComment(comment);
		book.addComment(comment2);

		double avgRating = book.getAvgRating();

		Assert.assertEquals(2, book.getComments().size());
		Assert.assertEquals(7.5, avgRating, 1E-5);

	}

	@Test(expected = LibraryException.class)
	public void testAvgComment_onUncommentedBook() {

		Assert.assertEquals(0, book.getComments().size());
		book.getAvgRating();

	}

	@Test
	public void testReserveBook() {

		Borrower borrower = createBorrower();

		Assert.assertEquals(ItemState.AVAILABLE, item.getState());
		Loan loan = item.borrow(borrower);
		Assert.assertNotNull(loan);

		Borrower anotherBorrower = createBorrower();
		
		Reservation reservation = book.reserve(anotherBorrower);
		Assert.assertNotNull(reservation);
		Assert.assertSame(anotherBorrower, reservation.getBorrower());
		Assert.assertSame(book, reservation.getBook());
		Assert.assertEquals(SystemDate.now(), reservation.getReservationDate());

	}

	private Borrower createBorrower() {
		Borrower borrower = new Borrower();
		BorrowerCategory category = new BorrowerCategory();
		category.setMaxLendingItems(2);
		borrower.setCategory(category);
		Assert.assertTrue(borrower.canBorrow());
		return borrower;
	}

	@Test
	public void denyReservation_whenBookItemAvailable() {

		Borrower borrower = createBorrower();

		Assert.assertEquals(ItemState.AVAILABLE, item.getState());

		Reservation reservation = book.reserve(borrower);
		Assert.assertNull(reservation);

	}

	@Test
	public void denyReservation_toTheBorrowerOfAnItem() {

		Borrower borrower = createBorrower();

		Assert.assertEquals(ItemState.AVAILABLE, item.getState());
		Loan loan = item.borrow(borrower);
		Assert.assertNotNull(loan);

		Reservation reservation = book.reserve(borrower);
		Assert.assertNull(reservation);
		
		

	}

}
