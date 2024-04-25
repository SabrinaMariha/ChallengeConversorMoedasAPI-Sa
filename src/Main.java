import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principal responsável por lidar com a conversão de moedas.
 */
public class Main {

    /**
     * Método principal que exibe o menu e realiza a conversão de moedas.
     *
     * @param args Argumentos da linha de comando (não utilizados neste programa).
     * @throws IOException Se ocorrer um erro de I/O.
     * @throws InterruptedException Se o programa for interrompido.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        do {
            // Instancia um objeto da classe Menu e exibe o menu
            Menu menu = new Menu();
            menu.exibirMenu(true);

            // Obtém as moedas selecionadas pelo usuário
            List<Moeda> moedasListadas = menu.decidirOpcao();

            // Realiza a conversão de moedas
            Conversao conversao = new Conversao();
            conversao.Converter(moedasListadas);

            // Pergunta ao usuário se deseja realizar outra conversão
            System.out.println("Deseja realizar outra conversão? 1-Sim 0-Não");
            if (sc.hasNextInt()) {
                continuar = sc.nextInt() == 1;
                sc.nextLine(); // Limpa o buffer de entrada
            } else {
                continuar = false; // Interrompe o loop se não houver mais entrada
            }
        } while (continuar);

        sc.close();
    }
}
