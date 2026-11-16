package fatec.catdevs.agent.state;
 
import fatec.catdevs.agent.observer.AgentEvent;
import fatec.catdevs.agent.observer.AgentEventPublisher;
 
/**
* Context do padrão State: guarda os dados da requisição e o estado atual.
*/
public class AgentContext {
 
    private final String userMessage;
    private final AgentEventPublisher publisher;
 
    private String plan = "";
    private String knowledge = "";
    private String response = "";
    private String history = "";
    private boolean knowledgeFound = false;
 
    private AgentState state;
 
    public AgentContext(String userMessage, AgentState initialState, AgentEventPublisher publisher) {
        this.userMessage = userMessage;
        this.publisher = publisher;
        setState(initialState);
    }
 
    /**
     * Delega o trabalho ao estado atual até chegar ao estado final.
     */
    public void run() {
        while (!state.isFinal()) {
            state.handle(this);
        }
    }
 
    public void setState(AgentState state) {
        this.state = state;
        publisher.publish(new AgentEvent(AgentEvent.Type.STATE_CHANGED, state.name()));
    }
 
    public AgentState getState() {
        return state;
    }
 
    public String getUserMessage() {
        return userMessage;
    }
 
    public String getPlan() {
        return plan;
    }
 
    public void setPlan(String plan) {
        this.plan = plan;
    }
 
    public String getKnowledge() {
        return knowledge;
    }
 
    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }
 
    public String getResponse() {
        return response;
    }
 
    public void setResponse(String response) {
        this.response = response;
    }
 
    public String getHistory() {
        return history;
    }
 
    public void setHistory(String history) {
        this.history = history;
    }
 
    /** true quando a pesquisa achou trechos na base de conhecimento. */
    public boolean isKnowledgeFound() {
        return knowledgeFound;
    }
 
    public void setKnowledgeFound(boolean knowledgeFound) {
        this.knowledgeFound = knowledgeFound;
    }
}