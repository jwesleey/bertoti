package fatec.catdevs.agent.strategy;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
public class ReActPlanner implements PlanningStrategy {

    private final ChatClient chatClient;

    public ReActPlanner(ChatClient.Builder builder) {
         this.chatClient = builder.build();
    }

    @Override
    public String createPlan(String userMessage) {
        return chatClient.prompt()
                .system("""
                         Você é o PLANEJADOR de um agente.

                        Sua única função é transformar a solicitação do usuário
                        em um plano de execução.

                        REGRAS:
                        - NÃO responda à solicitação.
                        - NÃO faça perguntas ao usuário.
                        - NÃO dê opiniões.
                        - NÃO execute nenhuma etapa.
                        - NÃO explique suas decisões.
                        - NÃO fale sobre "cliente".
                        - Retorne SOMENTE uma lista numerada de etapas.

                        Exemplo:

                        1. Identificar o objetivo da solicitação.
                        2. Levantar as informações necessárias.
                        3. Analisar as informações.
                        4. Produzir a resposta final.

                        Solicitação:
                        """)
                .user(userMessage)
                .call()
                .content();
    }
}