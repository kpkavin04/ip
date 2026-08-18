import java.util.Scanner;

/**
 * Starts Alfred, greets the user, and responds to commands until the user exits.
 */
public class Alfred {
    /**
     * Displays Alfred's greeting, echoes each command, and exits on {@code bye}.
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
        while (true) {
            String command = scanner.nextLine();
            System.out.println(separator);

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon sir!");
                System.out.println(separator);
                break;
            }

            System.out.println(command);
            System.out.println(separator);
        }
    }
}
