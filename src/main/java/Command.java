/**
 * Defines the commands Alfred accepts and their user-facing keywords.
 */
public enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String keyword;

    /**
     * Creates a command with the keyword users enter in the console.
     *
     * @param keyword command word recognized by Alfred
     */
    Command(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds the command at the beginning of a user input line.
     *
     * @param input full line entered by the user
     * @return the matching command, or {@code null} when no command matches
     */
    public static Command fromInput(String input) {
        for (Command command : values()) {
            if (input.equals(command.keyword) || input.startsWith(command.keyword + " ")) {
                return command;
            }
        }
        return null;
    }

    /**
     * Returns the command word used in user input and error messages.
     *
     * @return the command keyword
     */
    public String getKeyword() {
        return keyword;
    }
}
