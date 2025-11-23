package br.com.fiap.resource;


import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/test-key")
@Produces(MediaType.TEXT_PLAIN)
public class KeyTestResource {

    @ConfigProperty(name = "api_key")
    String apiKey;

    @GET
    public String test() {
        return "API KEY = " + apiKey;
    }
}