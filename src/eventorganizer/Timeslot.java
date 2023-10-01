package eventorganizer;

/**
 * @author Frances Cortuna
 *         Constants for timeslot
 */

public enum Timeslot {
    MORNING(10, 30),
    AFTERNOON(2, 00),
    EVENING(6, 30);

    private int hour;
    private int minute;

    /**
     * Create a Timeslot object
     * 
     * @param hour
     * @param minute
     */
    Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Getter method to return start time
     * 
     * @return start time
     */
    public String getStartTime() {
        switch (this) {
            case MORNING:
                return String.format("%02d:%02d AM", hour, minute);
            case AFTERNOON:
                return String.format("%d:%02d PM", hour, minute);
            case EVENING:
                return String.format("%d:%02d PM", hour, minute);
            default:
                return "";

        }
    }
}
