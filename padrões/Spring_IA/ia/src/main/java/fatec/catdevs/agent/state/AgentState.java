package fatec.catdevs.agent.state;

/** Interface State: cada estado sabe o que fazer e qual é o próximo estado. */
public interface AgentState {

    void handle(AgentContext context);

    String name();

    default boolean isFinal() {
        return false;
    }
}
