package eventorganizer;

import java.sql.Time;

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
                getLocation().getBuildingName(), getLocation().getCampusName(), getContact().getDepartment().getFullName(),
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
     * 
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
     * 
     * @return Date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Return timeslot
     * 
     * @return Timeslot
     */
    public Timeslot getStartTime() {
        return startTime;
    }

    /**
     * Return location
     * 
     * @return Location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Return contact
     * 
     * @return Contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Testbed main to test methods
     * TODO check methods with Date methods
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // testUnequalEvents();
        // testEqualEvents();
        testString();
        // testLessThanDates();
        // testMoreThanDates();
        // testEqualDatesTimeslots();
        // testLessThanTimeslots();
        // testMoreThanTimeslots();
    }

    /**
     * Test case #1 for equals(): Check two unequal events
     */
    private static void testUnequalEvents() {
        Date dateOne = new Date("05/10/2024");
        Contact contactOne = new Contact(Department.CS, "fdc16@rutgers.edu");
        Event eventOne = new Event(dateOne, Timeslot.MORNING, Location.ARC103, contactOne, 45);

        Date dateTwo = new Date("12/25/2023");
        Contact contactTwo = new Contact(Department.BAIT, "sjd13@rutgers.edu");
        Event eventTwo = new Event(dateTwo, Timeslot.AFTERNOON, Location.AB2225, contactTwo, 30);

        boolean expectedOutcome = false;
        boolean actualOutcome = eventOne.equals(eventTwo);
        System.out.println("**Test case #1: Event one and Event two are two different events.");
        testResult(expectedOutcome, actualOutcome);
    }

    /**
     * Test case #2 for equals(): Check two equal events
     */
    private static void testEqualEvents() {
        Date dateOne = new Date("05/10/2024");
        Contact contactOne = new Contact(Department.CS, "fdc16@rutgers.edu");
        Event eventOne = new Event(dateOne, Timeslot.MORNING, Location.ARC103, contactOne, 45);

        Date dateTwo = new Date("05/10/2024");
        Contact contactTwo = new Contact(Department.CS, "fdc16@rutgers.edu");
        Event eventTwo = new Event(dateTwo, Timeslot.MORNING, Location.ARC103, contactTwo, 45);

        boolean expectedOutcome = true;
        boolean actualOutcome = eventOne.equals(eventTwo);
        System.out.println("**Test case #2: Event one and event two are the same events.");
        testResult(expectedOutcome, actualOutcome);
    }

    /**
     * Test case #1 for toString(): Check output string.
     */
    private static void testString() {
        Date date = new Date("05/10/2024");
        Contact contact = new Contact(Department.CS, "fdc16@rutgers.edu");
        Event event = new Event(date, Timeslot.MORNING, Location.ARC103, contact, 45);

        String expectedOutcome = "[Event Date: 05/10/2024] [Start: 10:30 AM] [End: 11:15 AM] @ARC103 (Allison Road Classroom, Busch) [Contact: Computer Science, fdc16@rutgers.edu]";
        String actualOutcome = event.toString();
        System.out.println("**Test case #1: Check output string.");
        testResult(expectedOutcome, actualOutcome);
    }

    /**
     * Test case #1 for compareTo(): Event one is before event two
     */
    private static void testLessThanDates() {
        Date dateOne = new Date("11/10/2023");
        Contact contactOne = new Contact(Department.CS, "fdc16@rutgers.edu");
        Event eventOne = new Event(dateOne, Timeslot.MORNING, Location.ARC103, contactOne, 45);

        Date dateTwo = new Date("11/20/2023");
        Contact contactTwo = new Contact(Department.BAIT, "sjd13@rutgers.edu");
        Event eventTwo = new Event(dateTwo, Timeslot.AFTERNOON, Location.AB2225, contactTwo, 30);

        int expectedOutcome = -1;
        int actualOutcome = eventOne.compareTo(eventTwo);
        System.out.println("**Test case #1: Event one is before event two.");
        testResult(expectedOutcome, actualOutcome);
    }

    /**
     * Test case #2 for compareTo(): Event one is after event two
     */
    private static void testMoreThanDates() {
        Date dateOne = new Date("11/20/2023");
        Contact contactOne = new Contact(Department.CS, "fdc16@rutgers.edu");
        Event eventOne = new Event(dateOne, Timeslot.MORNING, Location.ARC103, contactOne, 45);

        Date dateTwo = new Date("11/10/2023");
        Contact contactTwo = new Contact(Department.BAIT, "sjd13@rutgers.edu");
        Event eventTwo = new Event(dateTwo, Timeslot.AFTERNOON, Location.AB2225, contactTwo, 30);

        int expectedOutcome = 1;
        int actualOutcome = eventOne.compareTo(eventTwo);
        System.out.println("**Test case #2: Event one is after event two.");
        testResult(expectedOutcome, actualOutcome);
    }

    /**
     * Test case #3 for compareTo(): Events one and two are happening at the same day, at the same time
     */
    private static void testEqualDatesTimeslots() {
        Date dateOne = new Date("11/10/2023");
        Contact contactOne = new Contact(Department.CS, "fdc16@rutgers.edu");
        Event eventOne = new Event(dateOne, Timeslot.MORNING, Location.ARC103, contactOne, 45);

        Date dateTwo = new Date("11/10/2023");
        Contact contactTwo = new Contact(Department.BAIT, "sjd13@rutgers.edu");
        Event eventTwo = new Event(dateTwo, Timeslot.MORNING, Location.AB2225, contactTwo, 30);

        int expectedOutcome = 0;
        int actualOutcome = eventOne.compareTo(eventTwo);
        System.out.println("**Test case #3: Event one and two are happening at the same day and time.");
        testResult(expectedOutcome, actualOutcome);
    }

    /**
     * Test case #4 for compareTo(): Event one is happening on the same day, but before event two
     */
    private static void testLessThanTimeslots() {
        Date dateOne = new Date("11/10/2023");
        Contact contactOne = new Contact(Department.CS, "fdc16@rutgers.edu");
        Event eventOne = new Event(dateOne, Timeslot.MORNING, Location.ARC103, contactOne, 45);

        Date dateTwo = new Date("11/10/2023");
        Contact contactTwo = new Contact(Department.BAIT, "sjd13@rutgers.edu");
        Event eventTwo = new Event(dateTwo, Timeslot.AFTERNOON, Location.AB2225, contactTwo, 30);

        int expectedOutcome = -1;
        int actualOutcome = eventOne.compareTo(eventTwo);
        System.out.println("**Test case #4: Event one is happening on the same day, but before event two");
        testResult(expectedOutcome, actualOutcome);
    }

    /**
     * Test case #5 for compareTo(): Event one is happening on the same day, but after event two
     */
    private static void testMoreThanTimeslots() {
        Date dateOne = new Date("11/10/2023");
        Contact contactOne = new Contact(Department.CS, "fdc16@rutgers.edu");
        Event eventOne = new Event(dateOne, Timeslot.EVENING, Location.ARC103, contactOne, 45);

        Date dateTwo = new Date("11/10/2023");
        Contact contactTwo = new Contact(Department.BAIT, "sjd13@rutgers.edu");
        Event eventTwo = new Event(dateTwo, Timeslot.MORNING, Location.AB2225, contactTwo, 30);

        int expectedOutcome = 1;
        int actualOutcome = eventOne.compareTo(eventTwo);
        System.out.println("**Test case #4: Event one is happening on the same day, but before event two");
        testResult(expectedOutcome, actualOutcome);
    }

    private static void testResult(boolean expectedOutcome, boolean actualOutcome) {
        if(expectedOutcome == actualOutcome) {
            System.out.println("Success");
        } else {
            System.out.println("Fail");
        }
    }

    private static void testResult(String expectedOutcome, String actualOutcome) {
        if(expectedOutcome.equals(actualOutcome)) {
            System.out.println("Success");
        } else {
            System.out.println("Fail");
        }
    }

    private static void testResult(int expectedOutcome, int actualOutcome) {
        if(expectedOutcome == actualOutcome) {
            System.out.println("Success");
        } else {
            System.out.println("Fail");
        }
    }
}