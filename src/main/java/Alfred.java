/**
 * Coordinates Alfred's user interface, task storage, and command execution.
 */
public class Alfred {
    private final Storage storage;
    private final Parser parser;
    private final Ui ui;
    private TaskList tasks;

    /** Creates Alfred's collaborators. */
    public Alfred() {
        storage = new Storage();
        parser = new Parser();
        ui = new Ui();
    }

    /**
     * Greets the user, loads saved tasks, and executes commands until the user exits.
     */
    public void run() {
        ui.showWelcome();
        tasks = loadTasks();

        boolean isExit = false;
        while (!isExit) {
            String commandInput = ui.readCommand();
            ui.showSeparator();

            try {
                Command command = parser.parseCommand(commandInput, tasks.size());
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (AlfredException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showSeparator();
            }
        }
    }

    /**
     * Loads the saved tasks, returning an empty task list when loading fails.
     *
     * @return loaded tasks, or an empty list after a loading error
     */
    private TaskList loadTasks() {
        try {
            return new TaskList(storage.load());
        } catch (AlfredException e) {
            ui.showError(e.getMessage());
            return new TaskList();
        }
    }

    /**
     * Starts an Alfred session.
     *
     * @param args command-line arguments, which are not used
     */
    public static void main(String[] args) {
        new Alfred().run();
    }

}
