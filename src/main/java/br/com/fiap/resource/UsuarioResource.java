package br.com.fiap.resource;

import br.com.fiap.dto.UsuarioCriarDto;
import br.com.fiap.dto.UsuarioLoginDto;
import br.com.fiap.dto.UsuarioTrocarSenhaDto;
import br.com.fiap.models.Usuario;
import br.com.fiap.service.UsuarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
@ApplicationScoped
@Path("/usuario")
public class UsuarioResource {
    @Inject
    UsuarioService usuarioService;

    @POST
    @Path("/criar-conta")
    public Response criarUsuario(UsuarioCriarDto usuario){
        try{
            return Response.ok(usuarioService.criarUsuario(usuario)).build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UsuarioLoginDto login){
        Usuario usuario = usuarioService.login(login);

        if(usuario.getNome() == null){
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"mensagem\": \"Usuário e/ou senha inválidos\"}")
                    .build();
        }

        return Response.ok(usuario).build();
    }

    @PUT
    @Path("/trocar-senha/{id_usuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterarSenha(@PathParam("id_usuario") int idUsu, UsuarioTrocarSenhaDto novaSenha){
        try{
            if(usuarioService.alterarSenha(idUsu, novaSenha)){
                return Response.ok()
                        .entity("{\"mensagem\": \"Senha alterada com sucesso!\"}")
                        .build();
            }

            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"mensagem\": \"ID não encontrado\"}")
                    .build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/deletar-conta/{id_usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarUsuario(@PathParam("id_usuario") int idUsu) throws  SQLException{
        try{
            if(usuarioService.deletarUsuario(idUsu)){
                return Response.ok()
                        .entity("{\"mensagem\": \"Conta apagada com sucesso!\"}")
                        .build();
            }

            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"mensagem\": \"ID não encontrado\"}")
                    .build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }
}
