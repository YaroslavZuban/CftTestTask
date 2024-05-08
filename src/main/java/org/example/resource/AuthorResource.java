package org.example.resource;

import org.example.model.Author;
import org.example.resource.payload.AuthorPayload;
import org.example.service.AuthorService;
import org.example.service.AuthorService;
import org.example.service.AuthorServiceImpl;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/author")
public class AuthorResource {

    private final AuthorService authorService = new AuthorServiceImpl();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthorList() {
        List<Author> readers = authorService.getAuthorList();

        if (readers != null) {
            return Response.status(Response.Status.OK)
                    .entity(readers)
                    .build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthorId(@PathParam("id") int id) {
        Author book = authorService.findAuthorId(id);

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
    public Response removeAuthorId(@PathParam("id") int id){
        try {
            authorService.remove(id);
        }catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAuthor(AuthorPayload authorPayload){
        try {
            authorService.save(authorPayload);
        }catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).build();
    }
}
