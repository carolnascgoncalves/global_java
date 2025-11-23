package br.com.fiap.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

@Provider
@ApplicationScoped
public class CORSFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private final String apiKey;

    @Inject
    public CORSFilter(@ConfigProperty(name = "api_key") String apiKey) {
        this.apiKey = apiKey;
        System.out.println("API KEY LIDA PELO QUARKUS = " + this.apiKey);
    }

    @Override
    public void filter(ContainerRequestContext request) throws IOException {

        // Pré-flight (OPTIONS)
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            request.abortWith(
                    Response.ok()
                            .header("Access-Control-Allow-Origin", "*")
                            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, x-api-key")
                            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                            .build()
            );
            return;
        }

        // Validação da API KEY
        String headerKey = request.getHeaderString("x-api-key");

        if (apiKey == null || apiKey.isBlank()) {
            request.abortWith(
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity("API_KEY não configurada no servidor.")
                            .build()
            );
            return;
        }

        if (headerKey == null || !headerKey.equals(apiKey)) {
            request.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Acesso negado: x-api-key inválida ou ausente.")
                            .build()
            );
        }
    }

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        response.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
        response.getHeaders().putSingle("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, x-api-key");
        response.getHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}
