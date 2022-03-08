package org.acme;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    private static final Logger LOG = Logger.getLogger(AccountResource.class);

    @Inject
    @RestClient
    MockInterface mockInterface;

    @Inject
    AccountExtractorService accountExtractorService;

    @POST
    @Path("/start-extraction/{quantity}")
    public Response postStartExtraction(@Parameter(example = "100") @PathParam("quantity") int quantity) {
        LOG.info("postStartExtraction() " + quantity);

        accountExtractorService.extractionProcess(quantity);

        return Response.status(Status.ACCEPTED).build();

    }

    @GET
    @Path("{id}")
    public Response get(@Parameter(example = "1") @PathParam("id") String id) {
        LOG.info("get()");

        var response = mockInterface.get(id);

        LOG.info("get() " + response.getStatus());

        return response;
    }

    @GET
    @Path("{id}/balances")
    public Response getBalances(@Parameter(example = "1") @PathParam("id") String id) {
        LOG.info("getBalances()");

        var response = mockInterface.getBalances(id);

        LOG.info("getBalances() " + response.getStatus());

        return response;
    }

    @GET
    @Path("{id}/transactions")
    public Response getTransactions(@Parameter(example = "1") @PathParam("id") String id) {
        LOG.info("getTransactions()");

        var response = mockInterface.getTransactions(id);

        LOG.info("getTransactions() " + response.getStatus());

        return response;
    }

}