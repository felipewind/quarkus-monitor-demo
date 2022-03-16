package org.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.logging.Logger;

@Path("/extraction")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ExtractionResource {

    private static final Logger LOG = Logger.getLogger(ExtractionResource.class);

    @Inject
    AccountExtractorService accountExtractorService;

    @POST
    @Path("/start-extraction/{quantity}")
    public Response postStartExtraction(@Parameter(example = "100") @PathParam("quantity") int quantity) {
        LOG.info("postStartExtraction() - Quantity of items to be extracted = " + quantity);

        accountExtractorService.extractionProcess(quantity);

        return Response.status(Status.ACCEPTED).build();

    }

}