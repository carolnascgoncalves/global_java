package br.com.fiap.resource;

import br.com.fiap.dto.AvaliacaoCadastroDto;
import br.com.fiap.service.AvaliacaoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
@ApplicationScoped
@Path("/avaliacao")
public class AvaliacaoResource {
    @Inject
    AvaliacaoService avaliacaoService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(AvaliacaoCadastroDto avaliacao){
        try{
            return Response.ok(avaliacaoService.cadastrar(avaliacao)).build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
