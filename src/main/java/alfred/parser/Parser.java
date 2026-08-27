package alfred.parser;

import java.time.LocalDateTime;

import alfred.command.AddCommand;
import alfred.command.Command;
import alfred.command.DeleteCommand;
import alfred.command.ExitCommand;
import alfred.command.ListCommand;
import alfred.command.MarkCommand;
import alfred.command.UnmarkCommand;
import alfred.exception.AlfredException;
import alfred.task.Deadline;
import alfred.task.Event;
import alfred.task.Task;
import alfred.task.TaskDateTime;
import alfred.task.Todo;

/**
 * Interprets user commands and constructs the tasks requested by those commands.
 */
public class Parser {
    /** Returns the command type recognized at the start of a user input line. */
    private CommandType parseCommandType(String input) {
        return CommandType.fromInput(input);
    }

    /**
     * Creates an executable command when the user input contains sufficient command details.
     *
     * @param input full command entered by the user
     * @param taskCount number of tasks currently stored
     * @return the executable command described by the input
     * @throws AlfredException if the input has an unknown command or invalid command details
     */
    public Command parseCommand(String input, int taskCount) throws AlfredException {
        CommandType commandType = parseCommandType(input);
        if (commandType == CommandType.BYE) {
            return new ExitCommand();
        }
        if (commandType == CommandType.LIST) {
            return new ListCommand();
        }
        if (commandType == CommandType.MARK) {
            return new MarkCommand(parseTaskIndex(input, commandType.getKeyword(), taskCount));
        }
        if (commandType == CommandType.UNMARK) {
            return new UnmarkCommand(parseTaskIndex(input, commandType.getKeyword(), taskCount));
        }
        if (commandType == CommandType.DELETE) {
            return new DeleteCommand(parseTaskIndex(input, commandType.getKeyword(), taskCount));
        }
        return new AddCommand(parseTask(input, commandType));
    }

    /**
     * Creates the task described by a task-creation command.
     *
     * @param input full command entered by the user
     * @param commandType recognized command type
     * @return the task described by the command
     * @throws AlfredException if the command is unrecognized or has invalid task details
     */
    private Task parseTask(String input, CommandType commandType) throws AlfredException {
        if (commandType == CommandType.DEADLINE) {
            return parseDeadline(input);
        }
        if (commandType == CommandType.EVENT) {
            return parseEvent(input);
        }
        if (commandType == CommandType.TODO) {
            return parseTodo(input);
        }
        throw new AlfredException("Alfred does not recognize that command. Please try again.");
    }

    /**
     * Validates a task number supplied to a command and converts it to an array index.
     *
     * @param input full command entered by the user
     * @param commandName name of the command requesting a task number
     * @param taskCount number of tasks currently stored
     * @return the zero-based index of the requested task
     * @throws AlfredException if the task number is missing, invalid, or not in the task list
     */
    public int parseTaskIndex(String input, String commandName, int taskCount) throws AlfredException {
        String taskNumberText = input.substring(commandName.length()).trim();
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

    /** Parses a deadline command into a deadline task. */
    private Task parseDeadline(String input) throws AlfredException {
        int byMarkerIndex = input.indexOf(" /by ");
        if (byMarkerIndex == -1) {
            if (input.endsWith(" /by")) {
                throw new AlfredException("Alfred needs a due time after `/by`.");
            }
            throw new AlfredException("Alfred needs `/by` followed by a due time for a deadline.");
        }
        String description = input.substring(9, byMarkerIndex).trim();
        String by = input.substring(byMarkerIndex + 5).trim();
        if (description.isEmpty()) {
            throw new AlfredException("Alfred needs a deadline description before `/by`.");
        }
        if (by.isEmpty()) {
            throw new AlfredException("Alfred needs a due time after `/by`.");
        }
        LocalDateTime byDateTime = TaskDateTime.parse(by);
        return new Deadline(description, byDateTime);
    }

    /** Parses an event command into an event task. */
    private Task parseEvent(String input) throws AlfredException {
        int fromMarkerIndex = input.indexOf(" /from ");
        if (fromMarkerIndex == -1) {
            if (input.endsWith(" /from")) {
                throw new AlfredException("Alfred needs a start time after `/from`.");
            }
            throw new AlfredException("Alfred needs `/from` followed by a start time for an event.");
        }
        int toMarkerIndex = input.indexOf(" /to ", fromMarkerIndex + 7);
        if (toMarkerIndex == -1) {
            if (input.endsWith(" /to")) {
                throw new AlfredException("Alfred needs an end time after `/to`.");
            }
            throw new AlfredException("Alfred needs `/to` followed by an end time for an event.");
        }
        String description = input.substring(6, fromMarkerIndex).trim();
        String from = input.substring(fromMarkerIndex + 7, toMarkerIndex).trim();
        String to = input.substring(toMarkerIndex + 5).trim();
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
        return new Event(description, fromDateTime, toDateTime);
    }

    /** Parses a to-do command into a to-do task. */
    private Task parseTodo(String input) throws AlfredException {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new AlfredException("Alfred cannot add a to-do without a mission description.");
        }
        return new Todo(description);
    }
}
