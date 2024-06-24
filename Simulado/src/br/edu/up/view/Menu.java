package br.edu.up.view;

import br.edu.up.models.*;
import br.edu.up.dals.*;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private GerenciadorArquivos gerenciador;
    private String arquivoPessoas;
    private String arquivoEnderecos;
    private String arquivoSaida;

    public Menu(String arquivoPessoas, String arquivoEnderecos, String arquivoSaida) {
        this.arquivoPessoas = arquivoPessoas;
        this.arquivoEnderecos = arquivoEnderecos;
        this.arquivoSaida = arquivoSaida;
        this.gerenciador = new GerenciadorArquivos(arquivoPessoas, arquivoEnderecos);
    }

    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        int escolha;

        do {
            System.out.println("\nMENU:");
            System.out.println("1. Adicionar nova pessoa");
            System.out.println("2. Adicionar novo endereço");
            System.out.println("3. Gerar arquivo PessoasComEndereco.csv");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (escolha) {
                case 1:
                    adicionarPessoa();
                    break;
                case 2:
                    adicionarEndereco();
                    break;
                case 3:
                    gerarArquivoPessoasComEndereco();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (escolha != 0);

        scanner.close();
    }

    private void adicionarPessoa() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o código da pessoa: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        System.out.print("Digite o nome da pessoa: ");
        String nome = scanner.nextLine();
        Pessoa novaPessoa = new Pessoa(codigo, nome);
        gerenciador.adicionarPessoa(novaPessoa, arquivoPessoas);
    }

    private void adicionarEndereco() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a rua do endereço: ");
        String rua = scanner.nextLine();
        System.out.print("Digite a cidade do endereço: ");
        String cidade = scanner.nextLine();
        System.out.print("Digite o código da pessoa para associar o endereço: ");
        int codigoPessoa = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        Endereco novoEndereco = new Endereco(rua, cidade, codigoPessoa);
        gerenciador.adicionarEndereco(novoEndereco, arquivoEnderecos);
    }

    private void gerarArquivoPessoasComEndereco() {
        List<Pessoa> pessoas = gerenciador.lerPessoas();
        List<Endereco> enderecos = gerenciador.lerEnderecos();
        List<PessoaComEndereco> pessoasComEndereco = gerenciador.combinarPessoasEnderecos(pessoas, enderecos);
        gerenciador.gerarArquivoPessoasComEndereco(pessoasComEndereco, arquivoSaida);
        System.out.println("Arquivo PessoasComEndereco.csv gerado com sucesso!");
    }
}
