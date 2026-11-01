package fatec.catdevs.agent.strategy;

import org.springframework.stereotype.Component;

import fatec.catdevs.agent.state.AgentContext;
import fatec.catdevs.agent.state.AgentState;
import fatec.catdevs.agent.state.AgentStateType;

@Component
public class PlanningState implements AgentState {

        private final PlanningStrategy planningStrategy;

        public PlanningState(PlanningStrategy planningStrategy){
            this.planningStrategy = planningStrategy;
        }

    @Override
    public void handle(AgentContext context) {
        System.out.println(">>> ENTROU NO PLANNING");
        context.setPlan(planningStrategy.createPlan(context.getUserMessage()));
        context.setState(AgentStateType.RESEARCH);
    }
}