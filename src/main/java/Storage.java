import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Saves Alfred tasks to, and loads them from, a file relative to the project directory.
 */
public class Storage {
    private static final Path FILE_PATH = Path.of("data", "alfred.txt");

    /**
     * Loads all saved tasks. A missing data file represents an empty task list.
     *
     * @return the tasks saved in the data file
     * @throws AlfredException if the data file cannot be read or contains invalid task data
     */
    public ArrayList<Task> load() throws AlfredException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(FILE_PATH)) {
            return tasks;
        }

        try {
            for (String line : Files.readAllLines(FILE_PATH, StandardCharsets.UTF_8)) {
                if (!line.isEmpty()) {
                    tasks.add(deserialise(line));
                }
            }
            return tasks;
        } catch (IOException | IllegalArgumentException e) {
            throw new AlfredException("Alfred could not load the saved tasks.");
        }
    }

    /**
     * Saves the current task list, creating the data directory when necessary.
     *
     * @param tasks tasks to save
     * @throws AlfredException if the task data cannot be saved
     */
    public void save(List<Task> tasks) throws AlfredException {
        ArrayList<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(serialise(task));
        }

        try {
            Files.createDirectories(FILE_PATH.getParent());
            Files.write(FILE_PATH, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new AlfredException("Alfred could not save the tasks.");
        }
    }

    /** Serializes one task into an escaped tab-separated storage line. */
    private String serialise(Task task) {
        String done = task.isDone() ? "1" : "0";
        if (task.getType() == TaskType.TODO) {
            return "T\t" + done + "\t" + escape(task.getDescription());
        }
        if (task.getType() == TaskType.DEADLINE) {
            Deadline deadline = (Deadline) task;
            return "D\t" + done + "\t" + escape(task.getDescription()) + "\t" + deadline.getBy();
        }
        Event event = (Event) task;
        return "E\t" + done + "\t" + escape(task.getDescription()) + "\t" + event.getFrom()
                + "\t" + event.getTo();
    }

    /** Deserializes one task from an escaped tab-separated storage line. */
    private Task deserialise(String line) throws AlfredException {
        String[] parts = line.split("\\t", -1);
        Task task;
        if (parts.length == 3 && parts[0].equals("T")) {
            task = new Todo(unescape(parts[2]));
        } else if (parts.length == 4 && parts[0].equals("D")) {
            task = new Deadline(unescape(parts[2]), TaskDateTime.parseStored(unescape(parts[3])));
        } else if (parts.length == 5 && parts[0].equals("E")) {
            task = new Event(unescape(parts[2]), TaskDateTime.parseStored(unescape(parts[3])),
                    TaskDateTime.parseStored(unescape(parts[4])));
        } else {
            throw new IllegalArgumentException("Invalid task data");
        }

        if (parts[1].equals("1")) {
            task.markAsDone();
        } else if (!parts[1].equals("0")) {
            throw new IllegalArgumentException("Invalid task status");
        }
        return task;
    }

    /** Escapes backslashes, tabs, and newlines so each task stays on one storage line. */
    private String escape(String text) {
        return text.replace("\\", "\\\\").replace("\t", "\\t").replace("\n", "\\n");
    }

    /** Restores characters escaped by {@link #escape(String)}. */
    private String unescape(String text) {
        StringBuilder result = new StringBuilder();
        boolean escaping = false;
        for (char character : text.toCharArray()) {
            if (escaping) {
                if (character == 't') {
                    result.append('\t');
                } else if (character == 'n') {
                    result.append('\n');
                } else if (character == '\\') {
                    result.append('\\');
                } else {
                    throw new IllegalArgumentException("Invalid escape sequence");
                }
                escaping = false;
            } else if (character == '\\') {
                escaping = true;
            } else {
                result.append(character);
            }
        }
        if (escaping) {
            throw new IllegalArgumentException("Invalid escape sequence");
        }
        return result.toString();
    }
}
