package br.com.fiap.dto;

public class ProfissaoListarDto {
    private String nome;
    private String descricao;
    private String habilidades;
    private double media_salarial;


    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}

    public String getHabilidades() {return habilidades;}
    public void setHabilidades(String habilidades) {this.habilidades = habilidades;}

    public double getMedia_salarial() {return media_salarial;}
    public void setMedia_salarial(double media_salarial) {this.media_salarial = media_salarial;}
}
