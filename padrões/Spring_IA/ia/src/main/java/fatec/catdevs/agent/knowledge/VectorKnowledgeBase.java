package fatec.catdevs.agent.knowledge;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/** Indexa os arquivos locais no Chroma e recupera trechos por similaridade semântica. */
@Component
public class VectorKnowledgeBase implements ApplicationRunner {

    private static final int TAMANHO_TRECHO = 1200;
    private static final int TOP_K = 6;

    private final KnowledgeBase knowledgeBase;
    private final VectorStore vectorStore;

    public VectorKnowledgeBase(KnowledgeBase knowledgeBase, VectorStore vectorStore) {
        this.knowledgeBase = knowledgeBase;
        this.vectorStore = vectorStore;
    }

    @Override
    public void run(ApplicationArguments args) throws IOException {
        List<Document> documentos = new ArrayList<>();
        try (Stream<java.nio.file.Path> arquivos = Files.list(knowledgeBase.getPasta())) {
            for (var arquivo : arquivos.filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".md") || p.toString().endsWith(".txt"))
                    .sorted().toList()) {
                String nome = arquivo.getFileName().toString();
                List<String> trechos = dividir(Files.readString(arquivo, StandardCharsets.UTF_8));
                for (int i = 0; i < trechos.size(); i++) {
                    Map<String, Object> metadata = new HashMap<>();
                    metadata.put("source", nome);
                    metadata.put("chunk", i);
                    String id = UUID.nameUUIDFromBytes((nome + ":" + i).getBytes(StandardCharsets.UTF_8)).toString();
                    documentos.add(new Document(id, trechos.get(i), metadata));
                }
            }
        }
        if (!documentos.isEmpty()) {
            vectorStore.add(documentos);
            System.out.println("[CHROMA] " + documentos.size() + " trechos indexados da pasta "
                    + knowledgeBase.getPasta());
        }
    }

    public List<String> buscar(String consulta) {
        return vectorStore.similaritySearch(SearchRequest.builder()
                        .query(consulta)
                        .topK(TOP_K)
                        .build())
                .stream()
                .map(doc -> "[" + doc.getMetadata().getOrDefault("source", "documento") + "] " + doc.getText())
                .toList();
    }

    private List<String> dividir(String texto) {
        List<String> trechos = new ArrayList<>();
        StringBuilder atual = new StringBuilder();
        for (String paragrafo : texto.split("\\R\\s*\\R")) {
            String parte = paragrafo.trim();
            if (parte.isEmpty()) continue;
            if (parte.length() > TAMANHO_TRECHO) {
                if (!atual.isEmpty()) {
                    trechos.add(atual.toString());
                    atual.setLength(0);
                }
                for (int inicio = 0; inicio < parte.length(); inicio += TAMANHO_TRECHO) {
                    trechos.add(parte.substring(inicio, Math.min(inicio + TAMANHO_TRECHO, parte.length())));
                }
            } else {
                if (!atual.isEmpty() && atual.length() + parte.length() + 2 > TAMANHO_TRECHO) {
                    trechos.add(atual.toString());
                    atual.setLength(0);
                }
                if (!atual.isEmpty()) atual.append("\n\n");
                atual.append(parte);
            }
        }
        if (!atual.isEmpty()) trechos.add(atual.toString());
        return trechos;
    }
}
