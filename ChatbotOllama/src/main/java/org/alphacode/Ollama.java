package org.alphacode;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import io.github.ollama4j.models.response.OllamaResult;
import io.github.ollama4j.utils.OptionsBuilder;

import java.io.IOException;

public class Ollama {
    String host = "http://localhost:11434/";

    public String getOllamaResponse(String text) throws OllamaBaseException, IOException, InterruptedException {

        OllamaAPI api = new OllamaAPI(host);

        OptionsBuilder options = new OptionsBuilder();
        options.setTemperature(0.8F);  // Menos aleatório, mais criativo
        options.setTopP(0.7F);         // Mais coerentes
        api.setRequestTimeoutSeconds(300);
        OllamaResult result = api.generate("gemma2:2b", text, true, options.build());
        return result.getResponse();
    }

}




