import java.io.IOException;
import java.util.List;
import java.util.Scanner;



/**
 * Classe menu, responsável por exibir o menu e decidir a opção escolhida pelo usuário
 * @throws IOException
 * @throws InterruptedException
 */
public class Menu {
    Scanner sc = new Scanner(System.in);


    public Menu() throws IOException, InterruptedException {
    }

    public void exibirMenu(boolean menuInicialAparecer) {
        /**
         * Exibe o menu inicial para o usuário
         */
        if(menuInicialAparecer){
            String menuInicial = """
                ********************************************************************
                                
                Seja bem-vindo ao Conversor de Moedas!
                       
                       Converter de:
                       
                       USD - Dólar 
                       BRL - Real 
                       EUR - Euro 
                       GBP - Libra Esterlina 
                       JPY - Iene Japonês 
                       CNY - Yuan Chinês 
                       AUD - Dólar Australiano 
                       CAD - Dólar Canadense 
                       CHF - Franco Suíço 
                       MXN - Peso Mexicano 
                       OUTRA - Escolher outra moeda
                       SAIR - Sair do programa
                                
                ********************************************************************
                """;
            System.out.println(menuInicial);}else{
            String menu = """
                ********************************************************************
                       
                       Converter para:
                       
                       USD - Dólar 
                       BRL - Real 
                       EUR - Euro 
                       GBP - Libra Esterlina 
                       JPY - Iene Japonês 
                       CNY - Yuan Chinês 
                       AUD - Dólar Australiano 
                       CAD - Dólar Canadense 
                       CHF - Franco Suíço 
                       MXN - Peso Mexicano 
                       OUTRA - Escolher outra moeda
                       SAIR - Sair do programa
                                
                ********************************************************************
                """;
            System.out.println(menu);}
    }
    public List<Moeda> decidirOpcao() throws Exception {
        /**
         * Recebe a sigla da moeda base escolhida pelo usuário
         * Verifica se a moeda escolhida é válida
         * Caso a moeda escolhida seja "OUTRA", exibe a lista de moedas disponíveis e pede para o usuário escolher uma
         * Caso a moeda escolhida seja "SAIR", encerra o programa
         * Caso a moeda escolhida seja válida, retorna a lista de moedas com valores referentes a moeda base escolhida
         */
        boolean moedaEncontrada = false;
        String moeda="BRL";
        //criando uma lista default com a moeda do Brasil para checar as moedas disponíveis
        ConsumoAPI consumoAPIBasico = new ConsumoAPI("BRL");
        List<Moeda> moedasListaBasica = consumoAPIBasico.getMoedasList();

        do {
            System.out.println("Digite abaixo a sigla da moeda base desejada: ");
            moeda = sc.nextLine().toUpperCase();

            if (moeda.equalsIgnoreCase("SAIR")) {
                System.out.println("Obrigado por utilizar o Conversor de Moedas! Saindo...");
                System.exit(0);
            }else if (moeda.equalsIgnoreCase("OUTRA")) {
                System.out.println("1 - Para digitar a sigla da moeda base.\n" +
                        "2 - Para ver a lista de moedas disponíveis.");
                int opcaoMoeda = sc.nextInt();
                if (opcaoMoeda == 1) {
                    sc.nextLine();
                    System.out.println("Digite a sigla da moeda base:");
                    moeda = sc.nextLine().toUpperCase();
                } else if (opcaoMoeda == 2) {
                    for (Moeda moedaItem : moedasListaBasica) {
                        System.out.println(moedaItem.getMoedaBase());
                    }
                    sc.nextLine();
                    System.out.println("Digite a sigla da moeda base:");
                    moeda = sc.nextLine().toUpperCase();
                } else {
                    System.out.println("Opção inválida!");
                    continue;
                }
            }

            //verificando se a moeda digitada está na lista de moedas disponíveis
            for (Moeda moedaItem : moedasListaBasica) {
                if (moedaItem.getMoedaBase().equals(moeda)) {
                    moedaEncontrada = true;
                    break;
                }
            }
            if (!moedaEncontrada) {
                System.out.println("Moeda não encontrada!");
            }

        }while(!moedaEncontrada);

        ConsumoAPI consumoAPI = new ConsumoAPI(moeda);
        List<Moeda> moedasList = consumoAPI.getMoedasList();
        return moedasList;


    }
}

