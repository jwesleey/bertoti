package fatec.catdevs.agent.command;
 
import java.io.IOException;
import java.util.List;
 
import fatec.catdevs.agent.knowledge.KnowledgeBase;
 
/** ConcreteCommand: lista os documentos disponíveis na base de conhecimento. */
public class ListDocumentsCommand implements Command {
 
    private final KnowledgeBase base;
 
    public ListDocumentsCommand(KnowledgeBase base) {
        this.base = base;
    }
 
    @Override
    public String name() {
        return "Listar documentos";
    }
 
    @Override
    public CommandResult execute() {
        try {
            List<String> documentos = base.listarDocumentos();
            if (documentos.isEmpty()) {
                return CommandResult.fail("Nenhum documento em: " + base.getPasta());
            }
            return CommandResult.ok("Documentos disponíveis: " + String.join(", ", documentos));
        } catch (IOException e) {
            return CommandResult.fail("Erro ao listar documentos: " + e.getMessage());
        }
    }
}