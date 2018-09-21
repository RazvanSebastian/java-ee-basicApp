package bookstore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import com.fortech.bookstore.model.Book;
import com.fortech.bookstore.model.Language;
import com.fortech.bookstore.repository.BookService;
import com.fortech.bookstore.util.LoggingProducer;
import com.fortech.bookstore.util.NumberGenerator;
import com.fortech.bookstore.util.TextUtil;
import com.fortech.bookstore.util.annotation.generator.PIsbnGenerator;

@RunWith(Arquillian.class)
public class BookServiceTest {

	@Inject
	private BookService bookService;

	@Test
	public void createTest() {

		bookService.deleteAll();

		assertEquals(0, bookService.findAll().size());
		assertEquals(Long.valueOf(0), bookService.countAll());

		// Create book
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
		Book book = new Book("Title", "blabla", 12.5f, "isbn", calendar.getTime(), 150, "image", Language.ENGLISH);
		book = bookService.create(book);
		Long id = book.getId();

		// check isbn number
		assertTrue(book.getIsbn().startsWith("p-13-5677-"));

		// check if titile and description are sanitized
		assertFalse(book.getTitle().matches("(\\t)|(\\s{2,})"));
		assertFalse(book.getDescription().matches("(\\t)|(\\s{2,})"));

		// chekc if book was persisted
		assertNotNull(id);

		// Find book
		Book bookfound = bookService.find(id);
		assertEquals(bookfound.getTitle(), book.getTitle());

		// Test counting books
		assertEquals(Long.valueOf(1), bookService.countAll());

		// Test find all
		assertEquals(1, bookService.findAll().size());

		// Test delete book
		bookService.delete(id);
		assertTrue(bookService.find(id) == null);

	}

	@Test(expected = Exception.class)
	public void createBookWithInvalidDescription() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
		Book book = new Book("Title", "", 12.5f, "isbn", calendar.getTime(), 150, "image", Language.ENGLISH);
		book = bookService.create(book);
	}

	@Test(expected = Exception.class)
	public void createBookWithInvalidTitle() {
		bookService.deleteAll();
		// Create book
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
		Book book = new Book(null, "blabla", 12.5f, "isbn", calendar.getTime(), 150, "image", Language.ENGLISH);
		bookService.create(book);
	}

	@Test(expected = Exception.class)
	public void createBookWithInvalidDate() {
		bookService.deleteAll();
		// Create book
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
		Book book = new Book("Title", "blabla", 12.5f, "isbn", calendar.getTime(), 150, "image", Language.ENGLISH);
		bookService.create(book);
	}

	@Test
	public void updateCostWithMethod1() {
		bookService.deleteAll();
		assertEquals(Long.valueOf(0), bookService.countAll());

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
		Book book = new Book("Title", "blabla", 12.5f, "isbn", calendar.getTime(), 150, "image", Language.ENGLISH);
		book = bookService.create(book);
		assertEquals(Long.valueOf(1), bookService.countAll());

		float currentCost = book.getUnitCost();
		Book updateBook = bookService.raiseUnitCost_method1(book.getId(), Float.valueOf(1));

		assertEquals(Float.valueOf(currentCost + 1f), Float.valueOf(updateBook.getUnitCost()));

	}

	@Test
	public void updateCostWithMethod2() {
		bookService.deleteAll();
		assertEquals(Long.valueOf(0), bookService.countAll());

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
		Book book = new Book("Title", "blabla", 12.5f, "isbn", calendar.getTime(), 150, "image", Language.ENGLISH);
		book = bookService.create(book);
		assertEquals(Long.valueOf(1), bookService.countAll());

		float currentCost = book.getUnitCost();
		Book updateBook = bookService.raiseUnitCost_method2(book, Float.valueOf(1));

		assertEquals(Float.valueOf(currentCost + 1f), Float.valueOf(updateBook.getUnitCost()));

	}

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class).addClass(BookService.class).addClass(Book.class)
				.addClass(Language.class).addClass(TextUtil.class).addClass(NumberGenerator.class)
				.addClass(Logger.class).addClass(LoggingProducer.class).addClass(PIsbnGenerator.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
	}

}
