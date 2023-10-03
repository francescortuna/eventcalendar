package eventorganizer;

/**
 * EventCalendar class that is a linear data structure to hold the list of events.
 * Has methods to add, remove, and sort events.
 * Can sort events by date/time, campus/building, and department.
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
     * Getter method to get number of events
     * @return Number of events
     */
    public int getNumEvents() {
        return numEvents;
    }
    
    /**
     * Getter method to get events array
     * @return Events array
     */
    public Event[] getEventsArray() {
        return events;
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

        for (int i = 0; i < numEvents; i++) {
            newEventArray[i] = events[i];
        }

        events = newEventArray;
    }

    /**
     * Adds an Event object to array.
     * Returns true if adding event was successful, false if not.
     * 
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
     * 
     * @param event Event to remove from array.
     * @return True if removing event was successful, false if not.
     */
    public boolean remove(Event event) {
        int indexOfEvent = find(event);
        if (indexOfEvent != NOT_FOUND) {
            for (int i = indexOfEvent; i < numEvents - 1; i++) {
                events[i] = events[i + 1];
            }
            numEvents -= 1;
            events[numEvents] = null;
            return true;
        }

        return false;
    }

    /**
     * Checks if event is in array.
     * Returns true if event is in array, false otherwise.
     * 
     * @param event Event to search in array
     * @return True of event is in array, false otherwise.
     */
    public boolean contains(Event event) {
        if (find(event) != NOT_FOUND) {
            return true;
        }
        return false;
    }

    /**
     * Prints event array as it is.
     */
    public void print() {
        for (int i = 0; i < numEvents; i++) {
            System.out.println(events[i].toString());
        }
    }

    /**
     * Switch the order of event one and event two.
     * 
     * @param eventArray Event array to be changed
     * @param eventOne   Index of event one to be switched with event two
     * @param eventTwo   Index of event two to be switched with event one
     */
    private void switchEvents(int eventOne, int eventTwo) {
        Event tempEvent = events[eventOne];
        events[eventOne] = events[eventTwo];
        events[eventTwo] = tempEvent;
    }

    /**
     * Sorts and prints event array in order by date and timeslot.
     */
    public void printByDate() {
        for (int i = 0; i < numEvents - 1; i++) {
            if (events[i].compareTo(events[i + 1]) > 0) {
                switchEvents(i, i + 1);

                int eventToSort = i;
                while (eventToSort != 0) {
                    if (events[eventToSort].compareTo(events[eventToSort - 1]) < 0) {
                        switchEvents(eventToSort, eventToSort - 1);

                        eventToSort -= 1;
                    } else {
                        break;
                    }
                }
            }
        }

        print();
    }

    /**
     * Sorts and prints event array in order by campus and building
     */
    public void printByCampus() {
        for (int i = 0; i < numEvents - 1; i++) {
            int campusComparison = compareCampus(events[i], events[i + 1]);

            if (campusComparison > 0) {
                switchEvents(i, i + 1);

                sortByBuildingCampus(i);
            } else if (campusComparison == 0) {
                int buildingComparison = compareBuilding(events[i], events[i + 1]);

                if (buildingComparison > 0) {
                    switchEvents(i, i + 1);

                    sortByBuildingCampus(i);
                }
            }
        }

        print();
    }

    /**
     * If an event is switched, it sorts the newly switched event by comparing it to previous events.
     * Sorts by campus first, then building.
     * @param eventToSort Event that was just switched
     */
    private void sortByBuildingCampus(int eventToSort) {
        while (eventToSort != 0) {
            int campusComparison = compareCampus(events[eventToSort], events[eventToSort - 1]);

            if (campusComparison < 0) {
                switchEvents(eventToSort, eventToSort - 1);

                eventToSort -= 1;
            } else if (campusComparison == 0) {
                int buildingComparison = compareBuilding(events[eventToSort], events[eventToSort - 1]);

                if (buildingComparison < 0) {
                    switchEvents(eventToSort, eventToSort - 1);

                    eventToSort -= 1;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
    }

    /**
     * Compares campuses of two events.
     * Returns positive number if event one's campus comes after event two
     * Returns negative number if event one's campus comes before event two
     * Returns a 0 if they are on the same campus
     * 
     * @param eventOne Event one to compare
     * @param eventTwo Event two to compare
     * @return a positive number, a negative number, or 0 depending on the
     *         comparison results
     */
    private int compareCampus(Event eventOne, Event eventTwo) {
        String eventOneCampus = eventOne.getLocation().getCampusName();
        String eventTwoCampus = eventTwo.getLocation().getCampusName();

        return eventOneCampus.compareTo(eventTwoCampus);
    }

    /**
     * Compares buildings of two events.
     * Returns positive number if event one's building comes after event two
     * Returns negative number if event one's building comes before event two
     * Returns a 0 if they are in the same building
     * 
     * @param eventOne event one to compare
     * @param eventTwo Event two to compare
     * @return A positive number, a negative number, or 0 depending on the
     *         comparison results
     */
    private int compareBuilding(Event eventOne, Event eventTwo) {
        String eventOneBuilding = eventOne.getLocation().getBuildingName();
        String eventTwoBuilding = eventTwo.getLocation().getBuildingName();

        return eventOneBuilding.compareTo(eventTwoBuilding);
    }

    /**
     * Sorts and prints events array by department
     */
    public void printByDepartment() {
        for(int i = 0; i < numEvents - 1; i++) {
            int departmentComparison = compareDepartment(events[i], events[i + 1]);
            if (departmentComparison > 0) {
                switchEvents(i, i + 1);

                int eventToSort = i;
                while(eventToSort != 0) {
                    departmentComparison = compareDepartment(events[eventToSort], events[eventToSort - 1]);

                    if (departmentComparison < 0) {
                        switchEvents(eventToSort, eventToSort - 1);
                        eventToSort--;
                    } else {
                        break;
                    }
                }
            }
        }

        print();
    }

    /**
     * Compares departments of two events.
     * Returns a positive number if event one's department is after event two's.
     * Returns a negative number if event one's department is before event two's.
     * Returns 0 if they're in same department.
     * @param eventOne Event one being compared
     * @param eventTwo Event two being compared
     * @return A positive number, a negative number, or 0 depending on result of comparison.
     */
    private int compareDepartment(Event eventOne, Event eventTwo) {
        String eventOneDepartment = eventOne.getContact().getDepartment().getFullName();
        String eventTwoDepartment = eventTwo.getContact().getDepartment().getFullName();

        return eventOneDepartment.compareTo(eventTwoDepartment);
    }
}
