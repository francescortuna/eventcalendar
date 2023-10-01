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
        StringTokenizer dateToken = new StringTokenizer(date, "/");
        while (dateToken.hasMoreTokens()){
            month = Integer.parseInt(dateToken.nextToken());
            day = Integer.parseInt(dateToken.nextToken());
            year = Integer.parseInt(dateToken.nextToken());
        }
    }

    /**
     * Creates a date with today's date
     */
    public Date() {
        Calendar todayDate = Calendar.getInstance();
        int month = todayDate.get(Calendar.MONTH)+1;
        int day = todayDate.get(Calendar.DAY_OF_MONTH);
        int year = todayDate.get(Calendar.YEAR);

        String formattedDate = string.format("%02d/%02d/%04d", month, day, year);
        Date today = new Date(formattedDate);
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


        public boolean isLeapYear() {
            if(year%QUADRENNIAL == 0){
                if(year%CENTENNIAL==0){
                    if(year%QUATERCENTENNIAL==0){
                        return true;
                    } else { return false;}
                } else { return true; }
            } else { return false; }
        }

        if(month >= MIN_MONTHS && month <= MAX_MONTHS){
            if(day >= MIN_DAYS) {
                if (month == jan || month == mar || month == may || month == july ||
                        month == aug || month == oct || month == dec) {
                    if (day <= MONTH_WITH_31DAYS) {
                        return true;
                    }

                } else if (month == apr || month == june || month == sept || month == nov) {
                    if (day <= MONTH_WITH_30DAYS) {
                        return true;
                    }

                } else if (month == feb) {
                    if (isLeapYear && day <= DAYS_IN_FEB_LEAP_YEAR) {
                        return true;
                    } else if (day <= DAYS_IN_FEB_NOT_LEAP_YEAR){
                        return true;
                    }
                } return false;
            } return false;
        } return false;
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
    //UNSURE OF EXACT USE, NEED TO UPDATE ACCORDINGLY
    public boolean equals (Object obj) {
        Date eventDate = obj;
        Date currentDate = today();
        if(eventDate.equals(currentDate)){
            return true;
        } else {
            return false;
        }
    }
}