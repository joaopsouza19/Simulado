package br.edu.up.models;

public class PessoaComEndereco {
    private int codigo;
    private String nome;
    private String rua;
    private String cidade;

    public PessoaComEndereco(int codigo, String nome, String rua, String cidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.rua = rua;
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return codigo + "; " + nome + "; " + rua + "; " + cidade;
    }
}
