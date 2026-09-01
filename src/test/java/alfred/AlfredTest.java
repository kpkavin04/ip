package alfred;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AlfredTest {

    @Test
    void getResponse_messageReceived_returnsEchoResponse() {
        Alfred alfred = new Alfred();

        assertEquals("Alfred heard: add task", alfred.getResponse("add task"));
    }
}
