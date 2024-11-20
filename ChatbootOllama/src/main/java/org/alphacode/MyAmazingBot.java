package org.alphacode;

import io.github.ollama4j.exceptions.OllamaBaseException;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyAmazingBot implements LongPollingSingleThreadUpdateConsumer {
    private final TelegramClient telegramClient;
    private final Ollama ollama = new Ollama();
    private static final Logger logger = Logger.getLogger(MyAmazingBot.class.getName());

    public MyAmazingBot(String token) {
        telegramClient = new OkHttpTelegramClient(token);
    }

    public MyAmazingBot(TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
    }

    @Override
    public void consume(Update update) {
        // Verifica se a mensagem contém texto
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String response = null;

            try {
                // Chama o método para obter a resposta do modelo Ollama
                response = ollama.getOllamaResponse(text);
            } catch (OllamaBaseException | IOException | InterruptedException e) {
                // Log de erro e retorno de mensagem de erro
                logger.log(Level.SEVERE, "Erro ao obter resposta do Ollama: ", e);
                response = "Erro ao tentar gerar uma resposta.";
            }

            // Criação da mensagem a ser enviada ao usuário
            SendMessage message = SendMessage
                    .builder()
                    .chatId(chatId)
                    .text(response)
                    .build();

            try {
                // Envia a mensagem gerada para o Telegram
                telegramClient.execute(message);
            } catch (TelegramApiException e) {
                // Log de erro ao enviar mensagem para o Telegram
                logger.log(Level.SEVERE, "Erro ao enviar mensagem para o Telegram: ", e);
            }
        }
    }
}
