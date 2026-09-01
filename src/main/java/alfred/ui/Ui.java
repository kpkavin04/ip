package alfred.ui;

import java.util.List;
import java.util.Scanner;

import alfred.task.Task;
import alfred.task.TaskList;

/**
 * Handles Alfred's console input and output.
 */
public class Ui {
    private static final String SEPARATOR = "____________________________________________________________";
    private static final String BANNER = "      *==/          |     |            \\==*\n"
            + "     /XX/           |\\__\\/|             \\XX\\\n"
            + "   /XXXX\\           |XXXXX|             /XXXX\\\n"
            + " |XXXXXX\\_         *XXXXXXX*         \\_/XXXXXX|\n"
            + "XXXXXXXXXXXxxxxxxxXXXXXXXXXXXxxxxxxxXXXXXXXXXXX\n"
            + "|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|\n"
            + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n"
            + "|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|\n"
            + " XXXXXX/^^^^\"\\XXXXXXXXXXXXXXXXXXXXX/^^^^^\\XXXXXX\n"
            + " |XXX|       \\XXX/^^\\XXXXX/^^\\XXX/       |XXX|\n"
            + "   \\XX\\       \\X/    \\XXX/    \\X/       /XX/\n"
            + "      \"\\       \"      \\X/      \"       /\n"
            + "\n"
            + "    _    _  __             _\n"
            + "   / \\  | |/ _|_ __ ___  __| |\n"
            + "  / _ \\ | | |_| '__/ _ \\/ _` |\n"
            + " / ___ \\| |  _| | |  __/ (_| |\n"
            + "/_/   \\_\\_|_| |_|  \\___|\\__,_|\n";

    private final Scanner scanner;
    private final StringBuilder responseBuffer;

    /**
     * Creates a console user interface that reads from standard input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
        responseBuffer = null;
    }

    /**
     * Creates a user interface that records responses for display in the GUI.
     *
     * @param responseBuffer buffer that receives displayed messages.
     */
    public Ui(StringBuilder responseBuffer) {
        scanner = new Scanner(System.in);
        this.responseBuffer = responseBuffer;
    }

    /** Displays Alfred's welcome message. */
    public void showWelcome() {
        showMessage(SEPARATOR);
        showMessage(BANNER);
        showMessage("How can I assist from the cave?");
        showMessage(SEPARATOR);
    }

    /** Reads the next command entered by the user. */
    public String readCommand() {
        return scanner.nextLine();
    }

    /** Displays the separator used before and after command responses. */
    public void showSeparator() {
        showMessage(SEPARATOR);
    }

    /** Displays Alfred's farewell message. */
    public void showGoodbye() {
        showMessage("Bye. Hope to see you again soon sir!");
    }

    /** Displays every task in the current task list. */
    public void showTaskList(TaskList tasks) {
        showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            showMessage((i + 1) + "." + tasks.get(i));
        }
    }

    /** Displays the tasks whose descriptions match a search keyword. */
    public void showMatchingTasks(List<Task> matchingTasks) {
        showMessage("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            showMessage((i + 1) + "." + matchingTasks.get(i));
        }
    }

    /** Displays confirmation that a task was marked as done. */
    public void showTaskMarked(Task task) {
        showMessage("Nice! I've marked this task as done:");
        showMessage("  " + task);
    }

    /** Displays confirmation that a task was marked as not done. */
    public void showTaskUnmarked(Task task) {
        showMessage("OK, I've marked this task as not done yet:");
        showMessage("  " + task);
    }

    /** Displays confirmation that a task was removed. */
    public void showTaskDeleted(Task task, int taskCount) {
        showMessage("Noted. I've removed this task:");
        showMessage("  " + task);
        showMessage("Now you have " + taskCount + " tasks in the list.");
    }

    /** Displays confirmation that a task was added. */
    public void showTaskAdded(Task task, int taskCount) {
        showMessage("Got it. I've added this task:");
        showMessage("  " + task);
        showMessage("Now you have " + taskCount + " tasks in the list.");
    }

    /** Displays an error message. */
    public void showError(String message) {
        showMessage(message);
    }

    /**
     * Sends a message to the console or GUI response buffer.
     *
     * @param message text to display.
     */
    private void showMessage(String message) {
        if (responseBuffer == null) {
            System.out.println(message);
        } else {
            responseBuffer.append(message).append(System.lineSeparator());
        }
    }
}
