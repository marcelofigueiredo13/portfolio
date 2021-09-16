package com.example.pintv20;

public class ListItemRanking {
    private Integer avatar;
    private String nome;
    private Integer pontos;

    public ListItemRanking(Integer avatar, String nome, Integer pontos) {
        this.avatar = avatar;
        this.nome = nome;
        this.pontos = pontos;
    }

    public Integer getAvatar() {
        return avatar;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }
}
