package fatec.catdevs.agent.knowledge;

import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KnowledgeBase {

    private static final int MAX_TRECHOS_POR_DOCUMENTO = 3;
    private static final int MAX_CARACTERES_POR_TRECHO = 300;

    private final Path pasta;

    public KnowledgeBase(@Value("${knowledge.path:./knowledge}") String caminho) {
        this.pasta = localizarPasta(caminho);
        try {
            System.out.println("[KNOWLEDGE] Pasta: " + pasta
                    + " (" + listarDocumentos().size() + " documentos)");
        } catch (IOException e) {
            System.out.println("[KNOWLEDGE] Pasta: " + pasta + " (erro ao listar: " + e.getMessage() + ")");
        }
    }

    public Path getPasta() {
        return pasta;
    }

    private static Path localizarPasta(String configurado) {
        Path informado = Path.of(configurado).toAbsolutePath().normalize();
        if (Files.isDirectory(informado)) {
            return informado;
        }

        Path atual = Path.of(System.getProperty("user.dir")).toAbsolutePath().normalize();

        // 1) "knowledge" no diretório atual e nos pais (até 4 níveis)
        Path sobe = atual;
        for (int i = 0; i < 5 && sobe != null; i++, sobe = sobe.getParent()) {
            Path candidata = sobe.resolve("knowledge");
            if (Files.isDirectory(candidata)) {
                return candidata;
            }
        }

        // 2) "knowledge" dentro de subpastas diretas (ex.: o projeto está em atual/ia/)
        try (Stream<Path> filhos = Files.list(atual)) {
            for (Path filho : filhos.filter(Files::isDirectory).toList()) {
                Path candidata = filho.resolve("knowledge");
                if (Files.isDirectory(candidata)) {
                    return candidata;
                }
            }
        } catch (IOException e) {
            // ignora e devolve o caminho informado
        }

        System.out.println("[KNOWLEDGE] ATENÇÃO: pasta não encontrada em " + informado
                + " (diretório de trabalho: " + atual + ")");
        return informado;
    }

    public List<String> listarDocumentos() throws IOException {
        if (!Files.isDirectory(pasta)) {
            return List.of();
        }
        try (Stream<Path> arquivos = Files.list(pasta)) {
            return arquivos
                    .filter(this::ehDocumento)
                    .map(p -> p.getFileName().toString())
                    .sorted()
                    .toList();
        }
    }

    public String catalogo() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (String documento : listarDocumentos()) {
            String titulo = lerLinhas(pasta.resolve(documento)).stream()
                    .map(String::trim)
                    .filter(linha -> !linha.isEmpty())
                    .findFirst()
                    .orElse(documento)
                    .replaceFirst("^#+\\s*", "");
            sb.append("[").append(documento).append("] ").append(titulo).append("\n");
        }
        return sb.toString().trim();
    }

    public List<String> buscar(String termo) throws IOException {
        // casa quando a palavra COMEÇA com o termo: "juro" casa "juros"
        Pattern alvo = Pattern.compile("(?<![\\p{L}\\p{N}])" + Pattern.quote(normalizar(termo)));
        List<String> trechos = new ArrayList<>();

        for (String documento : listarDocumentos()) {
            int encontrados = 0;
            for (String linha : lerLinhas(pasta.resolve(documento))) {
                if (alvo.matcher(normalizar(linha)).find()) {
                    trechos.add("[" + documento + "] " + cortar(linha.trim()));
                    if (++encontrados >= MAX_TRECHOS_POR_DOCUMENTO) {
                        break;
                    }
                }
            }
        }
        return trechos;
    }

    /** Remove acentos e põe em minúsculas, para "Padrão" casar com "padrao". */
    public static String normalizar(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase(Locale.ROOT);
    }

    private boolean ehDocumento(Path arquivo) {
        String nome = arquivo.getFileName().toString().toLowerCase(Locale.ROOT);
        return Files.isRegularFile(arquivo) && (nome.endsWith(".md") || nome.endsWith(".txt"));
    }

    private List<String> lerLinhas(Path arquivo) throws IOException {
        try {
            return Files.readAllLines(arquivo, StandardCharsets.UTF_8);
        } catch (MalformedInputException e) {
            return Files.readAllLines(arquivo, StandardCharsets.ISO_8859_1);
        }
    }

    private String cortar(String linha) {
        return linha.length() <= MAX_CARACTERES_POR_TRECHO
                ? linha
                : linha.substring(0, MAX_CARACTERES_POR_TRECHO) + "...";
    }
}