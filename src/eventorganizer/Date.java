package eventorganizer;
import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * @author Jia Wern Chong
 */

public class Date implements Comparable<Date> {

    public static final int MAX_MONTHS = 12;
    public static final int MIN_MONTHS = 1;

    public static final int MIN_DAYS = 1;
    public static final int MONTH_WITH_30DAYS = 30;
    public static final int MONTH_WITH_31DAYS = 31;
    public static final int DAYS_IN_FEB_NOT_LEAP_YEAR = 28;
    public static final int DAYS_IN_FEB_LEAP_YEAR = 29;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    private int year;
    private int month;
    private int day;

    /**
     * Constructor that takes in string in the form of "mm/dd/yy"
     * @param date
     */
    Date(String date) {
        StringTokenizer dateToken = new StringTokenizer(date, "/"); // Creates new StringTokenizer object to separate dates into tokens
        while (dateToken.hasMoreTokens()){ // Iterates through the tokens
            month = Integer.parseInt(dateToken.nextToken()); // Cast string into integers for month
            day = Integer.parseInt(dateToken.nextToken()); // Cast string into integers for day
            year = Integer.parseInt(dateToken.nextToken()); // Cast string into integers for year
        }
    }

    /**
     * Creates a date with today's date
     */
    public Date() {
        Calendar todayDate = Calendar.getInstance(); //Creates a calendar object to get today's date
        int month = todayDate.get(Calendar.MONTH)+1; // Get the current month from Calendar class and assign the month
        int day = todayDate.get(Calendar.DAY_OF_MONTH); // Get the current day from Calendar class and assign the day
        int year = todayDate.get(Calendar.YEAR); // Get the current year from Calendar class and assign the year

        String formattedDate = String.format("%02d/%02d/%04d", month, day, year); // Formats the date to be MM/DD/YYYY
        Date today = new Date(formattedDate); // Creates and assign a new Date object for today's date
    }

    /**
     * Compares 2 dates
     */
    @Override
    //UNSURE OF EXACT USE, NEED TO UPDATE ACCORDINGLY
    public int compareTo(Date date) {
        Date eventDate = date;
        Date currentDate = today();
        return (currentDate.compareTo(eventDate));
    }

    /**
     * Returns today's date
     * @return date
     */
    public static Date today () {
        return new Date();
    }

    /**
     * Check if year is a leap year or not
     * @return true or false
     */
    public boolean isLeapYear() { // Method to check if it is a leap year
        if(year%QUADRENNIAL == 0){
            if(year%CENTENNIAL==0){
                if(year%QUATERCENTENNIAL==0){
                    return true; // If year is divisible by 4 (quadrennial), 100 (centennial) and 400 (quatercentennial), it's a leap year,returns true
                } else { return false;} // If year is divisible by 100 but not 400, it is not a leap year, returns false
            } else { return true; } // If year is only divisible by 4 and not divisible by 100, it is a leap year, returns true
        } else { return false; } // If year is not divisible by 4, it is not a leap year, returns false
    }


    /**
     * Check if date is a valid calendar date
     */
    public boolean isValid() {

        int month = getMonth();
        int day = getDay();
        int year = getYear();

        int jan = Month.JANUARY.getMonthValue();
        int feb = Month.FEBRUARY.getMonthValue();
        int mar = Month.MARCH.getMonthValue();
        int apr = Month.APRIL.getMonthValue();
        int may = Month.MAY.getMonthValue();
        int june = Month.JUNE.getMonthValue();
        int july = Month.JULY.getMonthValue();
        int aug = Month.AUGUST.getMonthValue();
        int sept = Month.SEPTEMBER.getMonthValue();
        int oct = Month.OCTOBER.getMonthValue();
        int nov = Month.NOVEMBER.getMonthValue();
        int dec = Month.DECEMBER.getMonthValue();




        if(month >= MIN_MONTHS && month <= MAX_MONTHS){ // Checks if month is a valid month between 1(January) - 12(December)
            if(day >= MIN_DAYS) { // Checks if day is valid (>= 1) and not 0 or negative integer
                if (month == jan || month == mar || month == may || month == july ||
                        month == aug || month == oct || month == dec) { // Check if month is a month with 31 days
                    if (day <= MONTH_WITH_31DAYS) {
                        return true; // If day is between 1-31 days, day is valid, return true
                    }

                } else if (month == apr || month == june || month == sept || month == nov) { // Check if month is a month with 30 days
                    if (day <= MONTH_WITH_30DAYS) {
                        return true; // If day is between 1-30 days, day is valid, return true
                    }

                } else if (month == feb) { //Check if month is February
                    if (isLeapYear() && day <= DAYS_IN_FEB_LEAP_YEAR) {
                        return true; // If it is a leap year and day is between 1-29, day is valid, return true
                    } else if (day <= DAYS_IN_FEB_NOT_LEAP_YEAR){
                        return true; // If it is not leap year and day is between 1-28, day is valid, return true
                    }
                } return false; // If any field is incorrect
            } return false; // If day is 0 or a negative integer, day is invalid, return false
        } return false; // If month is not a valid month between 1(January) - 12(December), return false
    }

    /**
     * Returns the textual representation of a fitness.student.Date object
     * @param date
     */
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    /**
     * Getter method for day of a date
     * @return day
     */
    public int getDay() {
        return day;
    }

    /**
     * Getter method for month of a date
     * @return month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Getter method for year of a date
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     *  Check if two dates are the same
     */
    @Override
    public boolean equals (Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Date other = (Date) obj;
        return ((getMonth()==other.getMonth()) && (getDay() ==other.getDay())
                && (getYear()==other.getYear()));
    }
}