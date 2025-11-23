package br.com.fiap.config;

import org.glassfish.jersey.server.ResourceConfig;
import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig() {
        packages("br.com.fiap");
        register(br.com.fiap.config.CORSFilter.class);
    }
}
