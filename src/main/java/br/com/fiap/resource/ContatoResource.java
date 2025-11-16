package br.com.fiap.resource;

import br.com.fiap.dto.ContatoCadastroDto;
import br.com.fiap.models.Contato;
import br.com.fiap.service.ContatoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.internal.util.PropertyAlias;

import java.sql.SQLException;
@ApplicationScoped
@Path("/contato")
public class ContatoResource {
    @Inject
    ContatoService contatoService;

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response cadastrar(ContatoCadastroDto contato){
//        try{
//            return Response.ok(contatoService.cadastrar(contato)).build();
//        }catch (SQLException e){
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrar(ContatoCadastroDto contato){
        try{
            Contato contatoFinal = contatoService.cadastrar(contato);
            if(contatoFinal != null){
                return Response.ok(contatoFinal.getId()).build();
            }
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"mensagem\": \"Email inválido!\"}")
                    .build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
