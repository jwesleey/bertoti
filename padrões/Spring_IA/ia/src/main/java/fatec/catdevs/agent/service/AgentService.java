package fatec.catdevs.agent.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import fatec.catdevs.agent.memory.ConversationMemory;
import fatec.catdevs.agent.observer.AgentEvent;
import fatec.catdevs.agent.observer.AgentEventPublisher;
import fatec.catdevs.agent.observer.AgentObserver;
import fatec.catdevs.agent.state.AgentContext;
import fatec.catdevs.agent.state.PlanningState;
import fatec.catdevs.agent.strategy.PlanningStrategy;

@Service
public class AgentService {

    private final PlanningState planningState;
    private final AgentEventPublisher publisher;
    private final ConversationMemory memory;
    private final Map<String, PlanningStrategy> planners = new LinkedHashMap<>();

    public AgentService(PlanningState planningState,
            List<PlanningStrategy> strategies,
            AgentEventPublisher publisher,
            ConversationMemory memory) {
        this.planningState = planningState;
        this.publisher = publisher;
        this.memory = memory;
        strategies.forEach(s -> planners.put(s.name(), s));
    }

    public String chat(String userMessage) {
        AgentContext context = new AgentContext(userMessage, planningState, publisher);
        context.setHistory(memory.comoTexto());
        try {
            context.run();
        } catch (RuntimeException e) {
            publisher.publish(new AgentEvent(AgentEvent.Type.ERROR, String.valueOf(e.getMessage())));
            throw e;
        }
        memory.registrar(userMessage, context.getResponse());
        return context.getResponse();
    }

    public List<String> planners() {
        return List.copyOf(planners.keySet());
    }

    public String currentPlanner() {
        return planningState.getStrategyName();
    }

    public void usePlanner(String name) {
        PlanningStrategy strategy = planners.get(name);
        if (strategy != null) {
            planningState.setStrategy(strategy);
        }
    }

    public void addObserver(AgentObserver observer) {
        publisher.attach(observer);
    }
}
