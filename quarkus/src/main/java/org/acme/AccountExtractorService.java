package org.acme;

import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.jboss.logging.Logger;

@ApplicationScoped
public class AccountExtractorService {

    private static final Logger LOG = Logger.getLogger(AccountExtractorService.class);

    public static final Queue<String> accountQueue = new ConcurrentLinkedQueue<>();

    @ConfigProperty(name = "app.extraction.thread.pool")
    int extractionThreadPool;

    @Inject
    AccountResource accountResource;

    void extractionProcess(int quantity) {
        LOG.info("Extraction thread pool: " + extractionThreadPool);

        accountQueue.clear();

        for (int i = 1; i <= quantity; i++) {
            accountQueue.offer(i + "");
        }

        for (int i = 0; i < extractionThreadPool; i++) {
            processQueue();
        }

        LOG.info("All extraction threads were started");

    }

    @Asynchronous
    Future<Integer> processQueue() {
        LOG.info("Extraction STARTED ");

        var quantityAccountsProcessed = 0;

        var id = accountQueue.poll();

        while (id != null) {
            quantityAccountsProcessed++;
            extractAccount(id);
            id = accountQueue.poll();
        }

        LOG.info("Extraction FINISHED, total accounts processed = " + quantityAccountsProcessed);

        return CompletableFuture.completedFuture(quantityAccountsProcessed);

    }

    private void extractAccount(String id) {
        LOG.info("Extraction id " + id + " STARTED ");
        accountResource.get(id);
        accountResource.getBalances(id);
        accountResource.getTransactions(id);
        LOG.info("Extraction id " + id + " FINISHED ");

    }

}
