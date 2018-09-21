package com.fortech.bookstore.rest;

import java.net.URI;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.fortech.bookstore.model.Book;
import com.fortech.bookstore.repository.BookService;
import com.fortech.bookstore.repository.PurchaseOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("BookEndpoint")
@Path(value = "/books")
public class BookEndpoint {

	@Inject
	private BookService bookService;

	@Inject
	private PurchaseOrderService orderService;

	@GET
	@Path("/count")
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation(value = "Return the number of persisted books", response = Long.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "The number of books") })
	public Response getCountAll() {
		return Response.ok(bookService.countAll(), MediaType.TEXT_PLAIN).status(Response.Status.OK).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Return all books", response = Book.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Return all books"),
			@ApiResponse(code = 204, message = "Empty list if in database the book table is empty") })
	public Response getBooks() {
		List<Book> list = bookService.findAll();
		if (list.isEmpty())
			return Response.status(Response.Status.NO_CONTENT).build();
		return Response.ok(list).status(Response.Status.OK).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Returns a book by id", response = Book.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Return the book"),
			@ApiResponse(code = 204, message = "The book not found"),
			@ApiResponse(code = 400, message = "Invalid id value") })
	public Response getBook(@PathParam("id") Long id) {
		Book book = bookService.find(id);
		if (book == null)
			return Response.status(Response.Status.NO_CONTENT).build();

		return Response.ok(book).status(Response.Status.OK).build();
	}

	@DELETE
	@Path("{id}")
	@ApiOperation(value = "Delete a book by identifier")
	@ApiResponses({ @ApiResponse(code = 204, message = "Book delete success"),
			@ApiResponse(code = 400, message = "Invalid id value") })
	public Response deleteBook(@PathParam(value = "id") Long id) {
		bookService.delete(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Persist new book and return URI info")
	@ApiResponses({ @ApiResponse(code = 201, message = "Book persisted"),
			@ApiResponse(code = 500, message = "Invalid book"), })
	public Response save(@ApiParam(name = "Book to persist", required = true) Book book, @Context UriInfo uriInfo) {
		book = bookService.create(book);
		System.out.println(uriInfo.getPath());
		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(book.getId())).build();
		return Response.created(uri).status(Status.CREATED).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/buy")
	public Response purchaseOrder(List<Book> books) {
		orderService.addItem(books);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

}
