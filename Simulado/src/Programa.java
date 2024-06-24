import br.edu.up.view.Menu;

public class Programa {
    public static void main(String[] args) {
        String arquivoPessoas = "Pessoas.csv";
        String arquivoEnderecos = "Enderecos.csv";
        String arquivoSaida = "PessoasComEndereco.csv";

        Menu menu = new Menu(arquivoPessoas, arquivoEnderecos, arquivoSaida);
        menu.exibirMenu();
    }
}
