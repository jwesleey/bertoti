package fatec.catdevs.agent.observer;

import org.springframework.stereotype.Component;

/** ConcreteObserver: registra no console tudo o que o agente faz. */
@Component
public class ConsoleLogObserver implements AgentObserver {

    public ConsoleLogObserver(AgentEventPublisher publisher) {
        publisher.attach(this);
    }

    @Override
    public void onEvent(AgentEvent event) {
        System.out.println("[AGENTE] " + event.descricao());
    }
}
