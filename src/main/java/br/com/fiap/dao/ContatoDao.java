package br.com.fiap.dao;

import br.com.fiap.dto.ContatoCadastroDto;
import br.com.fiap.models.Contato;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
public class ContatoDao {
    private Connection conexao;

    public Contato cadastrar(ContatoCadastroDto contato) throws SQLException {
        String sql = "insert into contato(mensagem_cont, email_cont)\n" +
                "values(?,?)";
        conexao = ConnectionFactory.obterConexao();
        Integer id = null;


        try(PreparedStatement ps = conexao.prepareStatement(sql, new String[]{"id_cont"})){

            ps.setString(1, contato.getMensagem());
            ps.setString(2, contato.getEmail());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        }

        return contato.toEntity(contato, id);
    }
}
