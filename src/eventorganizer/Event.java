package eventorganizer;

/**
 * Event class to create an event with a date, timeslot, location, contact, and duration.
 * @author Frances Cortuna
 */
public class Event implements Comparable<Event> {

    private Date date;
    private Timeslot startTime;
    private Location location;
    private Contact contact;
    private int duration;

    /**
     * Constructor for event class to initialize an Event object.
     * @param date Date of event
     * @param startTime Timeslot of event
     * @param location Location of event
     * @param contact Contact of event
     * @param duration Duration of event
     */
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
     * Checks if two Event objects are equal.
     * @param object Event being compared
     * @return boolean whether or not two events are equal
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
     * Format all event details into a String.
     * @return String with all event details, formatted accordingly.
     */
    @Override
    public String toString() {
        return String.format("[Event Date: %02d/%02d/%04d] [Start: %s] [End: %s] @%s (%s, %s) [Contact: %s, %s]",
                getDate().getMonth(),
                getDate().getDay(), getDate().getYear(), getStartTime().getStartTime(), getEndTime(), getLocation(),
                getLocation().getBuildingName(), getLocation().getCampusName(),
                getContact().getDepartment().getFullName(),
                getContact().getEmail());
    }

    /**
     * Compares two Event objects.
     * If event being compared to a different event is happening before, it will return a negative number.
     * If event being compared to a different event is happening after, it will return a positive number.
     * Otherwise (if events are happening at exact same day and time), it will return 0.
     * @param other Event being compared
     * @return A negative or positive number or 0 depending on if event happens before, after, or at same time as different event.
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
     * Gets end time of an event.
     * @return String with end time, formatted to hh:mm AM/PM
     */
    public String getEndTime() {
        int startHour = getStartTime().getHour();
        int startMinute = getStartTime().getMinute();

        int addHour = duration / 60;
        int addMinute = duration % 60;

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
     * Gets Date object of event.
     * @return Date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets timeslot of event
     * @return Timeslot
     */
    public Timeslot getStartTime() {
        return startTime;
    }

    /**
     * Gets location of event.
     * @return Location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Gets contact of event.
     * @return Contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Testbed main to test all overrided methods.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        testUnequalEvents();
        testEqualEvents();
        testString();
        testLessThanDates();
        testMoreThanDates();
        testEqualDatesTimeslots();
        testLessThanTimeslots();
        testMoreThanTimeslots();
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
        System.out.println("**Test case #1 for equals(): Event one and event two are two different events.");
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
        System.out.println("**Test case #2 for equals(): Event one and event two are the same events.");
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
        System.out.println("**Test case #1 for toString(): Check output string.");
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

        String expectedOutcome = "Less than";
        int actualOutcome = eventOne.compareTo(eventTwo);
        System.out.println("**Test case #1 for compareTo(): Event one is before event two.");
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

        String expectedOutcome = "Greater than";
        int actualOutcome = eventOne.compareTo(eventTwo);
        System.out.println("**Test case #2 for compareTo(): Event one is after event two.");
        testResult(expectedOutcome, actualOutcome);
    }

    /**
     * Test case #3 for compareTo(): Events one and two are happening at the same
     * day, at the same time
     */
    private static void testEqualDatesTimeslots() {
        Date dateOne = new Date("11/10/2023");
        Contact contactOne = new Contact(Department.CS, "fdc16@rutgers.edu");
        Event eventOne = new Event(dateOne, Timeslot.MORNING, Location.ARC103, contactOne, 45);

        Date dateTwo = new Date("11/10/2023");
        Contact contactTwo = new Contact(Department.BAIT, "sjd13@rutgers.edu");
        Event eventTwo = new Event(dateTwo, Timeslot.MORNING, Location.AB2225, contactTwo, 30);

        String expectedOutcome = "Equal";
        int actualOutcome = eventOne.compareTo(eventTwo);
        System.out.println("**Test case #3 for compareTo(): Event one and two are happening at the same day and time.");
        testResult(expectedOutcome, actualOutcome);
    }

    /**
     * Test case #4 for compareTo(): Event one is happening on the same day, but
     * before event two
     */
    private static void testLessThanTimeslots() {
        Date dateOne = new Date("11/10/2023");
        Contact contactOne = new Contact(Department.CS, "fdc16@rutgers.edu");
        Event eventOne = new Event(dateOne, Timeslot.MORNING, Location.ARC103, contactOne, 45);

        Date dateTwo = new Date("11/10/2023");
        Contact contactTwo = new Contact(Department.BAIT, "sjd13@rutgers.edu");
        Event eventTwo = new Event(dateTwo, Timeslot.AFTERNOON, Location.AB2225, contactTwo, 30);

        String expectedOutcome = "Less than";
        int actualOutcome = eventOne.compareTo(eventTwo);
        System.out.println(
                "**Test case #4 for compareTo(): Event one is happening on the same day, but before event two");
        testResult(expectedOutcome, actualOutcome);
    }

    /**
     * Test case #5 for compareTo(): Event one is happening on the same day, but
     * after event two
     */
    private static void testMoreThanTimeslots() {
        Date dateOne = new Date("11/10/2023");
        Contact contactOne = new Contact(Department.CS, "fdc16@rutgers.edu");
        Event eventOne = new Event(dateOne, Timeslot.EVENING, Location.ARC103, contactOne, 45);

        Date dateTwo = new Date("11/10/2023");
        Contact contactTwo = new Contact(Department.BAIT, "sjd13@rutgers.edu");
        Event eventTwo = new Event(dateTwo, Timeslot.MORNING, Location.AB2225, contactTwo, 30);

        String expectedOutcome = "Greater than";
        int actualOutcome = eventOne.compareTo(eventTwo);
        System.out.println(
                "**Test case #5 for compareTo(): Event one is happening on the same day, but before event two");
        testResult(expectedOutcome, actualOutcome);
    }

    /** 
     * Compares expected outcome to actual outcome and outputs result to system.
     * @param expectedOutcome Expected outcome
     * @param actualOutcome Actual outcome
     */
    private static void testResult(boolean expectedOutcome, boolean actualOutcome) {
        if (expectedOutcome == actualOutcome) {
            System.out.println("Success");
        } else {
            System.out.println("Fail");
        }
    }

    /** 
     * Compares expected String to actual String and outputs result to system.
     * @param expectedOutcome Expected String
     * @param actualOutcome Actual String
     */
    private static void testResult(String expectedOutcome, String actualOutcome) {
        if (expectedOutcome.equals(actualOutcome)) {
            System.out.println("Success");
        } else {
            System.out.println("Fail");
        }
    }
    
    /** 
     * Compares expected outcome to actual outcome and outputs result to system.
     * @param expectedOutcome Expected outcome
     * @param actualOutcome Actual outcome
     */
    private static void testResult(String expectedOutcome, int actualOutcome) {
        switch (expectedOutcome) {
            case "Greater than":
                if (actualOutcome > 0) {
                    System.out.println("Success.");
                } else {
                    System.out.println("Fail.");
                }
                break;
            case "Less than":
                if (actualOutcome < 0) {
                    System.out.println("Success");
                } else {
                    System.out.println("Fail.");
                }
                break;
            case "Equal":
                if (actualOutcome == 0) {
                    System.out.println("Success");
                } else {
                    System.out.println("Fail.");
                }
                break;
            default:
                System.out.println("Fail.");
        }
    }
}