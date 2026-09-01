package alfred;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import alfred.storage.Storage;
import alfred.ui.Ui;

class AlfredTest {
    @TempDir
    Path temporaryDirectory;

    @Test
    void getResponse_validCommands_addsAndListsTasks() {
        Alfred alfred = createGuiAlfred();

        assertEquals("Got it. I've added this task:\n  [T][ ] read book\n"
                        + "Now you have 1 tasks in the list.",
                alfred.getResponse("todo read book"));
        assertEquals("AddCommand", alfred.getLastCommandType());
        assertEquals("Here are the tasks in your list:\n1.[T][ ] read book", alfred.getResponse("list"));
        assertEquals("ListCommand", alfred.getLastCommandType());
    }

    @Test
    void getResponse_invalidCommand_returnsErrorWithoutChangingTasks() {
        Alfred alfred = createGuiAlfred();

        alfred.getResponse("todo read book");

        assertEquals("Alfred cannot add a to-do without a mission description.", alfred.getResponse("todo"));
        assertEquals("Error", alfred.getLastCommandType());
        assertEquals("Here are the tasks in your list:\n1.[T][ ] read book", alfred.getResponse("list"));
    }

    @Test
    void getResponse_byeRequestsExit() {
        Alfred alfred = createGuiAlfred();

        assertEquals("Bye. Hope to see you again soon sir!", alfred.getResponse("bye"));
        assertEquals("ExitCommand", alfred.getLastCommandType());
        assertTrue(alfred.isExitRequested());
    }

    /** Creates Alfred with temporary storage and a GUI response buffer. */
    private Alfred createGuiAlfred() {
        StringBuilder responseBuffer = new StringBuilder();
        Storage storage = new Storage(temporaryDirectory.resolve("data").resolve("alfred.txt"));
        return new Alfred(storage, new Ui(responseBuffer), responseBuffer);
    }
}
