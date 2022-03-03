package org.acme;

import java.net.URI;
import java.util.UUID;

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

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.logging.Logger;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    private static final Logger LOG = Logger.getLogger(AccountResource.class);

    @POST
    public Response create(@Context UriInfo uriInfo) {

        LOG.info("create()");

        var account = new Account(UUID.randomUUID().toString(), "BankBoston");

        URI location = uriInfo.getAbsolutePathBuilder().path(account.id).build();

        return Response.created(location).entity(account).build();

    }

    @GET
    @Path("{id}")
    public Response get(@Parameter(example = "1") @PathParam("id") String id) {

        LOG.info("get() " + id);

        var account = new Account(id, "BankBoston");

        return Response.status(Status.OK).entity(account).build();

    }
}