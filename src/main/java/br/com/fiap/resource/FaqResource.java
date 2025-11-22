package br.com.fiap.resource;

import br.com.fiap.service.FaqService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
@ApplicationScoped

@Path("/faq")
public class FaqResource {
    @Inject
    FaqService faqService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(){
        if(faqService.listar().isEmpty()){
            return Response.noContent().build();
        }

        return Response.ok(faqService.listar()).build();
    }
}
