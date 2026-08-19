/**
 * Represents a task occurring between a specified start and end time.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Creates an incomplete event with its description, start time, and end time.
     *
     * @param description text describing the event
     * @param from event start date or time, kept as entered by the user
     * @param to event end date or time, kept as entered by the user
     */
    public Event(String description, String from, String to) {
        super(TaskType.EVENT, description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns this event with its type, completion status, start time, and end time.
     *
     * @return the formatted event task
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
