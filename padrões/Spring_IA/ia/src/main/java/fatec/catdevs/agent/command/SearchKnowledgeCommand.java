package fatec.catdevs.agent.command;

import java.util.List;

import fatec.catdevs.agent.knowledge.VectorKnowledgeBase;

/** ConcreteCommand: busca um termo nos documentos da base de conhecimento. */
public class SearchKnowledgeCommand implements Command {

    private final VectorKnowledgeBase base;
    private final String termo;

    public SearchKnowledgeCommand(VectorKnowledgeBase base, String termo) {
        this.base = base;
        this.termo = termo;
    }

    @Override
    public String name() {
        return "Buscar \"" + termo + "\"";
    }

    @Override
    public CommandResult execute() {
        List<String> trechos = base.buscar(termo);
        if (trechos.isEmpty()) {
            return CommandResult.fail("Nenhum trecho encontrado para \"" + termo + "\".");
        }
        return CommandResult.ok(String.join("\n", trechos));
    }
}
