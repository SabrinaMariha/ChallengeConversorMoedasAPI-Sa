import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * Classe responsável por consumir a API de conversão de moedas
 * @throws IOException
 * @throws InterruptedException
 */
public class ConsumoAPI {
/**
 * Faz a requisição à API e retorna uma lista de moedas em formato JSON
 * Cria um objeto Moeda para cada moeda da lista e adiciona à lista de moedas
 * retorna a lista de moedas
 */
    String moedaBase;
    String moedaEscolhida = "USD";

    Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .setPrettyPrinting()
            .create();


    public ConsumoAPI(String moedaBase) throws IOException, InterruptedException {
        this.moedaBase = moedaBase;

    }
    public List<Moeda> getMoedasList() throws IOException, InterruptedException {
        //------------------------------------ REQUISIÇÃO------------------------------------------------
        try {
            String URL = "https://v6.exchangerate-api.com/v6/f54b92d47d0c4a96444f05eb/latest/" + moedaBase;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            // Converter o JSON para um objeto JsonObject
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

            // Obter o objeto de taxas de conversão
            JsonObject conversionRatesObject = jsonObject.getAsJsonObject("conversion_rates");

            // Obter as entradas (pares moeda-valor) do objeto de taxas de conversão
            Set<Map.Entry<String, JsonElement>> entries = conversionRatesObject.entrySet();

            // Criar uma lista para armazenar os objetos Moeda
            List<Moeda> moedasList = new ArrayList<>();
            //------------------------------------FIM------------------------------------------------


            // Iterar sobre as entradas do objeto de taxas de conversão
            for (Map.Entry<String, JsonElement> entry : entries) {
                // Obter o nome da moeda (chave) e o valor de conversão
                String moeda = entry.getKey();
                double valor = entry.getValue().getAsDouble();

                // Criar um objeto Moeda e adicioná-lo à lista
                moedasList.add(new Moeda(moeda, valor));
            }
            return moedasList;
        } catch (IOException e) {
            System.out.println("Erro ao acessar a API: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("A requisição foi interrompida: " + e.getMessage());
        }
        return null;

    }
}
