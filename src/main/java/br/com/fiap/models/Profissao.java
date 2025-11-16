package br.com.fiap.models;

public class Profissao {
    private int id;
    private String nome;
    private String descricao;
    private String habilidades;
    private double media_salarial;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}

    public String getHabilidades() {return habilidades;}
    public void setHabilidades(String habilidades) {this.habilidades = habilidades;}

    public double getMedia_salarial() {return media_salarial;}
    public void setMedia_salarial(double media_salarial) {this.media_salarial = media_salarial;}


    public Profissao(){}
    public Profissao(int id, String nome, String descricao, String habilidades, double media_salarial) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.habilidades = habilidades;
        this.media_salarial = media_salarial;
    }
}
