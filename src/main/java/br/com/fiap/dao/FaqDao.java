package br.com.fiap.dao;

import br.com.fiap.dto.FaqMostrarDto;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FaqDao {
    private Connection conexao;

    public List<FaqMostrarDto> listar(){
        List<FaqMostrarDto> faqs = new ArrayList<>();
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;

        try{
            String sql = "select * from faq";
            ps = conexao.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                FaqMostrarDto faq = new FaqMostrarDto();
                faq.setPergunta(rs.getString("perg_faq"));
                faq.setResposta(rs.getString("resp_faq"));

                faqs.add(faq);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return faqs;
    }
}
