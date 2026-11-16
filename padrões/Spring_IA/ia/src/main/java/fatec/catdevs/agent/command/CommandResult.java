package fatec.catdevs.agent.command;

public record CommandResult(boolean success, String output) {

    public static CommandResult ok(String output) {
        return new CommandResult(true, output);
    }

    public static CommandResult fail(String reason) {
        return new CommandResult(false, reason);
    }
}
