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

import com.fortech.bookstore.model.Book;
import com.fortech.bookstore.model.Language;
import com.fortech.bookstore.repository.BookRepository;
import com.fortech.bookstore.util.IsbnGenerator;
import com.fortech.bookstore.util.NumberGenerator;
import com.fortech.bookstore.util.TextUtil;

@RunWith(Arquillian.class)
public class BookRepositoryTest {

	@Inject
	private BookRepository bookRepository;

	@Test
	public void createTest() {
		bookRepository.deleteAll();

		assertEquals(0, bookRepository.findAll().size());
		assertEquals(Long.valueOf(0), bookRepository.countAll());

		// Create book
		Book book = new Book("Javaa    Book", "bla  bla", 12.5f, "isbn", new Date(), 150, "image", Language.ENGLISH);
		book = bookRepository.create(book);
		Long id = book.getId();
		
		//check isbn number
		assertTrue(book.getIsbn().startsWith("13"));
		
		//check if titile and description are sanitized
		assertFalse(book.getTitle().matches("(\\t)|(\\s{2,})"));
		assertFalse(book.getDescription().matches("(\\t)|(\\s{2,})"));
		
		// chekc if book was persisted
		assertNotNull(id);
 
		// Find book
		Book bookfound = bookRepository.find(id);
		assertEquals(bookfound.getTitle(), book.getTitle());

		// Test counting books
		assertEquals(Long.valueOf(1), bookRepository.countAll());

		// Test find all
		assertEquals(1, bookRepository.findAll().size());

		// Test delete book
		bookRepository.delete(id);
		assertTrue(bookRepository.find(id) == null);

	}

	@Test(expected = Exception.class)
	public void createBookWithInvalidDescription() {
		bookRepository.deleteAll();
		// Create book
		Book book = new Book("Javaa Book", "", 12.5f, "isbn", new Date(), 150, "image", Language.ENGLISH);
		bookRepository.create(book);
	}
	
	@Test(expected = Exception.class)
	public void createBookWithInvalidTitle() {
		bookRepository.deleteAll();
		// Create book
		Book book = new Book(null, "bla bla", 12.5f, "isbn", new Date(), 150, "image", Language.ENGLISH);
		bookRepository.create(book);
	}

	
	@Test(expected = Exception.class)
	public void createBookWithInvalidDate() {
		bookRepository.deleteAll();
		// Create book
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR)+1);
		Book book = new Book("Title", "blabla", 12.5f, "isbn", calendar.getTime(), 150, "image", Language.ENGLISH);
		bookRepository.create(book);
	}
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
				.addClass(BookRepository.class)
				.addClass(Book.class)
				.addClass(Language.class)
				.addClass(TextUtil.class)
				.addClass(NumberGenerator.class)
				.addClass(IsbnGenerator.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
	}

}
