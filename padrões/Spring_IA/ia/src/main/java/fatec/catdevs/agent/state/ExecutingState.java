package fatec.catdevs.agent.state;
 
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;
 
@Component
public class ExecutingState implements AgentState {
 
    private static final String REGRAS_COM_BASE = """
            Você é um assistente de base de conhecimento.
            Responda à pergunta do usuário usando as informações dos TRECHOS fornecidos.
 
            REGRAS:
            - Use os trechos como fonte e cite o documento entre colchetes, por exemplo [juridico-lgpd.md].
            - Escreva uma resposta clara e direta, com suas próprias palavras.
            - Ignore trechos que não tenham relação com a pergunta.
            - Se os trechos cobrirem só parte da pergunta, responda a parte coberta e diga o que faltou.
            - Não invente documentos nem informações que não estejam nos trechos.
            - Em temas médicos, jurídicos ou financeiros, recomende consultar um profissional.
            - Responda em português.
            """;
 
    private static final String REGRAS_SEM_BASE = """
            Você é um assistente de base de conhecimento.
            A pesquisa NÃO encontrou trechos relevantes para a pergunta.
 
            REGRAS:
            - Comece avisando, em uma frase, que não encontrou isso na base de conhecimento.
            - Depois responda com seu conhecimento geral, deixando claro que essa parte não vem da base.
            - Se a lista de documentos disponíveis for informada, mencione os assuntos que a base cobre.
            - Em temas médicos, jurídicos ou financeiros, recomende consultar um profissional.
            - Responda em português.
            """;
 
    private final ChatClient chatClient;
    private final FinalState next;
 
    public ExecutingState(ChatClient.Builder builder, FinalState next) {
        this.chatClient = builder.build();
        this.next = next;
    }
 
    @Override
    public void handle(AgentContext context) {
        boolean comBase = context.isKnowledgeFound();
 
        String regras = comBase ? REGRAS_COM_BASE : REGRAS_SEM_BASE;
        String mensagem = """
                TRECHOS DA BASE DE CONHECIMENTO:
                %s
 
                HISTÓRICO RECENTE (use só para entender referências como "isso" ou "o anterior"):
                %s
 
                PERGUNTA:
                %s
 
                PLANO DE TRABALHO (não repita o plano na resposta):
                %s
                """.formatted(
                context.getKnowledge(),
                context.getHistory(),
                context.getUserMessage(),
                context.getPlan());
 
        String resposta = chatClient.prompt()
                .system(regras)
                .user(mensagem)
                .call()
                .content();
 
        context.setResponse(resposta);
        context.setState(next);
    }
 
    @Override
    public String name() {
        return "Respondendo";
    }
}