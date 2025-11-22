package br.com.fiap.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

import br.com.fiap.resource.*;
import br.com.fiap.resource.ContatoResource;
import br.com.fiap.resource.FaqResource;
import br.com.fiap.resource.ProfissaoResource;
import br.com.fiap.resource.UsuarioResource;

@ApplicationPath("/")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();

        classes.add(AvaliacaoResource.class);
        classes.add(ContatoResource.class);
        classes.add(FaqResource.class);
        classes.add(ProfissaoResource.class);
        classes.add(UsuarioResource.class);

        classes.add(CORSFilter.class);

        return classes;
    }
}
