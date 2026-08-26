/**
 * Marks a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Creates a command that marks the task at the given zero-based index as not done.
     *
     * @param taskIndex zero-based index of the task to unmark
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /** {@inheritDoc} */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlfredException {
        Task task = tasks.get(taskIndex);
        task.markAsNotDone();
        storage.save(tasks);
        ui.showTaskUnmarked(task);
    }
}
