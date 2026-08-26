/**
 * Removes a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Creates a command that removes the task at the given zero-based index.
     *
     * @param taskIndex zero-based index of the task to remove
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /** {@inheritDoc} */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlfredException {
        Task deletedTask = tasks.remove(taskIndex);
        storage.save(tasks);
        ui.showTaskDeleted(deletedTask, tasks.size());
    }
}
