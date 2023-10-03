package eventorganizer;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * EventOrganizer class as the user interface to handle commands.
 * 
 * @author Jia Wern Chong, Frances Cortuna
 */
public class EventOrganizer {
    private EventCalendar eventCalendar;
    private Scanner scanner;

    /**
     * Constructor to create an instance of the EventOrganizer class.
     */
    public EventOrganizer() {
        eventCalendar = new EventCalendar();
        scanner = new Scanner(System.in);
    }

    /**
     * Allows program to run and accept user input.
     */
    public void run() {
        System.out.println("Event Organizer running....");

        while (true) {
            String command = scanner.nextLine().trim();

            if (command.equals("Q")) {
                System.out.println("Event Organizer terminated.");
                break;
            }

            commands(command);
        }
    }

    /**
     * Processes the command input from the system and directs to appropriate
     * method.
     * 
     * @param commandString Full command string from the system
     */
    private void commands(String commandString) {
        StringTokenizer stringTokenizer = new StringTokenizer(commandString, " ");
        String command = stringTokenizer.nextToken();

        switch (command) {
            case "A":
                addEvent(stringTokenizer);
                break;
            case "R":
                break; // TODO cancel an event
            case "P":
                break; // TODO display event calendar
            case "PE":
                break; // TODO display event calendar sorted by date/timeslot
            case "PC":
                break; // TODO display event calendar sorted by campus/building
            case "PD":
                break; // TODO display event calendar sorted by department
            default:
                System.out.println(String.format("%s is an invalid command!", command));
        }
    }

    /**
     * Add an event to event calendar.
     * @param stringTokenizer Commandline input with event details
     */
    private void addEvent(StringTokenizer stringTokenizer) {
        Date eventDate = new Date(stringTokenizer.nextToken());
        if(!validateDate(eventDate))    return;

        Timeslot timeslot = parseEnum(Timeslot.class, stringTokenizer.nextToken().toUpperCase(), "timeslot");
        if (timeslot == null)   return;
        Location location = parseEnum(Location.class, stringTokenizer.nextToken().toUpperCase(), "location");
        if (location == null)   return;
        Department department = parseEnum(Department.class, stringTokenizer.nextToken().toUpperCase(), "contact information");
        if (department == null) return;

        Contact contact = new Contact(department, stringTokenizer.nextToken());
        if (!contact.isValid()) {
            System.out.println("Invalid contact information!");
            return;
        }

        int duration = Integer.parseInt(stringTokenizer.nextToken());
        if(30 > duration || duration > 120) {
            System.out.println("Event duration must be at least 30 minutes and at most 120 minutes");
            return;
        }

        Event event = new Event(eventDate, timeslot, location, contact, duration);
        if(checkEventIsInCalendar(event)) {
            System.out.println("The event is already on the calendar.");
        } else {
            eventCalendar.add(event);
            System.out.println("Event added to the calendar.");
        }
    }

    /**
     * Validate date and make sure date is a future date that is within 6 months from current date.
     * Prints any error messages for invalid dates.
     * @param eventDate Date of event to be added
     * @return True or false whether or not date is valid
     */
    private boolean validateDate(Date eventDate) {
        if(!eventDate.isValid()) {
            System.out.println(String.format("%d/%d/%d: Invalid calendar date!", eventDate.getMonth(), eventDate.getDay(), eventDate.getYear()));
            return false;
        }

        Date currentDate = new Date();

        if(eventDate.compareTo(currentDate) <= 0) {
            System.out.println(String.format("%d/%d/%d: Event date must be a future date!", eventDate.getMonth(), eventDate.getDay(), eventDate.getYear()));
            return false;
        }

        int sixMonths = 6; // add 6 months to current date to determine limit for adding an event
        Date addSixMonths = new Date(String.format("%02d/%02d/%04d",currentDate.getMonth() + sixMonths, currentDate.getDay(), currentDate.getYear()));

        if(eventDate.compareTo(addSixMonths) > 0) {
            System.out.println(String.format("%d/%d/%d: Event date must be within 6 months!", eventDate.getMonth(), eventDate.getDay(), eventDate.getYear()));
            return false;
        }

        return true;
    }

    /**
     * Process and validate enum values from commandline input.
     * @param <T> Type fo the enum
     * @param enumType Class object that represents enum class
     * @param value The string from commandline input to be parsed as an enum
     * @param name Name of enum value
     * @return If valid enum value, it returns it. Otherwise, it returns null.
     */
    private <T extends Enum<T>> T parseEnum(Class<T> enumType, String value, String name) {
        try {
            return Enum.valueOf(enumType, value.toUpperCase());
        } catch (IllegalArgumentException err) {
            System.out.println(String.format("Invalid %s!", name));
            return null;
        }
    }

    /**
     * Check if an event is in event calendar already.
     * 
     * @param event Event to check for in calendar
     * @return True or false whether or not event is in calendar
     */
    private boolean checkEventIsInCalendar(Event event) {
        if (eventCalendar.getNumEvents() != 0) {
            for (int i = 0; i < eventCalendar.getEventsArray().length; i++) {
                if (event.equals(eventCalendar.getEventsArray()[i])) {
                    return true;
                }
            }
        }

        return false;
    }
}