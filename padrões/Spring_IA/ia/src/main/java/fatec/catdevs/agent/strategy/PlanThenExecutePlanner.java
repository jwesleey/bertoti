package fatec.catdevs.agent.strategy;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

/** ConcreteStrategy: plano completo definido antes da execução (Plan-then-Execute). */
@Component
public class PlanThenExecutePlanner implements PlanningStrategy {

    private final ChatClient chatClient;

    public PlanThenExecutePlanner(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public String name() {
        return "Plan-then-Execute";
    }

    @Override
    public String createPlan(String userMessage) {
        return chatClient.prompt()
                .system("""
                        Você é o PLANEJADOR de um assistente de base de conhecimento.

                        Crie o plano COMPLETO antes de qualquer execução,
                        dividido em exatamente três fases: BUSCAR, ANALISAR e RESPONDER.

                        REGRAS:
                        - NÃO responda à solicitação.
                        - NÃO faça perguntas ao usuário.
                        - NÃO explique suas decisões.
                        - Retorne SOMENTE as três linhas, uma por fase.

                        Exemplo:

                        BUSCAR: localizar na base os documentos sobre o assunto.
                        ANALISAR: selecionar os trechos que respondem à pergunta.
                        RESPONDER: escrever a resposta citando os documentos usados.
                        """)
                .user(userMessage)
                .call()
                .content();
    }
}
