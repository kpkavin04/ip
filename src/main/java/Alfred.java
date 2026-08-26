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
                Command executableCommand = parser.parseCommand(command, tasks.size());
                executableCommand.execute(tasks, ui, storage);
                if (executableCommand.isExit()) {
                    break;
                }
            } catch (AlfredException e) {
                ui.showError(e.getMessage());
            }
            ui.showSeparator();
        }
    }

}
