package br.com.fiap.config;

import jakarta.ws.rs.container.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CORSFilter implements ContainerRequestFilter, ContainerResponseFilter {
    private static final String API_KEY = System.getenv("API_KEY");

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
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

        String headerKey = request.getHeaderString("x-api-key");

        if (API_KEY == null || API_KEY.isBlank()) {
            request.abortWith(
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity("API_KEY não configurada no servidor.")
                            .build()
            );
            return;
        }

        if (headerKey == null || !headerKey.equals(API_KEY)) {
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