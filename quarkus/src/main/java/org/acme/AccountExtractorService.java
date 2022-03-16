package org.acme;

import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.jboss.logging.Logger;

@ApplicationScoped
public class AccountExtractorService {

    private static final Logger LOG = Logger.getLogger(AccountExtractorService.class);

    public static final Queue<String> accountQueue = new ConcurrentLinkedQueue<>();

    @ConfigProperty(name = "app.extraction.thread.pool")
    int extractionThreadPool;

    @Inject
    AccountResource accountResource;

    // @Asynchronous
    @Bulkhead(value = 1)
    Future<String> extractionProcess(int quantity) {
        LOG.info("Extraction thread pool size: " + extractionThreadPool);

        accountQueue.clear();

        for (int i = 1; i <= quantity; i++) {
            extractAccount(i + "");
            // accountQueue.offer(i + "");
        }

        LOG.info("finish");

        // LOG.info("Processes sent to Queue");

        // var threadReturnFuture = new ArrayList<Future<Integer>>();

        // for (int i = 0; i < extractionThreadPool; i++) {
        //     threadReturnFuture.add(processQueue());
        // }

        // LOG.info("All extraction threads were started, waiting for completion of them");

        // threadReturnFuture.forEach(f -> {
        //     try {
        //         f.get(5L, TimeUnit.MINUTES);
        //     } catch (Exception e) {
        //         e.printStackTrace();
        //     }
        // });

        // LOG.info("All extraction threads finished");

        return CompletableFuture.completedFuture("Process started");

    }

    @Asynchronous
    Future<Integer> processQueue() {
        LOG.info("Extraction thread STARTED ");

        var quantityAccountsProcessed = 0;

        var id = accountQueue.poll();

        while (id != null) {
            quantityAccountsProcessed++;
            extractAccount(id);
            id = accountQueue.poll();
        }

        LOG.info("Extraction thread FINISHED, total accounts processed = " + quantityAccountsProcessed);

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
