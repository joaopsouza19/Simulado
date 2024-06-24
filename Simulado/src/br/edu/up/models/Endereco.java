package br.edu.up.models;

// Endereco.java
public class Endereco {
    private String rua;
    private String cidade;
    private int pessoaCodigo;

    public Endereco(String rua, String cidade, int pessoaCodigo) {
        this.rua = rua;
        this.cidade = cidade;
        this.pessoaCodigo = pessoaCodigo;
    }

    public String getRua() {
        return rua;
    }

    public String getCidade() {
        return cidade;
    }

    public int getPessoaCodigo() {
        return pessoaCodigo;
    }

    @Override
    public String toString() {
        return rua + "; " + cidade + "; " + pessoaCodigo;
    }
}
