import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável por realizar a conversão de moedas.
 */
public class Conversao {
    /**
     * Realiza a conversão de moedas com base nas opções fornecidas pelo usuário.
     *
     * @param moedasListadas Lista de moedas disponíveis para conversão.
     * @throws IOException Se ocorrer um erro de I/O.
     * @throws InterruptedException Se o programa for interrompido.
     */
    public void Converter(List<Moeda> moedasListadas) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        try {
            // Exibe o menu para escolha da moeda final
            Menu menu = new Menu();
            menu.exibirMenu(false);
            String moedaConver = sc.nextLine().toUpperCase();

            if (moedaConver.equalsIgnoreCase("OUTRA")) {
                boolean moedaEncontrada = false;
                do {
                    System.out.println("1. Para digitar a sigla da moeda que deseja converter." +
                            "\n2. Para ver a lista de moedas disponíveis.");
                    int opcaoMoeda = sc.nextInt();
                    if (opcaoMoeda == 1) {
                        System.out.println("Digite a sigla da moeda para a qual deseja converter:");
                        moedaConver = sc.next();
                    } else if (opcaoMoeda == 2) {
                        for (Moeda moedaItem : moedasListadas) {
                            System.out.println(moedaItem.getMoedaBase() + " " + moedaItem.getValorBase());
                        }
                        System.out.println("Digite a sigla da moeda para a qual deseja converter:");
                        moedaConver = sc.next();
                    } else {
                        System.out.println("Opção inválida!");
                        continue;
                    }
                    for (Moeda moedaItem : moedasListadas) {
                        if (moedaItem.getMoedaBase().equals(moedaConver)) {
                            moedaEncontrada = true;
                            break;
                        }
                    }
                    if (!moedaEncontrada) {
                        System.out.println("Moeda não encontrada!");
                    }
                } while (!moedaEncontrada);
            } else if (moedaConver.equalsIgnoreCase("SAIR")) {
                System.out.println("Obrigado por utilizar o Conversor de Moedas! Saindo...");
                System.exit(0);
            }

            // Realiza a conversão com base na moeda escolhida
            for (Moeda moedaItem : moedasListadas) {
                if (moedaItem.getMoedaBase().equals(moedaConver)) {
                    System.out.println("Digite o valor a ser convertido:");
                    double valor = sc.nextDouble();
                    StringBuilder sb = new StringBuilder();
                    sb.append("O valor convertido é: " + String.format("%.2f", valor * moedaItem.getValorBase())
                            + " " + moedaItem.getMoedaBase());
                    System.out.println(sb);
                }
            }
        } catch (Exception e) {
            System.out.println("Opção inválida!");
            sc.nextLine();
        }
        sc.nextLine();
    }
}
