import java.time.LocalDateTime;

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
                Command commandType = Command.fromInput(command);
                if (commandType == Command.BYE) {
                    ui.showGoodbye();
                    break;
                }

                if (commandType == Command.LIST) {
                    ui.showTaskList(tasks);
                } else if (commandType == Command.MARK) {
                    int taskIndex = getTaskIndex(command, commandType.getKeyword(), tasks.size());
                    tasks.get(taskIndex).markAsDone();
                    storage.save(tasks);
                    ui.showTaskMarked(tasks.get(taskIndex));
                } else if (commandType == Command.UNMARK) {
                    int taskIndex = getTaskIndex(command, commandType.getKeyword(), tasks.size());
                    tasks.get(taskIndex).markAsNotDone();
                    storage.save(tasks);
                    ui.showTaskUnmarked(tasks.get(taskIndex));
                } else if (commandType == Command.DELETE) {
                    int taskIndex = getTaskIndex(command, commandType.getKeyword(), tasks.size());
                    Task deletedTask = tasks.remove(taskIndex);
                    storage.save(tasks);
                    ui.showTaskDeleted(deletedTask, tasks.size());
                } else {
                    Task newTask;
                    if (commandType == Command.DEADLINE) {
                        int byMarkerIndex = command.indexOf(" /by ");
                        if (byMarkerIndex == -1) {
                            if (command.endsWith(" /by")) {
                                throw new AlfredException("Alfred needs a due time after `/by`.");
                            }
                            throw new AlfredException("Alfred needs `/by` followed by a due time for a deadline.");
                        }
                        String description = command.substring(9, byMarkerIndex).trim();
                        String by = command.substring(byMarkerIndex + 5).trim();
                        if (description.isEmpty()) {
                            throw new AlfredException("Alfred needs a deadline description before `/by`.");
                        }
                        if (by.isEmpty()) {
                            throw new AlfredException("Alfred needs a due time after `/by`.");
                        }
                        LocalDateTime byDateTime = TaskDateTime.parse(by);
                        newTask = new Deadline(description, byDateTime);
                    } else if (commandType == Command.EVENT) {
                        int fromMarkerIndex = command.indexOf(" /from ");
                        if (fromMarkerIndex == -1) {
                            if (command.endsWith(" /from")) {
                                throw new AlfredException("Alfred needs a start time after `/from`.");
                            }
                            throw new AlfredException("Alfred needs `/from` followed by a start time for an event.");
                        }
                        int toMarkerIndex = command.indexOf(" /to ", fromMarkerIndex + 7);
                        if (toMarkerIndex == -1) {
                            if (command.endsWith(" /to")) {
                                throw new AlfredException("Alfred needs an end time after `/to`.");
                            }
                            throw new AlfredException("Alfred needs `/to` followed by an end time for an event.");
                        }
                        String description = command.substring(6, fromMarkerIndex).trim();
                        String from = command.substring(fromMarkerIndex + 7, toMarkerIndex).trim();
                        String to = command.substring(toMarkerIndex + 5).trim();
                        if (description.isEmpty()) {
                            throw new AlfredException("Alfred needs an event description before `/from`.");
                        }
                        if (from.isEmpty()) {
                            throw new AlfredException("Alfred needs a start time after `/from`.");
                        }
                        if (to.isEmpty()) {
                            throw new AlfredException("Alfred needs an end time after `/to`.");
                        }
                        LocalDateTime fromDateTime = TaskDateTime.parse(from);
                        LocalDateTime toDateTime = TaskDateTime.parse(to);
                        newTask = new Event(description, fromDateTime, toDateTime);
                    } else if (commandType == Command.TODO) {
                        String description = command.substring(4).trim();
                        if (description.isEmpty()) {
                            throw new AlfredException("Alfred cannot add a to-do without a mission description.");
                        }
                        newTask = new Todo(description);
                    } else {
                        throw new AlfredException("Alfred does not recognize that command. Please try again.");
                    }
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

    /**
     * Validates a task number supplied to a command and converts it to an array index.
     *
     * @param command full command entered by the user
     * @param commandName name of the command requesting a task number
     * @param taskCount number of tasks currently stored
     * @return the zero-based index of the requested task
     * @throws AlfredException if the task number is missing, invalid, or not in the task list
     */
    private static int getTaskIndex(String command, String commandName, int taskCount) throws AlfredException {
        String taskNumberText = command.substring(commandName.length()).trim();
        if (taskNumberText.isEmpty()) {
            throw new AlfredException("Alfred needs a task number after `" + commandName + "`.");
        }
        if (taskCount == 0) {
            throw new AlfredException("Alfred's task list is empty, so there is nothing to " + commandName + ".");
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(taskNumberText);
        } catch (NumberFormatException e) {
            throw new AlfredException("Alfred needs a whole-number task number after `" + commandName + "`.");
        }

        if (taskNumber < 1 || taskNumber > taskCount) {
            throw new AlfredException("Alfred cannot find task " + taskNumber + ". Choose a number from 1 to "
                    + taskCount + ".");
        }
        return taskNumber - 1;
    }
}
