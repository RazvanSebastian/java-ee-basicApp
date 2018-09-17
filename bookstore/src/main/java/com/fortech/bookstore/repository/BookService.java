package com.fortech.bookstore.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.validation.constraints.NotNull;

import com.fortech.bookstore.model.Book;
import com.fortech.bookstore.util.NumberGenerator;
import com.fortech.bookstore.util.TextUtil;
import com.fortech.bookstore.util.cdi_qualifier.EightDigits;
import com.fortech.bookstore.util.cdi_qualifier.Loggable;
import com.fortech.bookstore.util.cdi_qualifier.ThirteenDigits;

@Loggable
public class BookService {

	@PersistenceContext(unitName = "bookStorePU")
	private EntityManager entityManager;

	@Inject
	private TextUtil textUtil;

	@Inject
	// @ThirteenDigits
	@EightDigits
	private NumberGenerator generator;

	@Transactional(value = TxType.REQUIRED)
	public Book create(@NotNull Book book) {
		book.setDescription(textUtil.sanitize(book.getDescription()));
		book.setTitle(textUtil.sanitize(book.getTitle()));
		book.setIsbn(generator.generateNumber());
		entityManager.persist(book);
		return book;
	}

	@Transactional(value = TxType.REQUIRED)
	public void delete(@NotNull Long id) {
		Book book = entityManager.getReference(Book.class, id);
		entityManager.remove(book);
	}

	@Transactional(value = TxType.REQUIRED)
	public void deleteAll() {
		Query query = entityManager.createQuery("DELETE FROM Book");
		query.executeUpdate();
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
