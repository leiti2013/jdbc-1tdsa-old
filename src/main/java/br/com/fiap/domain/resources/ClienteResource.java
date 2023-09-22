package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.Cliente;
import br.com.fiap.domain.repository.ClienteRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;


import javax.print.attribute.standard.Media;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Path("/cliente")
public class ClienteResource {
    @Context
    UriInfo uriInfo;

    private ClienteRepository repo = new ClienteRepository();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByID( @PathParam ("id") Long id){
        Cliente cliente = repo.findById(id);
        if (Objects.isNull(cliente)) return Response.status(404).build();
        return Response.ok(cliente).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response persit(Cliente c){
        Cliente cliente = repo.persist(c);
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI uri = ub.path(String.valueOf(cliente.getId())).build();
        return Response.created(uri).entity(cliente).build();
    }
}
