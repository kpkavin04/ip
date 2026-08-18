/**
 * Represents a task without a date or time.
 */
public class Todo extends Task {
    /**
     * Creates an incomplete to-do task with the given description.
     *
     * @param description text describing the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns this to-do with its type and completion status.
     *
     * @return the formatted to-do task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
