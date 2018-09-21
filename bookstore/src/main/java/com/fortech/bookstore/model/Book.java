package com.fortech.bookstore.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fortech.bookstore.util.annotation.ChronologicalDates;
import com.fortech.bookstore.util.annotation.URL;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description = "Book representation resource")
@ChronologicalDates
public class Book {

	private Long id;

	private String title;

	private String description;

	private Float unitCost;

	private String isbn;

	private Date publicationDate;

	private Date creationDate;

	private Integer ngOfPages;

	private String imageUrl;

	private Language language;

	public Book() {
		super();
	}

	public Book(String title, String description, Float unitCost, String isbn, Date publicationDate, Integer ngOfPages,
			String imageUrl, Language language) {
		super();
		this.title = title;
		this.description = description;
		this.unitCost = unitCost;
		this.isbn = isbn;
		this.publicationDate = publicationDate;
		this.ngOfPages = ngOfPages;
		this.imageUrl = imageUrl;
		this.language = language;
	}
	
	@Id
	@GeneratedValue
	@ApiModelProperty("Identifier")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@Column(length = 200)
	@Size(min = 1, max = 200)
	@ApiModelProperty("Title of the book")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotNull
	@Column(length = 10000)
	@Size(min = 1, max = 10000)
	@ApiModelProperty("Description of the book")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "unit_cost")
	@Min(value = 1)
	public Float getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(Float unitCost) {
		this.unitCost = unitCost;
	}

	@NotNull
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Column(name = "publication_date")
	@Temporal(TemporalType.DATE)
	@Past
	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	@Column(name = "nb_of_pages")
	public Integer getNgOfPages() {
		return ngOfPages;
	}

	public void setNgOfPages(Integer ngOfPages) {
		this.ngOfPages = ngOfPages;
	}

	@Column(name = "image_url")
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Past
	@Temporal(TemporalType.DATE)
	@Column(name = "creation_date")
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", description=" + description + ", unitCost=" + unitCost
				+ ", isbn=" + isbn + ", publicationDate=" + publicationDate + ", ngOfPages=" + ngOfPages + ", imageUrl="
				+ imageUrl + ", language=" + language + "]";
	}

}
