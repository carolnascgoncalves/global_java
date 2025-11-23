package br.com.fiap.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/test-key")
public class KeyTestResource {

    @ConfigProperty(name = "api_key")
    String apiKey;

    @GET
    public String test() {
        return "API KEY = " + apiKey;
    }
}
