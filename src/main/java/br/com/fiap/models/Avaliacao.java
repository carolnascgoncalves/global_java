package br.com.fiap.models;

import br.com.fiap.enums.opAvaliacaoEnum;

public class Avaliacao {
    private int id;
    private opAvaliacaoEnum media;
    private String comentario;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public opAvaliacaoEnum getMedia() {return media;}
    public void setMedia(opAvaliacaoEnum media) {this.media = media;}

    public String getComentario() {return comentario;}
    public void setComentario(String comentario) {this.comentario = comentario;}
}
