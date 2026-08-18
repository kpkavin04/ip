/**
 * Represents one task in Alfred's task list.
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Creates an incomplete task with the given description.
     *
     * @param description text describing the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status symbol displayed beside the task.
     *
     * @return {@code "X"} when the task is complete, otherwise a space
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /** Marks this task as complete. */
    public void markAsDone() {
        isDone = true;
    }

    /** Marks this task as incomplete. */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the task description.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns this task in the format used in Alfred's task list.
     *
     * @return a status icon followed by the task description
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
