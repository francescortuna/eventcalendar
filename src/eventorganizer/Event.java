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
                getStartTime().equals(other.getStartTime()) && getLocation().equals(other.getLocation())    );
    }

    @Override
    public String toString() {
        return String.format("[Event Date: %02s/%02s/2%03s] [Start: %s:%s]", getDate().getMonth(), getDate().getDay(), getDate().getYear());
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
}
