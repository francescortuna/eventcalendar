package eventorganizer;

/**
 * @author Frances Cortuna
 *         Constants for timeslot
 */

public enum Timeslot {
    MORNING(10, 30),
    AFTERNOON(14, 0),
    EVENING(18, 30);

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
                return String.format("%d:%02d PM", hour % 12, minute); // ternary operator turns 14 into 2
            case EVENING:
                return String.format("%d:%02d PM", hour % 12, minute); // turns 18 into 6
            default:
                return "";

        }
    }

    /**
     * 
     * @return hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * 
     * @return minute
     */
    public int getMinute() {
        return minute;
    }
}
