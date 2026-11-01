package fatec.catdevs.agent.strategy;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import fatec.catdevs.agent.state.AgentContext;
import fatec.catdevs.agent.state.AgentState;
import fatec.catdevs.agent.state.AgentStateType;

@Component
public class ResearchState implements AgentState {

    private final ChatClient chatClient;

    public ResearchState(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public void handle(AgentContext context) {
        System.out.println(">>> ENTROU NO REASERCH");
        String research = chatClient.prompt()
                .system("""
                        Você está no estado RESEARCH de um agente.

                        Sua função é fornecer informações que serão usadas
                        posteriormente pelo estado EXECUTING.

                        NÃO responda diretamente ao usuário.
                        NÃO faça perguntas.
                        NÃO dê sugestões sobre como melhorar a resposta.

                        Analise o plano e produza somente as informações
                        necessárias para executar cada etapa.
                        """)
                .user("""
                        Solicitação:
                        %s

                        Plano:
                        %s
                        """.formatted(context.getUserMessage(), context.getPlan()))
                .call()
                .content();

        context.setResponse(research);
        context.setState(AgentStateType.EXECUTING);
    }
}