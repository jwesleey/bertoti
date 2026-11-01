package fatec.catdevs.agent.strategy;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import fatec.catdevs.agent.state.AgentContext;
import fatec.catdevs.agent.state.AgentState;
import fatec.catdevs.agent.state.AgentStateType;

@Component
public class ExecutingState implements AgentState {

    private final ChatClient chatClient;

    public ExecutingState(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public void handle(AgentContext context) {
        System.out.println(">>> ENTROU NO EXECUTING");

        String response = chatClient.prompt()
                .system("""
                        Você está na etapa de execução de um agente.

                        Resolva a solicitação do usuário utilizando
                        o plano e as informações obtidas na pesquisa.
                        """)
                .user("""
                        Solicitação:
                        %s

                        Plano:
                        %s

                        Pesquisa:
                        %s
                        """.formatted(
                        context.getUserMessage(),
                        context.getPlan(),
                        context.getResponse()))
                .call()
                .content();

        context.setResponse(response);
        context.setState(AgentStateType.FINAL);
    }
}
