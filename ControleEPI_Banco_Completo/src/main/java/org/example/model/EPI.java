package org.example.model;

public class EPI {

    private int idEpi;  // id_epi
    private String nome;
    private int quantidade;  // quantidade

    // Construtor com todos os atributos
    public EPI(int idEpi, String nome, int quantidade) {
        this.idEpi = idEpi;
        this.nome = nome;
        this.quantidade = quantidade;
    }

    // Construtor sem o idEpi (caso seja gerado automaticamente)
    public EPI(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public int getIdEpi() {
        return idEpi;
    }

    public void setIdEpi(int idEpi) {
        this.idEpi = idEpi;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
