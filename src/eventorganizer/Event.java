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

    /**
     * @param object Event being compared
     * @return boolean Whether or not two events are equal
     */
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

    /**
     * @return String with formatted event details
     */

    @Override
    public String toString() {
        return String.format("[Event Date: %02d/%02d/%04d] [Start: %s] [End: %s] @%s (%s, %s) [Contact: %s, %s]",
                getDate().getMonth(),
                getDate().getDay(), getDate().getYear(), getStartTime().getStartTime(), getEndTime(), getLocation(),
                getLocation().getBuildingName(), getLocation().getCampusName(), getContact().getDepartment(),
                getContact().getEmail());
    }

    /**
     * @param other Event being compared
     * @return
     */
    @Override
    public int compareTo(Event other) {
        int dateComparison = this.getDate().compareTo(other.getDate());
        if (dateComparison != 0) {
            return dateComparison;
        }

        int timeslotComparison = this.getStartTime().compareTo(other.getStartTime());
        return timeslotComparison;
    }

    /**
     * Gets end time
     * @return
     */
    public String getEndTime() {
        int startHour = getStartTime().getHour();
        int startMinute = getStartTime().getMinute();

        int addHour = duration / 60; // 1 hour is 60 minutes, so divide duration by 60 to get how many hours to add
        int addMinute = duration % 60; // duration modulus 60 gets how many minutes to add

        int endHour = startHour + addHour;
        int endMinute = startMinute + addMinute;
        if (endMinute >= 60) {
            endHour++;
            endMinute %= 60;
        }

        return String.format("%02d:%02d %s", endHour > 12 ? endHour % 12 : endHour, endMinute,
                endHour >= 12 ? "PM" : "AM");
    }

    /**
     * Return date
     * @return Date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Return timeslot
     * @return Timeslot
     */
    public Timeslot getStartTime() {
        return startTime;
    }

    /**
     * Return location
     * @return Location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Return contact
     * @return Contact
     */
    public Contact getContact() {
        return contact;
    }
}