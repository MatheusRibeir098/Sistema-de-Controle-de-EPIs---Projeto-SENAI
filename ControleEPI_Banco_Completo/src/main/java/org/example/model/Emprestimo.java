package org.example.model;

import java.time.LocalDateTime;

public class Emprestimo {
    private int idEmprestimo;
    private int idUsuario;
    private int idEpi;
    private LocalDateTime dataRetirada;
    private LocalDateTime dataPrevistaDevolucao;
    private boolean confirmacaoRetirada;

    public Emprestimo(int idEmprestimo, int idUsuario, int idEpi, LocalDateTime dataRetirada, LocalDateTime dataPrevistaDevolucao, boolean confirmacaoRetirada) {
        this.idEmprestimo = idEmprestimo;
        this.idUsuario = idUsuario;
        this.idEpi = idEpi;
        this.dataRetirada = dataRetirada;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.confirmacaoRetirada = confirmacaoRetirada;
    }

    public Emprestimo(int idUsuario, int idEpi, LocalDateTime dataRetirada, LocalDateTime dataPrevistaDevolucao) {
        this.idUsuario = idUsuario;
        this.idEpi = idEpi;
        this.dataRetirada = dataRetirada;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.confirmacaoRetirada = false;
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEpi() {
        return idEpi;
    }

    public void setIdEpi(int idEpi) {
        this.idEpi = idEpi;
    }

    public LocalDateTime getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(LocalDateTime dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public LocalDateTime getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(LocalDateTime dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public boolean isConfirmacaoRetirada() {
        return confirmacaoRetirada;
    }

    public void setConfirmacaoRetirada(boolean confirmacaoRetirada) {
        this.confirmacaoRetirada = confirmacaoRetirada;
    }
}
