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
        boolean moedaEncontrada = false;
        String moedaConver;
        Menu menu = new Menu();
        menu.exibirMenu(false);
        do {

            System.out.println("Digite abaixo a sigla da moeda base desejada: ");
            moedaConver = sc.nextLine().toUpperCase();

            if (moedaConver.equalsIgnoreCase("SAIR")) {
                System.out.println("Obrigado por utilizar o Conversor de Moedas! Saindo...");
                System.exit(0);
            }else if (moedaConver.equalsIgnoreCase("OUTRA")) {
                System.out.println("1 - Para digitar a sigla da moeda base.\n" +
                        "2 - Para ver a lista de moedas disponíveis.");
                int opcaoMoeda = sc.nextInt();
                if (opcaoMoeda == 1) {
                    sc.nextLine();
                    System.out.println("Digite a sigla da moeda base:");
                    moedaConver = sc.nextLine().toUpperCase();
                } else if (opcaoMoeda == 2) {
                    for (Moeda moedaItem : moedasListadas) {
                        System.out.println(moedaItem.getMoedaBase());
                    }
                    sc.nextLine();
                    System.out.println("Digite a sigla da moeda base:");
                    moedaConver = sc.nextLine().toUpperCase();
                } else {
                    System.out.println("Opção inválida!");
                    continue;
                }
            }

            //verificando se a moeda digitada está na lista de moedas disponíveis
            for (Moeda moedaItem : moedasListadas) {
                if (moedaItem.getMoedaBase().equals(moedaConver)) {
                    moedaEncontrada = true;
                    break;
                }
            }
            if (!moedaEncontrada) {
                System.out.println("Moeda não encontrada!");
            }

        }while(!moedaEncontrada);

        String valor = null;
        // Realiza a conversão com base na moeda escolhida
        for (Moeda moedaItem : moedasListadas) {

            if (moedaItem.getMoedaBase().equals(moedaConver)) {
                System.out.println("Digite o valor a ser convertido:");
                valor = sc.nextLine();
                while (!isANumber(valor)) {
                    System.out.println("Por favor, digite um valor numérico válido.");
                    valor = sc.nextLine();
                }
                valor = String.valueOf(Double.parseDouble(valor));
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("Hoje, $ %.2f",Double.parseDouble(valor)) + " "+moedasListadas.get(0).getMoedaBase()+" equivalem a $"
                        +String.format("%.2f", Double.parseDouble(valor) * moedaItem.getValorBase())+" "+moedaItem.getMoedaBase());
                System.out.println(sb);

            }
        }
    }



    public boolean isANumber(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
