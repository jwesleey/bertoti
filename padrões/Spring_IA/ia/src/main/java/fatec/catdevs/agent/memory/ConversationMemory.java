package fatec.catdevs.agent.memory;
 
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.stereotype.Component;
 
/**
* Memória curta da conversa.
*/
@Component
public class ConversationMemory {
 
    private static final int MAX_TROCAS = 4;
    private static final int MAX_CARACTERES_RESPOSTA = 200;
 
    private final List<String> trocas = new ArrayList<>();
 
    public synchronized void registrar(String pergunta, String resposta) {
        trocas.add("Usuário: " + pergunta + "\nAgente: " + resumir(resposta));
        while (trocas.size() > MAX_TROCAS) {
            trocas.remove(0);
        }
    }
 
    public synchronized String comoTexto() {
        return trocas.isEmpty() ? "(sem histórico)" : String.join("\n\n", trocas);
    }
 
    public synchronized void limpar() {
        trocas.clear();
    }
 
    /** Guarda só o começo da resposta; respostas "não encontrei" viram uma marca curta. */
    private String resumir(String resposta) {
        if (resposta == null || resposta.isBlank()) {
            return "(sem resposta)";
        }
        String limpa = resposta.strip();
        if (limpa.toLowerCase().startsWith("não encontrei")) {
            return "(não havia informação sobre isso na base)";
        }
        return limpa.length() <= MAX_CARACTERES_RESPOSTA
                ? limpa
                : limpa.substring(0, MAX_CARACTERES_RESPOSTA) + "...";
    }
}