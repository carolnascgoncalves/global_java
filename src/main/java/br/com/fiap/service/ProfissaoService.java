package br.com.fiap.service;

import br.com.fiap.dao.ProfissaoDao;
import br.com.fiap.dto.ProfissaoFavoritaDto;
import br.com.fiap.dto.ProfissaoListarDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.SQLException;
import java.util.List;
@ApplicationScoped
public class ProfissaoService {
    @Inject
    ProfissaoDao profissaoDao;

    public List<ProfissaoListarDto> listar(){
        return profissaoDao.listar();
    }

    public void adicionarFavorito(int idUsu, ProfissaoFavoritaDto profissao) throws SQLException {
        profissaoDao.adicionarFavorito(idUsu, profissao);
    }
}
