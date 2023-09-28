package eventorganizer;

/**
 * @author Frances Cortuna
 * Constants for timeslot
 */

public enum Timeslot {
    MORNING(10, 30),
    AFTERNOON(2,00),
    EVENING(6,30);

    private int hour;
    private int minute;

    /**
     * Create a Timeslot object
     * @param hour
     * @param minute
     */
    Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }
}
