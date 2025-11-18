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

    @DELETE
    @Path("/desfavoritar/{id_usu}/{id_prof}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarFavorito(@PathParam("id_usu") int idUsu, @PathParam("id_prof") int idProf){
        try{
            if(profissaoService.deletarFavorito(idUsu, idProf)){
                return Response.ok()
                        .entity("{\"mensagem\": \"Profissão desfavoritada com sucesso!\"}")
                        .build();
            }

            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"mensagem\": \"ID inválido não encontrado\"}")
                    .build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/favoritos/{id_usu}")
    public List<ProfissaoListarDto> listarFavoritos(@PathParam("id_usu") int idUsu){
        return profissaoService.listarFavoritos(idUsu);
    }
}
