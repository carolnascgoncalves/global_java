package br.com.fiap.resource;

import br.com.fiap.dto.ContatoCadastroDto;
import br.com.fiap.service.ContatoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
@ApplicationScoped
@Path("/usuario/contato")
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
    public Response cadastrar(ContatoCadastroDto contato){
        try{
            return Response.ok(contatoService.cadastrar(contato).getId()).build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
