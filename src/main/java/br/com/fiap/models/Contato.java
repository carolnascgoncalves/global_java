package br.com.fiap.models;

import jakarta.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Contato {
    private int id;
    private String mensagem;
    private String email;
    private Usuario usuario;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getMensagem() {return mensagem;}
    public void setMensagem(String mensagem) {this.mensagem = mensagem;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public Usuario getUsuario() {return usuario;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}


    public Contato(int id, String mensagem, String email, Usuario usuario) {
        this.id = id;
        this.mensagem = mensagem;
        this.email = email;
        this.usuario = usuario;
    }

    public Contato(String mensagem, String email, Usuario usuario) {
        this.mensagem = mensagem;
        this.email = email;
        this.usuario = usuario;
    }

    public Contato() {}
}
