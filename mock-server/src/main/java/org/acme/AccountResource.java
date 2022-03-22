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

        var delay = new Random().nextInt(1000);

        LOG.info("get() id = " + id + " delay = " + delay);

        Thread.sleep(delay);

        var account = new Account(id, "BankBoston");

        LOG.info("get() id = " + id + " delay = " + delay + " RESPONSE");

        return Response
                .status(Status.OK)
                .header("delay-time", delay)
                .entity(account)
                .build();

    }

    @GET
    @Path("{id}/balances")
    public Response getBalances(@Parameter(example = "1") @PathParam("id") String id) throws InterruptedException {

        var delay = new Random().nextInt(2000);

        LOG.info("getBalances() " + id + " delay = " + delay);

        Thread.sleep(delay);

        var accountBallance = new AccountBallance(new BigDecimal(new Random().nextInt(200)),
                new BigDecimal(new Random().nextInt(10)));

        LOG.info("getBalances() " + id + " delay = " + delay + " RESPONSE");

        return Response
                .status(Status.OK)
                .header("delay-time", delay)
                .entity(accountBallance)
                .build();

    }

    @GET
    @Path("{id}/transactions")
    public Response getTransactions(@Parameter(example = "1") @PathParam("id") String id) throws InterruptedException {

        var delay = new Random().nextInt(3000);

        LOG.info("getTransactions() " + id + " delay = " + delay);

        Thread.sleep(delay);

        var limit = new Random().nextInt(10);

        var listAccountTransactions = new ArrayList<AccountTransaction>();

        for (int i = 0; i < limit; i++) {

            var creditDebitType = (new Random().nextInt(200) % 2 == 0) ? "CREDIT" : "DEBIT";

            listAccountTransactions.add(new AccountTransaction(UUID.randomUUID().toString(), "Payment", creditDebitType,
                    new BigDecimal(new Random().nextInt(200))));

        }

        var accountTransactions = new AccountTransactions(listAccountTransactions);

        LOG.info("getTransactions() " + id + " delay = " + delay + " RESPONSE");

        return Response
                .status(Status.OK)
                .header("delay-time", delay)
                .entity(accountTransactions)
                .build();

    }

}