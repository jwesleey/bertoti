package fatec.catdevs.agent.service;

import org.springframework.stereotype.Service;

import fatec.catdevs.agent.state.AgentContext;
import fatec.catdevs.agent.state.AgentState;
import fatec.catdevs.agent.state.AgentStateType;
import fatec.catdevs.agent.strategy.ExecutingState;
import fatec.catdevs.agent.strategy.FinalState;
import fatec.catdevs.agent.strategy.PlanningState;
import fatec.catdevs.agent.strategy.ResearchState;

@Service
public class AgentService {

    private final PlanningState planningState;
    private final ExecutingState executingState;
    private final ResearchState researchState;
    private final FinalState finalState;

    public AgentService(PlanningState planningState, ExecutingState executingState, ResearchState researchState, FinalState finalState) {
        this.planningState = planningState;
        this.executingState = executingState;
        this.researchState = researchState;
        this.finalState = finalState;
    }

    public String chat(String userMessage) {

        AgentContext context = new AgentContext(userMessage, AgentStateType.PLANNING);
        AgentState current;

        do {

            current = switch (context.getState()) {
                case PLANNING -> planningState;
                case EXECUTING -> executingState;
                case RESEARCH -> researchState;
                case FINAL -> finalState;
            };

            current.handle(context);


        } while (!current.isFinal());

        return context.getResponse();
    }
}