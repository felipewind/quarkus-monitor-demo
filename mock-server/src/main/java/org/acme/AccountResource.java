package org.acme;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.logging.Logger;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    private static final Logger LOG = Logger.getLogger(AccountResource.class);

    @GET
    @Path("{id}")
    public Response get(@Parameter(example = "1") @PathParam("id") String id) throws InterruptedException {

        LOG.info("get() " + id);

        var account = new Account(UUID.randomUUID().toString(), "BankBoston");

        var delay = delay();

        return Response
                .status(Status.OK)
                .header("delay-test", delay)
                .entity(account)
                .build();

    }

    @GET
    @Path("{id}/balances")
    public Response getBalances(@Parameter(example = "1") @PathParam("id") String id) throws InterruptedException {

        LOG.info("getBalances() " + id);

        var accountBallance = new AccountBallance(new BigDecimal(new Random().nextInt(200)),
                new BigDecimal(new Random().nextInt(10)));

        var delay = delay();

        return Response
                .status(Status.OK)
                .header("delay-test", delay)
                .entity(accountBallance)
                .build();

    }

    @GET
    @Path("{id}/transactions")
    public Response getTransactions(@Parameter(example = "1") @PathParam("id") String id) throws InterruptedException {

        LOG.info("getTransactions() " + id);

        var limit = new Random().nextInt(10);

        var listAccountTransactions = new ArrayList<AccountTransaction>();

        for (int i = 0; i < limit; i++) {

            var creditDebitType = (new Random().nextInt(200) % 2 == 0) ? "CREDIT" : "DEBIT";

            listAccountTransactions.add(new AccountTransaction(UUID.randomUUID().toString(), "Payment", creditDebitType,
                    new BigDecimal(new Random().nextInt(200))));

        }

        var accountTransactions = new AccountTransactions(listAccountTransactions);

        var delay = delay();

        return Response
                .status(Status.OK)
                .header("delay-test", delay)
                .entity(accountTransactions)
                .build();

    }

    private int delay() throws InterruptedException {
        var delay = new Random().nextInt(2000);
        Thread.sleep(delay);
        return delay;
    }

}