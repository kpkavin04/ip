/**
 * Starts Alfred, greets the user, stores tasks, and responds to commands until the user exits.
 */
public class Alfred {
    /**
     * Displays Alfred's greeting, stores entered tasks, updates and deletes them on request,
     * and exits on {@code bye}.
     *
     * @param args command-line arguments, which are not used
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcome();
        Storage storage = new Storage();
        Parser parser = new Parser();
        TaskList tasks;
        try {
            tasks = new TaskList(storage.load());
        } catch (AlfredException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
        while (true) {
            String command = ui.readCommand();
            ui.showSeparator();

            try {
                CommandType commandType = parser.parseCommandType(command);
                Command executableCommand = parser.parseCommand(commandType);

                if (executableCommand != null) {
                    executableCommand.execute(tasks, ui, storage);
                    if (executableCommand.isExit()) {
                        break;
                    }
                } else if (commandType == CommandType.MARK) {
                    int taskIndex = parser.parseTaskIndex(command, commandType.getKeyword(), tasks.size());
                    tasks.get(taskIndex).markAsDone();
                    storage.save(tasks);
                    ui.showTaskMarked(tasks.get(taskIndex));
                } else if (commandType == CommandType.UNMARK) {
                    int taskIndex = parser.parseTaskIndex(command, commandType.getKeyword(), tasks.size());
                    tasks.get(taskIndex).markAsNotDone();
                    storage.save(tasks);
                    ui.showTaskUnmarked(tasks.get(taskIndex));
                } else if (commandType == CommandType.DELETE) {
                    int taskIndex = parser.parseTaskIndex(command, commandType.getKeyword(), tasks.size());
                    Task deletedTask = tasks.remove(taskIndex);
                    storage.save(tasks);
                    ui.showTaskDeleted(deletedTask, tasks.size());
                } else {
                    Task newTask = parser.parseTask(command, commandType);
                    tasks.add(newTask);
                    storage.save(tasks);
                    ui.showTaskAdded(tasks.get(tasks.size() - 1), tasks.size());
                }
            } catch (AlfredException e) {
                ui.showError(e.getMessage());
            }
            ui.showSeparator();
        }
    }

}
