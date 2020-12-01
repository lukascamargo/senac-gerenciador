package com.example.gerenciadorv3.model;

import java.io.Serializable;

public class Colaboradores implements Serializable {
    private Long id;
    private String nomeColaborador;
    private String emailColaborador;
    private int telefoneColaborador;
    private int idadeColaborador;
    private String cargoColaborador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeColaborador() {
        return nomeColaborador;
    }

    public void setNomeColaborador(String nomeColaborador) {
        this.nomeColaborador = nomeColaborador;
    }

    public String getEmailColaborador() {
        return emailColaborador;
    }

    public void setEmailColaborador(String emailColaborador) {
        this.emailColaborador = emailColaborador;
    }

    public int getTelefoneColaborador() {
        return telefoneColaborador;
    }

    public void setTelefoneColaborador(int telefoneColaborador) {
        this.telefoneColaborador = telefoneColaborador;
    }

    public int getIdadeColaborador() {
        return idadeColaborador;
    }

    public void setIdadeColaborador(int idadeColaborador) {
        this.idadeColaborador = idadeColaborador;
    }

    public String getCargoColaborador() {
        return cargoColaborador;
    }

    public void setCargoColaborador(String cargoColaborador) {
        this.cargoColaborador = cargoColaborador;
    }
}
