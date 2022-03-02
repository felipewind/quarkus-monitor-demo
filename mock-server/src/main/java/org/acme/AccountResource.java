package org.acme;

import java.net.URI;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jboss.logging.Logger;

@Path("/account")
public class AccountResource {

    private static final Logger LOG = Logger.getLogger(AccountResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Context UriInfo uriInfo) {

        LOG.info("create()");

        var account = new Account(UUID.randomUUID().toString(), "BankBoston");

        URI location = uriInfo.getAbsolutePathBuilder().path(account.id).build();

        return Response.created(location).entity(account).build();

    }
}