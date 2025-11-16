package br.com.fiap.dao;

import br.com.fiap.dto.UsuarioCriarDto;
import br.com.fiap.dto.UsuarioLoginDto;
import br.com.fiap.dto.UsuarioTrocarSenhaDto;
import br.com.fiap.enums.SexoEnum;
import br.com.fiap.models.Usuario;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
public class UsuarioDao {
    private Connection conexao;

    public boolean buscarPorEmail(String email){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try {
            String sql = "select * from usuario where email_usu = ?";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public Usuario buscarPorId(int id){
        Usuario usuario = new Usuario();
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try {
            String sql = "select * from usuario where id_usu = ?";
            ps = conexao.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                usuario.setNome(rs.getString("nome_usu"));
                usuario.setEmail(rs.getString("email_usu"));
                usuario.setSenha(rs.getString("senha_usu"));
                usuario.setSexo(SexoEnum.valueOf(rs.getString("sexo_usu")));
                usuario.setData_nasc(rs.getDate("data_nasc_usu"));
                usuario.setUf(rs.getString("uf_usu"));
            }

            try (ResultSet rsId = ps.getGeneratedKeys()) {
                if (rsId.next()) {
                    usuario.setId(rs.getInt(1));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return usuario;
    }

    public Integer criarUsuario(UsuarioCriarDto usuario) throws SQLException{
        String sql = "insert into usuario(nome_usu, email_usu, senha_usu, sexo_usu, uf_usu, data_nasc_usu)\n" +
                "values(?,?,?,?,?,?)";
        conexao = ConnectionFactory.obterConexao();
        Integer id = null;

        try(PreparedStatement ps = conexao.prepareStatement(sql, new String[]{"id_usu"})){
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getSexo().toString());
            ps.setString(5, usuario.getUf());
            ps.setDate(6, usuario.getData_nasc());

            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    id = rs.getInt(1);
                }
            }
        }

        return id;
    }

    public Usuario login(UsuarioLoginDto login){
        conexao = ConnectionFactory.obterConexao();
        Usuario usuario = new Usuario();
        PreparedStatement ps = null;

        try{
            String sql = "select * from usuario \n" +
                    "where email_usu = ? and senha_usu = ?";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, login.getEmail());
            ps.setString(2, login.getSenha());

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                usuario.setId(rs.getInt("id_usu"));
                usuario.setNome(rs.getString("nome_usu"));
                usuario.setEmail(rs.getString("email_usu"));
                usuario.setSenha(rs.getString("senha_usu"));
                usuario.setSexo(SexoEnum.valueOf(rs.getString("sexo_usu")));
                usuario.setUf(rs.getString("uf_usu"));
                usuario.setData_nasc(rs.getDate("data_nasc_usu"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return usuario;
    }

    public boolean alterarSenha(int idUsu, UsuarioTrocarSenhaDto novaSenha) throws  SQLException{
        String sql = "update usuario\n" +
                "set senha_usu = ?\n" +
                "where id_usu = ?";
        conexao = ConnectionFactory.obterConexao();
        if(buscarPorId(idUsu).getNome() == null){
            return false;
        }

        try(PreparedStatement ps = conexao.prepareStatement(sql)){

            ps.setString(1, novaSenha.getNovaSenha());
            ps.setInt(2, idUsu);

            ps.executeUpdate();
        }

        return true;
    }

    public boolean deletarUsuario(int idUsu) throws  SQLException{
        String sql = "delete from usuario where id_usu = ?";
        conexao = ConnectionFactory.obterConexao();
        if(buscarPorId(idUsu).getNome() == null){
            return false;
        }

        try(PreparedStatement ps = conexao.prepareStatement(sql)){

            ps.setInt(1, idUsu);

            ps.executeUpdate();
        }

        return true;
    }
}
