package alfred.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
