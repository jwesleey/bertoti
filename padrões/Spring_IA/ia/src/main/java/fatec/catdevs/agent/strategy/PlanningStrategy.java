package fatec.catdevs.agent.strategy;

/** Interface Strategy: cada implementação é uma forma diferente de planejar. */
public interface PlanningStrategy {

    String name();

    String createPlan(String userMessage);
}
