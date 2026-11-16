package fatec.catdevs.agent.observer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;

/** Subject (Publisher) do padrão Observer: mantém os observers e os notifica. */
@Component
public class AgentEventPublisher {

    private final List<AgentObserver> observers = new CopyOnWriteArrayList<>();

    public void attach(AgentObserver observer) {
        observers.add(observer);
    }

    public void detach(AgentObserver observer) {
        observers.remove(observer);
    }

    public void publish(AgentEvent event) {
        for (AgentObserver observer : observers) {
            try {
                observer.onEvent(event);
            } catch (RuntimeException e) {
                System.err.println("Observer falhou: " + e.getMessage());
            }
        }
    }
}
