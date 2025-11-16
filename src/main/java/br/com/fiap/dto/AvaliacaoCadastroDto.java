package br.com.fiap.dto;

import br.com.fiap.enums.opAvaliacaoEnum;

public class AvaliacaoCadastroDto {
    private opAvaliacaoEnum media;
    private String comentario;

    public opAvaliacaoEnum getMedia() {return media;}
    public void setMedia(opAvaliacaoEnum media) {this.media = media;}

    public String getComentario() {return comentario;}
    public void setComentario(String comentario) {this.comentario = comentario;}
}
