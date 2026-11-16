package fatec.catdevs.agent.strategy;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/** ConcreteStrategy: plano intercalando raciocínio e ação (estilo ReAct). */
@Component
@Primary
public class ReActPlanner implements PlanningStrategy {

    private final ChatClient chatClient;

    public ReActPlanner(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public String name() {
        return "ReAct";
    }

    @Override
    public String createPlan(String userMessage) {
        return chatClient.prompt()
                .system("""
                        Você é o PLANEJADOR de um assistente de base de conhecimento.

                        Sua única função é transformar a solicitação do usuário
                        em um plano curto, alternando RACIOCÍNIO e AÇÃO.

                        REGRAS:
                        - NÃO responda à solicitação.
                        - NÃO faça perguntas ao usuário.
                        - NÃO explique suas decisões.
                        - Retorne SOMENTE uma lista numerada, no máximo 4 itens.
                        - Cada item começa com "Pensar:" ou "Agir:".

                        Exemplo:

                        1. Pensar: identificar o assunto principal da pergunta.
                        2. Agir: buscar o assunto na base de conhecimento.
                        3. Pensar: verificar se os trechos encontrados respondem à pergunta.
                        4. Agir: redigir a resposta usando apenas os trechos encontrados.
                        """)
                .user(userMessage)
                .call()
                .content();
    }
}
