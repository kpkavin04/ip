package alfred.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Stores and manages Alfred's ordered collection of tasks.
 */
public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> tasks;

    /** Creates an empty task list. */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Creates a task list containing the supplied tasks in their current order.
     *
     * @param tasks tasks with which to initialize the task list
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /** Adds a task to the end of the list. */
    public void add(Task task) {
        tasks.add(task);
    }

    /** Returns the task at the given zero-based index. */
    public Task get(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /** Removes and returns the task at the given zero-based index. */
    public Task remove(int taskIndex) {
        return tasks.remove(taskIndex);
    }

    /** Returns the number of tasks in the list. */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns tasks whose descriptions contain the keyword, ignoring letter case.
     *
     * @param keyword text to look for in task descriptions
     * @return matching tasks in their original list order
     */
    public List<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String lowerCaseKeyword = keyword.toLowerCase();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(lowerCaseKeyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /** Returns an iterator over the tasks in their list order. */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
