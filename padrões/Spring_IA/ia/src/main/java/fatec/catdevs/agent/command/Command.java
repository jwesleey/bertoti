package fatec.catdevs.agent.command;

/** Interface Command: encapsula uma ferramenta (tool) do agente como objeto. */
public interface Command {

    /** Nome legível, usado nos eventos do Observer. */
    String name();

    CommandResult execute();
}
