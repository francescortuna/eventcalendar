package eventorganizer;

/**
 * @author Frances Cortuna
 */

public class Event implements Comparable<Event> {

    private Date date;
    private Timeslot startTime;
    private Location location;
    private Contact contact;
    private int duration;

    public Event(
            Date date,
            Timeslot startTime,
            Location location,
            Contact contact,
            int duration) {
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.contact = contact;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Event other = (Event) object;
        return (getDate().equals(other.getDate()) &&
                getStartTime().equals(other.getStartTime()) && getLocation().equals(other.getLocation()));
    }

    @Override // TODO: Finish method
    public String toString() {
        return String.format("[Event Date: %02d/%02d/2%03d] [Start: %s] [End: ]", getDate().getMonth(),
                getDate().getDay(), getDate().getYear(), getStartTime()); // TODO: fix format for date (year)
    }

    public Date getDate() {
        return date;
    }

    public Timeslot getStartTime() {
        return startTime;
    }

    public Location getLocation() {
        return location;
    }

    public Contact getContact() {
        return contact;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public int compareTo(Event other) {
        // TODO: Finish method
        int dateComparison = this.getDate().compareTo(other.getDate());
        if (dateComparison != 0) {
            return dateComparison;
        }

        int timeslotComparison = this.getStartTime().compareTo(other.getStartTime());
        return timeslotComparison;
    }
}
