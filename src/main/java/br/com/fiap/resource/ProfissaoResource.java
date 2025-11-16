package br.com.fiap.resource;

import br.com.fiap.dto.ProfissaoFavoritaDto;
import br.com.fiap.dto.ProfissaoListarDto;
import br.com.fiap.service.ProfissaoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;
@ApplicationScoped
@Path("/usuario/profissoes")
public class ProfissaoResource {
    @Inject
    ProfissaoService profissaoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProfissaoListarDto> listar(){
        return profissaoService.listar();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id_usu}")
    public Response adicionarFavorito(@PathParam("id_usu") int idUsu, ProfissaoFavoritaDto profissao){
        try {
            profissaoService.adicionarFavorito(idUsu, profissao);
            return Response.ok().build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
