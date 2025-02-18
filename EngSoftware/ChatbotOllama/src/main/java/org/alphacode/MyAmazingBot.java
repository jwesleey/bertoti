package org.alphacode;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.ollama4j.exceptions.OllamaBaseException;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyAmazingBot implements LongPollingSingleThreadUpdateConsumer {
    private final TelegramClient telegramClient;
    private final Ollama ollama = new Ollama();
    private static final Logger logger = Logger.getLogger(MyAmazingBot.class.getName());
    private static final String apiKEY = "87f3240ec003f3cca29b4fad23365b91";
    private static final String apiURL = "https://api.openweathermap.org/data/2.5/weather";

    public MyAmazingBot(String token) {
        telegramClient = new OkHttpTelegramClient(token);
    }

    public MyAmazingBot(TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String response = "";
            String weatherInfo = "";

            if (text.startsWith("/clima")) {
                String city = text.replace("/clima", "").trim();
                if (city.isEmpty()) {
                    response = "Informe uma localização. Exemplo: clima São Paulo";
                } else {
                    try {
                        // Obtém o clima da cidade
                        weatherInfo = getWeather(city);

                        // Envia as informações do clima para o Ollama para gerar uma resposta
                        response = ollama.getOllamaResponse(weatherInfo);
                    } catch (IOException | InterruptedException e) {
                        logger.log(Level.SEVERE, "Erro ao obter clima para a cidade: " + city, e);
                        response = "Erro ao obter o clima. Por favor, tente novamente mais tarde.";
                    } catch (OllamaBaseException e) {
                        logger.log(Level.SEVERE, "Erro ao obter resposta do Ollama: ", e);
                        response = "Erro ao tentar gerar uma resposta do modelo Ollama.";
                    }
                }
            } else {
                try {
                    response = ollama.getOllamaResponse(text);  // Gera uma resposta com o Ollama
                } catch (OllamaBaseException | IOException | InterruptedException e) {
                    logger.log(Level.SEVERE, "Erro ao obter resposta do Ollama: ", e);
                    response = "Erro ao tentar gerar uma resposta.";
                }
            }

            // Concatenação do tempo + resposta do Ollama
            String finalMessage = weatherInfo + "\n\n" + response;
            sendMessage(chatId, finalMessage);
        }
    }


    public String getWeather(String city) throws IOException, InterruptedException {
        try {
            String local = URLEncoder.encode(city, StandardCharsets.UTF_8.toString());
            String url1 = String.format("%s?q=%s&appid=%s&units=metric&lang=pt_br", apiURL, local, apiKEY);
            String url = String.format("%s?q=%s&appid=%s&units=metric&lang=pt_br", apiURL, local, apiKEY);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url1))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return weatherResponse(response.body());
            } else {
                return "Não foi possível obter o clima. Verifique se a cidade está correta." + response.statusCode();
            }
        } catch (IOException | InterruptedException e) {
            logger.log(Level.SEVERE, "Erro ao obter dados da API de clima: ", e);
            return "Erro ao obter dados do clima. Tente novamente mais tarde.";
        }
    }

    private String weatherResponse(String responseBody) {
        JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
        String cityName = json.get("name").getAsString();
        JsonObject main = json.getAsJsonObject("main");
        double temperature = main.get("temp").getAsDouble();
        double feelsLike = main.get("feels_like").getAsDouble();
        int humidity = main.get("humidity").getAsInt();


        String weatherDescription = json.getAsJsonArray("weather")
                .get(0)
                .getAsJsonObject()
                .get("description")
                .getAsString();

        return String.format("Clima em %s:\n" +
                        "- Temperatura: %.1f°C\n" +
                        "- Sensação Térmica: %.1f°C\n" +
                        "- Umidade: %d%%\n" +
                        "- Condições: %s",
                cityName, temperature, feelsLike, humidity, weatherDescription);

    }


    private void sendMessage(long chatId, String response) {
        SendMessage message = SendMessage.builder()
                .chatId(chatId)
                .text(response)
                .build();
        try {
            telegramClient.execute(message);
        } catch (TelegramApiException e) {
            logger.log(Level.SEVERE, "Erro ao enviar mensagem para o Telegram: ", e);
        }
    }
}
