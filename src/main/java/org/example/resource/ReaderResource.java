package org.example.resource;

import org.example.model.Reader;
import org.example.resource.payload.BookPayload;
import org.example.resource.payload.ReaderPayload;
import org.example.resource.payload.Report;
import org.example.service.ReaderService;
import org.example.service.ReaderServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/reader")
public class ReaderResource {
    private final ReaderService readerService = new ReaderServiceImpl();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReaderList() {
        List<Reader> readers = readerService.getReaderList();

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
    public Response getReaderId(@PathParam("id") int id) {
        Reader book = readerService.findReaderId(id);

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
    public Response removeReaderId(@PathParam("id") int id) {
        try {
            readerService.remove(id);
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAuthor(ReaderPayload readerPayload) {
        try {
            readerService.save(readerPayload);
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/report")
    @Produces(MediaType.APPLICATION_JSON)
    public Response printRepostBook(Report report) {
        try {
            return Response.status(Response.Status.OK).entity(readerService.repostBook(report)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}