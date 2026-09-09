package alfred.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

class TaskListTest {
    @Test
    void findTasks_matchingKeywordIgnoringCase_returnsMatchesInListOrder() {
        Task firstMatch = new Todo("read book");
        Task nonMatch = new Todo("attend lecture");
        Task secondMatch = new Todo("return BOOK");
        TaskList taskList = new TaskList(List.of(firstMatch, nonMatch, secondMatch));

        List<Task> matchingTasks = taskList.findTasks("book");

        assertEquals(List.of(firstMatch, secondMatch), matchingTasks);
    }

    @Test
    void findTasks_noMatchingKeyword_returnsEmptyList() {
        TaskList taskList = new TaskList(List.of(new Todo("read book")));

        assertEquals(List.of(), taskList.findTasks("report"));
    }

    @Test
    void getTasksSortedChronologically_ordersDatedTasksStablyWithoutChangingList() {
        Task firstTodo = new Todo("first todo");
        Task lateDeadline = new Deadline("late deadline", LocalDateTime.of(2024, 3, 3, 0, 0));
        Task sameTimeEvent = new Event("same-time event", LocalDateTime.of(2024, 3, 2, 9, 0),
                LocalDateTime.of(2024, 3, 2, 10, 0));
        Task sameTimeDeadline = new Deadline("same-time deadline", LocalDateTime.of(2024, 3, 2, 9, 0));
        Task secondTodo = new Todo("second todo");
        Task earlyEvent = new Event("early event", LocalDateTime.of(2024, 3, 1, 9, 0),
                LocalDateTime.of(2024, 3, 1, 10, 0));
        List<Task> insertionOrder = List.of(firstTodo, lateDeadline, sameTimeEvent, sameTimeDeadline,
                secondTodo, earlyEvent);
        TaskList taskList = new TaskList(insertionOrder);

        List<Task> ascendingTasks = taskList.getTasksSortedChronologically(true);
        List<Task> descendingTasks = taskList.getTasksSortedChronologically(false);

        assertEquals(List.of(earlyEvent, sameTimeEvent, sameTimeDeadline, lateDeadline, firstTodo, secondTodo),
                ascendingTasks);
        assertEquals(List.of(firstTodo, secondTodo, lateDeadline, sameTimeEvent, sameTimeDeadline, earlyEvent),
                descendingTasks);
        assertEquals(insertionOrder, getTaskOrder(taskList));
    }

    @Test
    void add_nullTask_assertionErrorThrown() {
        TaskList taskList = new TaskList();

        assertThrows(AssertionError.class, () -> taskList.add(null));
    }

    @Test
    void access_invalidIndex_assertionErrorThrown() {
        TaskList taskList = new TaskList(List.of(new Todo("read book")));

        assertThrows(AssertionError.class, () -> taskList.get(-1));
        assertThrows(AssertionError.class, () -> taskList.remove(1));
    }

    private List<Task> getTaskOrder(TaskList taskList) {
        java.util.ArrayList<Task> taskOrder = new java.util.ArrayList<>();
        taskList.forEach(taskOrder::add);
        return taskOrder;
    }
}
