package com.fortech.bookstore.repository;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.validation.constraints.NotNull;

import com.fortech.bookstore.model.Book;
import com.fortech.bookstore.util.NumberGenerator;
import com.fortech.bookstore.util.TextUtil;
import com.fortech.bookstore.util.annotation.Generator;
import com.fortech.bookstore.util.annotation.Generator.NumberOfDigits;
import com.fortech.bookstore.util.annotation.Loggable;

@Loggable
public class BookService {

	@Inject
	private Logger logger;

	@PersistenceContext(unitName = "bookStorePU")
	private EntityManager entityManager;

	@Inject
	private TextUtil textUtil;

	@Inject
	@Generator(numberOfDigits = NumberOfDigits.THIRTEEN, printed = true)
	private NumberGenerator generator;

	@Transactional(value = TxType.REQUIRED)
	public Book create(@NotNull Book book) {
		book.setDescription(textUtil.sanitize(book.getDescription()));
		book.setTitle(textUtil.sanitize(book.getTitle()));
		book.setIsbn(generator.generateNumber());
		entityManager.persist(book);
		logger.info(" Create book : " + book.toString());
		return book;
	}

	@Transactional(value = TxType.REQUIRED)
	public void delete(@NotNull Long id) {
		Book book = entityManager.find(Book.class, id);
		if (book != null)
			entityManager.remove(book);
	}

	@Transactional(value = TxType.REQUIRED)
	public void deleteAll() {
		Query query = entityManager.createQuery("DELETE FROM Book");
		query.executeUpdate();
	}

	@Transactional(value = TxType.REQUIRED)
	public Book raiseUnitCost_method1(Long id, Float value) {
		Book book = entityManager.find(Book.class, id);
		if (book != null) {
			book.setUnitCost(book.getUnitCost() + value);
		}

		return book;
	}

	@Transactional(value = TxType.REQUIRED)
	public Book raiseUnitCost_method2(Book book, Float value) {
		Book bookToUpdate = entityManager.merge(book);
		bookToUpdate.setUnitCost(bookToUpdate.getUnitCost() + value);

		return bookToUpdate;
	}

	@Transactional(value = TxType.SUPPORTS)
	public Book find(Long id) {
		return entityManager.find(Book.class, id);
	}

	@Transactional(value = TxType.SUPPORTS)
	public List<Book> findAll() {
		TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b order by b.title", Book.class);
		return query.getResultList();
	}

	@Transactional(value = TxType.SUPPORTS)
	public Long countAll() {
		TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(b) FROM Book b", Long.class);
		return query.getSingleResult();
	}
}
