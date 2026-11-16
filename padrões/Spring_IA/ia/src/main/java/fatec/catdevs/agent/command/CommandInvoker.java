package fatec.catdevs.agent.command;

import org.springframework.stereotype.Component;

import fatec.catdevs.agent.observer.AgentEvent;
import fatec.catdevs.agent.observer.AgentEventPublisher;

/**
 * Invoker do padrão Command: executa qualquer Command sem saber qual é,
 * e avisa os observers do início e do resultado.
 */
@Component
public class CommandInvoker {

    private final AgentEventPublisher publisher;

    public CommandInvoker(AgentEventPublisher publisher) {
        this.publisher = publisher;
    }

    public CommandResult run(Command command) {
        publisher.publish(new AgentEvent(AgentEvent.Type.COMMAND_STARTED, command.name()));

        CommandResult result;
        try {
            result = command.execute();
        } catch (RuntimeException e) {
            result = CommandResult.fail(String.valueOf(e.getMessage()));
        }

        publisher.publish(new AgentEvent(
                result.success() ? AgentEvent.Type.COMMAND_FINISHED : AgentEvent.Type.COMMAND_FAILED,
                command.name()));
        return result;
    }
}
