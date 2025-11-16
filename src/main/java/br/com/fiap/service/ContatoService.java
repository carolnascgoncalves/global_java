package br.com.fiap.service;

import br.com.fiap.dao.ContatoDao;
import br.com.fiap.dto.ContatoCadastroDto;
import br.com.fiap.models.Contato;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.SQLException;
@ApplicationScoped
public class ContatoService {
    @Inject
    ContatoDao contatoDao;

    public Contato cadastrar(ContatoCadastroDto contato) throws SQLException {
        return contatoDao.cadastrar(contato);
    }
}
