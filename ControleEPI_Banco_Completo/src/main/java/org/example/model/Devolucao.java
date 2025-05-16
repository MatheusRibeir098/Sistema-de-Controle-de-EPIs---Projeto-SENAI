package org.example.model;

import java.util.Date;

public class Devolucao {
    private int idDevolucao;
    private int idEmprestimo;
    private Date dataDevolucao;

    public Devolucao(int idEmprestimo, int id_emprestimo, Date dataDevolucao) {
        this.idEmprestimo = idEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public int getIdDevolucao() {
        return idDevolucao;
    }

    public void setIdDevolucao(int idDevolucao) {
        this.idDevolucao = idDevolucao;
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
