import java.util.Scanner;

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

    /**
     * Creates a console user interface that reads from standard input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /** Displays Alfred's welcome message. */
    public void showWelcome() {
        System.out.println(SEPARATOR);
        System.out.println(BANNER);
        System.out.println("How can I assist from the cave?");
        System.out.println(SEPARATOR);
    }

    /** Reads the next command entered by the user. */
    public String readCommand() {
        return scanner.nextLine();
    }

    /** Displays the separator used before and after command responses. */
    public void showSeparator() {
        System.out.println(SEPARATOR);
    }

    /** Displays Alfred's farewell message. */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon sir!");
        showSeparator();
    }

    /** Displays every task in the current task list. */
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /** Displays confirmation that a task was marked as done. */
    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /** Displays confirmation that a task was marked as not done. */
    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /** Displays confirmation that a task was removed. */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /** Displays confirmation that a task was added. */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /** Displays an error message. */
    public void showError(String message) {
        System.out.println(message);
    }
}
