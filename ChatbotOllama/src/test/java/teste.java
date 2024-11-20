import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class teste {
    public static void main(String[] args) throws IOException, InterruptedException {

        String apiKey = "87f3240ec003f3cca29b4fad23365b91"; // Chave de API do OpenWeatherMap
        String cityName = "São paulo"; // Cidade
        String encodedCityName = URLEncoder.encode(cityName, StandardCharsets.UTF_8.toString());
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric&lang=pt_br", encodedCityName, apiKey);

        // Cria cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Cria requisição
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        // Enviar a requisição e receber a resposta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Exibir o corpo da resposta (JSON)
        System.out.println(response.body());
    }
}
