package br.com.fiap.dto;

import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.models.Contato;

public class ContatoCadastroDto {
    private String mensagem;
    private String email;
    private int id_usuario;

    public Contato toEntity(ContatoCadastroDto dto, int id){
        UsuarioDao usuDao = new UsuarioDao();

        return new Contato(id, dto.getMensagem(), dto.getMensagem(), usuDao.buscarPorId(dto.getId_usuario()));
    }

    public ContatoCadastroDto toDto(Contato contato){
        return new ContatoCadastroDto(contato.getMensagem(), contato.getEmail(), contato.getUsuario().getId());
    }

    public String getMensagem() {return mensagem;}
    public void setMensagem(String mensagem) {this.mensagem = mensagem;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public int getId_usuario() {return id_usuario;}
    public void setId_usuario(int id) {this.id_usuario = id;}


    public ContatoCadastroDto(String mensagem, String email, int id_usuario) {
        this.mensagem = mensagem;
        this.email = email;
        this.id_usuario = id_usuario;
    }
    public ContatoCadastroDto() {}
}
