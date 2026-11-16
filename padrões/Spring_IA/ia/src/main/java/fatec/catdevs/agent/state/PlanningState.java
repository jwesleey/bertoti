package fatec.catdevs.agent.state;

import org.springframework.stereotype.Component;

import fatec.catdevs.agent.strategy.PlanningStrategy;

/** ConcreteState: cria o plano usando a Strategy escolhida e passa para a pesquisa. */
@Component
public class PlanningState implements AgentState {

    private volatile PlanningStrategy strategy;
    private final ResearchState next;

    public PlanningState(PlanningStrategy strategy, ResearchState next) {
        this.strategy = strategy;
        this.next = next;
    }

    public void setStrategy(PlanningStrategy strategy) {
        this.strategy = strategy;
    }

    public String getStrategyName() {
        return strategy.name();
    }

    @Override
    public void handle(AgentContext context) {
        context.setPlan(strategy.createPlan(context.getUserMessage()));
        context.setState(next);
    }

    @Override
    public String name() {
        return "Planejando";
    }
}
