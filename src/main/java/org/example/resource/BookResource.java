package org.example.resource;

import org.example.model.Book;
import org.example.resource.payload.AuthorPayload;
import org.example.resource.payload.BookPayload;
import org.example.resource.payload.Report;
import org.example.service.BookService;
import org.example.service.BookServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/book")
public class BookResource {
    private final BookService bookService = new BookServiceImpl();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookList() {
        List<Book> books = bookService.getBookList();

        if (books != null) {
            return Response.status(Response.Status.OK)
                    .entity(books)
                    .build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookId(@PathParam("id") int id) {
        Book book = bookService.findBookId(id);

        if (book != null) {
            return Response.status(Response.Status.OK)
                    .entity(book)
                    .build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeBookId(@PathParam("id") int id) {
        try {
            bookService.remove(id);
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(BookPayload bookPayload) {
        try {
            bookService.save(bookPayload);
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).build();
    }
}
