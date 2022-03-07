package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "mock-server")
public interface MockInterface {

    @GET
    @Path("/accounts/{id}")
    public Response get(@PathParam("id") String id);

    @GET
    @Path("/accounts/{id}/balances")
    public Response getBalances(@PathParam("id") String id);

    @GET
    @Path("/accounts/{id}/transactions")
    public Response getTransactions(@PathParam("id") String id);

}
