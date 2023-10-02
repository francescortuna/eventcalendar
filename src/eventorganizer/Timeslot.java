package eventorganizer;

/**
 * Constants for timeslot
 * @author Frances Cortuna
 */
public enum Timeslot {
    MORNING(10, 30),
    AFTERNOON(14, 0),
    EVENING(18, 30);

    private int hour;
    private int minute;

    /**
     * Timeslot constants with hour and minute as objects
     * @param hour
     * @param minute
     */
    Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Gets start time as a String formatted as hh:mm AM/PM
     * @return start time
     */
    public String getStartTime() {
        int militaryTimeEnd = 12;
        
        switch (this) {
            case MORNING:
                return String.format("%02d:%02d AM", hour, minute);
            case AFTERNOON:
                return String.format("%d:%02d PM", hour % militaryTimeEnd, minute);
            case EVENING:
                return String.format("%d:%02d PM", hour % militaryTimeEnd, minute);
            default:
                return "";

        }
    }

    /**
     * Gets hour
     * @return hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Gets minute
     * @return minute
     */
    public int getMinute() {
        return minute;
    }
}
