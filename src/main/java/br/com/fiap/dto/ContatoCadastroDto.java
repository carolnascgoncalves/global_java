package br.com.fiap.dto;

import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.models.Contato;

public class ContatoCadastroDto {
    private String mensagem;
    private String email;

    public Contato toEntity(ContatoCadastroDto dto, int id){
        UsuarioDao usuDao = new UsuarioDao();

        return new Contato(id, dto.getMensagem(), dto.getMensagem());
    }

    public ContatoCadastroDto toDto(Contato contato){
        return new ContatoCadastroDto(contato.getMensagem(), contato.getEmail());
    }

    public String getMensagem() {return mensagem;}
    public void setMensagem(String mensagem) {this.mensagem = mensagem;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public ContatoCadastroDto(String mensagem, String email) {
        this.mensagem = mensagem;
        this.email = email;
    }
    public ContatoCadastroDto() {}
}
