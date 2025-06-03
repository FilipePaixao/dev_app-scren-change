package com.example.troca_tela;

public class Model {
    private String descricao;
    private String data;
    private String hora;
    private String prioridade;

    public Model(String descricao, String data, String hora, String prioridade) {
        this.descricao = descricao;
        this.data = data;
        this.hora = hora;
        this.prioridade = prioridade;
    }

    public String getDescricao() { return descricao; }
    public String getData() { return data; }
    public String getHora() { return hora; }
    public String getPrioridade() { return prioridade; }
}
