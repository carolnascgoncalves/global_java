package br.com.fiap.service;

import br.com.fiap.dao.AvaliacaoDao;
import br.com.fiap.dto.AvaliacaoCadastroDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.SQLException;
@ApplicationScoped
public class AvaliacaoService {
    @Inject
    AvaliacaoDao avaliacaoDao;

    public int cadastrar(AvaliacaoCadastroDto avaliacao) throws SQLException {
        return avaliacaoDao.cadastrar(avaliacao);
    }
}
