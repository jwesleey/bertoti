package fatec.catdevs.agent.observer;

/** Evento publicado pelo agente durante a execução (mensagem do padrão Observer). */
public record AgentEvent(Type type, String message) {

    public enum Type {
        STATE_CHANGED,
        COMMAND_STARTED,
        COMMAND_FINISHED,
        COMMAND_FAILED,
        ERROR
    }

    public String descricao() {
        return switch (type) {
            case STATE_CHANGED -> "Estado: " + message;
            case COMMAND_STARTED -> "Executando: " + message;
            case COMMAND_FINISHED -> "Concluído: " + message;
            case COMMAND_FAILED -> "Sem resultado: " + message;
            case ERROR -> "Erro: " + message;
        };
    }
}
