package eventorganizer;

/**
 * TODO add desc for class
 * 
 * @author Jia Wern Chong, Frances Cortuna
 */
public class EventCalendar {

    private static final int INITIAL_CAPACITY = 4;
    private static final int GROWTH_FACTOR = 4;
    private static final int NOT_FOUND = -1;

    private Event[] events;
    private int numEvents;

    /**
     * Constructor for EventCalendar object to initialize object with initial
     * capacity of 4 and initial numEvents as 0
     */
    public EventCalendar() {
        events = new Event[INITIAL_CAPACITY];
        numEvents = 0;
    }

    /**
     * Find an Event in array of Events.
     * If Event is in array, it returns its index. If not, it returns -1
     * 
     * @param event Event to find in array
     * @return Index of event in array, or -1 if not found
     */
    private int find(Event event) {
        for (int i = 0; i < numEvents; i++) {
            if (events[i].equals(event)) {
                return i;
            }
        }

        return NOT_FOUND;
    }

    /**
     * When array is full, grow its capacity by 4.
     */
    private void grow() {
        int newCapacity = events.length + GROWTH_FACTOR;
        Event[] newEventArray = new Event[newCapacity];

        for (int i = 0; i < events.length; i++) {
            newEventArray[i] = events[i];
        }

        events = newEventArray;
    }

    /**
     * Adds an Event object to array.
     * Returns true if adding event was successful, false if not.
     * @param event Event to add to array
     * @return True if adding event was successful, false if not
     */
    public boolean add(Event event) {
        if (numEvents == events.length) {
            grow();
        }

        events[numEvents] = event;
        numEvents += 1;
        return true;
    }

    /**
     * Removes an event from array.
     * Returns true if removal was successful, false otherwise.
     * @param event Event to remove from array.
     * @return True if removing event was successful, false if not.
     */
    public boolean remove(Event event) {
        int indexOfEvent = find(event);
        if(indexOfEvent != NOT_FOUND) {
            for(int i = indexOfEvent; i < numEvents - 1; i++) {
                events[i] = events[i+1];
            }
            numEvents -= 1;
            return true;
        }

        return false;
    }

    /**
     * Checks if event is in array.
     * Returns true if event is in array, false otherwise.
     * @param event Event to search in array
     * @return True of event is in array, false otherwise.
     */
    public boolean contains(Event event) {
        if(find(event) != NOT_FOUND) {
            return true;
        }
        return false;
    }

    /**
     * Prints event array as it is.
     */
    public void print() {
        for(int i = 0; i < events.length; i++) {
            System.out.println(events[i].toString());
        }
    }

    /**
     * Prints event array in order by date and timeslot.
     */
    public void printByDate() {
        for(int i = 0; i < events.length - 1; i++) {
            if(events[i].compareTo(events[i + 1]) >= 0) {
                switchEvents(events, i, i + 1);

                int eventToSort = i;
                while(eventToSort != 0) {
                    if(events[eventToSort].compareTo(events[eventToSort - 1]) <= 0) {
                        switchEvents(events, eventToSort, eventToSort - 1);

                        eventToSort -= 1;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    /**
     * Switch the order of event one and event two.
     * @param eventArray Event array to be changed
     * @param eventOne Index of event one to be switched with event two
     * @param eventTwo Index of event two to be switched with event one
     */
    private void switchEvents(Event[] eventArray, int eventOne, int eventTwo) {
        Event tempEvent = eventArray[eventOne];
        eventArray[eventOne] = eventArray[eventTwo];
        eventArray[eventTwo] = tempEvent;
    }

    // ordered by campus and building/room
    public void printByCampus() {

    }

    // ordered by department
    public void printByDepartment() {

    }
}
