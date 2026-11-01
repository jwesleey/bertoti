package fatec.catdevs.agent.state;

public class AgentContext {

    private String userMessage;
    private String plan;
    private String response;

    private AgentStateType state;

    public AgentContext(String userMessage, AgentStateType initialState){
        this.userMessage = userMessage;
        this.state = initialState;
    }

    public AgentContext(){
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public AgentStateType getState() {
        return state;
    }

    public void setState(AgentStateType state) {
        this.state = state;
    }
}