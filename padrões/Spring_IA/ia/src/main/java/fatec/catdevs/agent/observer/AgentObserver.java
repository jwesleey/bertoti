package fatec.catdevs.agent.observer;

/** Interface Observer:  acompanhar a execução do agente. */
public interface AgentObserver {
    void onEvent(AgentEvent event);
}
