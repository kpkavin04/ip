import java.util.Scanner;

/**
 * Starts Alfred, greets the user, stores tasks, and responds to commands until the user exits.
 */
public class Alfred {
    /**
     * Displays Alfred's greeting, stores entered tasks, lists them on request, updates task status,
     * and exits on {@code bye}.
     *
     * @param args command-line arguments, which are not used
     */
    public static void main(String[] args) {
        String separator = "____________________________________________________________";
        String banner = "    _    _  __             _ \n"
                + "   / \\  | |/ _|_ __ ___  __| |\n"
                + "  / _ \\ | | |_| '__/ _ \\/ _` |\n"
                + " / ___ \\| |  _| | |  __/ (_| |\n"
                + "/_/   \\_\\_|_| |_|  \\___|\\__,_|\n";
        System.out.println(separator);
        System.out.println(banner);
        System.out.println("How can I assist from the cave?");
        System.out.println(separator);

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;
        while (true) {
            String command = scanner.nextLine();
            System.out.println(separator);

            try {
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon sir!");
                    System.out.println(separator);
                    break;
                }

                if (command.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + "." + tasks[i]);
                    }
                } else if (command.startsWith("mark ")) {
                    int taskNumber = Integer.parseInt(command.substring(5));
                    int taskIndex = taskNumber - 1;
                    tasks[taskIndex].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[taskIndex]);
                } else if (command.startsWith("unmark ")) {
                    int taskNumber = Integer.parseInt(command.substring(7));
                    int taskIndex = taskNumber - 1;
                    tasks[taskIndex].markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[taskIndex]);
                } else {
                    Task newTask;
                    if (command.equals("deadline") || command.startsWith("deadline ")) {
                        int byMarkerIndex = command.indexOf(" /by ");
                        if (byMarkerIndex == -1) {
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
                        newTask = new Deadline(description, by);
                    } else if (command.startsWith("event ")) {
                        int fromMarkerIndex = command.indexOf(" /from ");
                        int toMarkerIndex = command.indexOf(" /to ", fromMarkerIndex + 7);
                        String description = command.substring(6, fromMarkerIndex);
                        String from = command.substring(fromMarkerIndex + 7, toMarkerIndex);
                        String to = command.substring(toMarkerIndex + 5);
                        newTask = new Event(description, from, to);
                    } else if (command.equals("todo") || command.startsWith("todo ")) {
                        String description = command.substring(4).trim();
                        if (description.isEmpty()) {
                            throw new AlfredException("Alfred cannot add a to-do without a mission description.");
                        }
                        newTask = new Todo(description);
                    } else {
                        throw new AlfredException("Alfred does not recognize that command. Please try again.");
                    }
                    tasks[taskCount] = newTask;
                    taskCount++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                }
            } catch (AlfredException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(separator);
        }
    }
}
