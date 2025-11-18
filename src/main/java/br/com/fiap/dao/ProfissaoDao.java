package br.com.fiap.dao;

import br.com.fiap.dto.ProfissaoFavoritaDto;
import br.com.fiap.dto.ProfissaoListarDto;
import br.com.fiap.models.Profissao;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProfissaoDao {
    private Connection conexao;

    public Profissao buscarPorNome(String nome){
        conexao = ConnectionFactory.obterConexao();
        Profissao profissao = new Profissao();
        PreparedStatement ps = null;

        try{
            String sql = "select * from profissao where nome_prof = ?";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, nome);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                profissao.setId(rs.getInt("id_prof"));
                profissao.setNome(rs.getString("nome_prof"));
                profissao.setDescricao(rs.getString("desc_prof"));
                profissao.setHabilidades(rs.getString("habilidades_prof"));
                profissao.setMedia_salarial(rs.getDouble("med_sal_prof"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return profissao;
    }

    public List<ProfissaoListarDto> listar(){
        List<ProfissaoListarDto> profissoes = new ArrayList<>();
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;

        try{
            String sql = "select * from profissao";
            ps = conexao.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ProfissaoListarDto profissao = new ProfissaoListarDto();

                profissao.setNome(rs.getString("nome_prof"));
                profissao.setDescricao(rs.getString("desc_prof"));
                profissao.setHabilidades(rs.getString("habilidades_prof"));
                profissao.setMedia_salarial(rs.getDouble("med_sal_prof"));

                profissoes.add(profissao);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return profissoes;
    }

    public void adicionarFavorito(int idUsu, ProfissaoFavoritaDto profFav) throws SQLException{
        String sql = "insert into usu_prof(id_usu, id_prof)\n" +
                "values(?,?)";
        conexao = ConnectionFactory.obterConexao();

        try(PreparedStatement ps = conexao.prepareStatement(sql)){

            ps.setInt(1, idUsu);
            ps.setInt(2, buscarPorNome(profFav.getNome()).getId());

            ps.executeUpdate();
        }
    }

    public Profissao buscarPorId(int id){
        Profissao profissao = new Profissao();
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try {
            String sql = "select * from profissao where id_prof = ?";
            ps = conexao.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                profissao.setNome(rs.getString("nome_prof"));
                profissao.setDescricao(rs.getString("desc_prof"));
                profissao.setHabilidades(rs.getString("habilidades_prof"));
                profissao.setMedia_salarial(rs.getDouble("med_sal_prof"));
            }

            try (ResultSet rsId = ps.getGeneratedKeys()) {
                if (rsId.next()) {
                    profissao.setId(rs.getInt(1));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return profissao;
    }

    public boolean deletarFavorito(int idUsu, int idProf) throws  SQLException{
        String sql = "delete from usu_prof \n" +
                "where id_usu = ? and id_prof = ?";
        conexao = ConnectionFactory.obterConexao();
        if(buscarPorId(idProf).getNome() == null){
            return false;
        }

        try(PreparedStatement ps = conexao.prepareStatement(sql)){

            ps.setInt(1, idUsu);
            ps.setInt(2, idProf);

            ps.executeUpdate();
        }

        return true;
    }

    public List<ProfissaoListarDto> listarFavoritos(int idUsu){
        List<ProfissaoListarDto> profissoes = new ArrayList<>();
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;

        try{
            String sql = "select prof.nome_prof, prof.desc_prof, prof.habilidades_prof, prof.med_sal_prof from usuario usu\n" +
                    "join usu_prof on usu.id_usu = usu_prof.id_usu\n" +
                    "join profissao prof on prof.id_prof = usu_prof.id_prof" +
                    "where usu.id_usu = ?";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, idUsu);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ProfissaoListarDto profissao = new ProfissaoListarDto();

                profissao.setNome(rs.getString("nome_prof"));
                profissao.setDescricao(rs.getString("desc_prof"));
                profissao.setHabilidades(rs.getString("habilidades_prof"));
                profissao.setMedia_salarial(rs.getDouble("med_sal_prof"));

                profissoes.add(profissao);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return profissoes;
    }
}
