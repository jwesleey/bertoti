package fatec.catdevs.agent.strategy;

import org.springframework.stereotype.Component;

import fatec.catdevs.agent.state.AgentContext;
import fatec.catdevs.agent.state.AgentState;

@Component
public class FinalState implements AgentState {

    @Override
    public void handle(AgentContext context) {
        System.out.println(">>> ENTROU NO FINAL");
    }

    @Override
    public boolean isFinal() {
        return true;
    }
}
