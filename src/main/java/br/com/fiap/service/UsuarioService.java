package br.com.fiap.service;

import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.dto.UsuarioCriarDto;
import br.com.fiap.dto.UsuarioLoginDto;
import br.com.fiap.dto.UsuarioTrocarSenhaDto;
import br.com.fiap.models.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.SQLException;
@ApplicationScoped
public class UsuarioService {
    @Inject
    UsuarioDao usuarioDao;

    public Integer criarUsuario(UsuarioCriarDto usuario) throws SQLException{
        return usuarioDao.criarUsuario(usuario);
    }

    public Usuario login(UsuarioLoginDto login){
        return usuarioDao.login(login);
    }

    public boolean alterarSenha(int idUsu, UsuarioTrocarSenhaDto novaSenha) throws  SQLException{
        return usuarioDao.alterarSenha(idUsu, novaSenha);
    }

    public boolean deletarUsuario(int idUsu) throws  SQLException{
        return usuarioDao.deletarUsuario(idUsu);
    }
}
