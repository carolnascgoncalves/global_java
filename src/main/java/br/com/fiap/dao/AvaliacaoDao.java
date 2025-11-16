package br.com.fiap.dao;

import br.com.fiap.dto.AvaliacaoCadastroDto;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
public class AvaliacaoDao {
    private Connection conexao;

    public int cadastrar(AvaliacaoCadastroDto avaliacao){
        String sql = "insert into avaliacao(media_ava)\n" +
                "values(?)";
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        Integer id = null;

        try{
            if(avaliacao.getComentario() != null) {
                String sqlCompleto = "insert into avaliacao(media_ava, coment_ava)\n" +
                        "values(?,?)";

                ps = conexao.prepareStatement(sqlCompleto, new String[]{"id_ava"});

                ps.setString(1, avaliacao.getMedia().toString());
                ps.setString(2, avaliacao.getComentario());

                ps.executeUpdate();
            }else{
                ps = conexao.prepareStatement(sql, new String[]{"id_ava"});
                ps.setString(1, avaliacao.getMedia().toString());

                ps.executeUpdate();
            }


            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return id;
    }
}
