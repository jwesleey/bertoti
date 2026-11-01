package fatec.catdevs.agent.state;

public interface AgentState {
    void handle(AgentContext context);

    default boolean isFinal(){
        return false;
    }
}
