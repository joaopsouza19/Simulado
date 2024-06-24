package br.edu.up.dals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import br.edu.up.models.*;

public class GerenciadorArquivos {
    private String arquivoPessoas;
    private String arquivoEnderecos;

    public GerenciadorArquivos(String arquivoPessoas, String arquivoEnderecos) {
        this.arquivoPessoas = arquivoPessoas;
        this.arquivoEnderecos = arquivoEnderecos;
    }

    public List<Pessoa> lerPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoPessoas))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int codigo = Integer.parseInt(dados[0].trim());
                String nome = dados[1].trim();
                pessoas.add(new Pessoa(codigo, nome));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pessoas;
    }

    public List<Endereco> lerEnderecos() {
        List<Endereco> enderecos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoEnderecos))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                String rua = dados[0].trim();
                String cidade = dados[1].trim();
                int pessoaCodigo = Integer.parseInt(dados[2].trim());
                enderecos.add(new Endereco(rua, cidade, pessoaCodigo));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return enderecos;
    }

    public void adicionarPessoa(Pessoa pessoa, String arquivoPessoas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoPessoas, true))) {
            bw.write(pessoa.getCodigo() + "; " + pessoa.getNome());
            bw.newLine();
            System.out.println("Pessoa adicionada com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adicionarEndereco(Endereco endereco, String arquivoEnderecos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoEnderecos, true))) {
            bw.write(endereco.getRua() + "; " + endereco.getCidade() + "; " + endereco.getPessoaCodigo());
            bw.newLine();
            System.out.println("Endereço adicionado com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gerarArquivoPessoasComEndereco(List<PessoaComEndereco> pessoasComEndereco, String arquivoSaida) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoSaida))) {
            for (PessoaComEndereco pessoa : pessoasComEndereco) {
                bw.write(pessoa.toString());
                bw.newLine();
            }
            System.out.println("Arquivo PessoasComEndereco.csv gerado com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<PessoaComEndereco> combinarPessoasEnderecos(List<Pessoa> pessoas, List<Endereco> enderecos) {
        List<PessoaComEndereco> pessoasComEndereco = new ArrayList<>();

        for (Endereco endereco : enderecos) {
            for (Pessoa pessoa : pessoas) {
                if (endereco.getPessoaCodigo() == pessoa.getCodigo()) {
                    pessoasComEndereco.add(new PessoaComEndereco(pessoa.getCodigo(), pessoa.getNome(),
                            endereco.getRua(), endereco.getCidade()));
                }
            }
        }

        return pessoasComEndereco;
    }
}
