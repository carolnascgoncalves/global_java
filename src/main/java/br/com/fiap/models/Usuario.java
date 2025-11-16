package br.com.fiap.models;

import br.com.fiap.enums.SexoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.sql.Date;

@XmlRootElement
public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private SexoEnum sexo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
    private Date data_nasc;
    private String uf;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}

    public SexoEnum getSexo() {return sexo;}
    public void setSexo(SexoEnum sexo) {this.sexo = sexo;}

    public Date getData_nasc() {return data_nasc;}
    public void setData_nasc(Date data_nasc) {this.data_nasc = data_nasc;}

    public String getUf() {return uf;}
    public void setUf(String uf) {this.uf = uf;}
}
