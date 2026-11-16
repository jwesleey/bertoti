package fatec.catdevs.agent.state;

import org.springframework.stereotype.Component;

/** ConcreteState final: encerra o ciclo do agente. */
@Component
public class FinalState implements AgentState {

    @Override
    public void handle(AgentContext context) {
        // nada a fazer: o ciclo termina aqui
    }

    @Override
    public String name() {
        return "Concluído";
    }

    @Override
    public boolean isFinal() {
        return true;
    }
}
