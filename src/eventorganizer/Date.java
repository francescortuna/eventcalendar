package eventorganizer;

/**
 * @author Jia Wern Chong
 */

public class Date implements Comparable<Date> {


    private int year;
    private int month;
    private int day;

    /**
     * Constructor that takes in string in the form of "mm/dd/yy"
     * @param date
     */
    Date(String date) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Creates a date with today's date
     */
    public Date() {
        Date todayDate = new Date(date);
    }

    /**
     * Copy constructor that clones a date object
     * @param date
     */
    public Date(Date date) {

    }

    /**
     * Compares 2 dates
     */
    @Override
    public int compareTo(Date date) {

    }

    /**
     * Returns today's date
     * @return date
     */
    public static Date today () {
        return new Date();
    }

    /**
     * Check if date is a valid calandar date
     */
    public boolean isValid() {
        StringTokenizer dateToken = new StringTokenizer(date, "/");
        while (dateToken.hasMoreTokens()){
            month = Integer.parseInt(dateToken.nextToken());
            day = Integer.parseInt(dateToken.nextToken());
            year = Integer.parseInt(dateToken.nextToken());
        }

        public static final int MAX_MONTHS = 12;
        public static final int MIN_MONTHS = 1;

        public static final int MIN_DAYS = 1;
        public static final int MONTH_WITH_30DAYS = 30;
        public static final int MONTH_WITH_31DAYS = 31;
        public static final int DAYS_IN_FEB_NOT_LEAP_YEAR = 28;
        public static final int DAYS_IN_FEB_LEAP_YEAR = 29;

        public static final int JANUARY = 1;
        public static final int FEBRUARY = 2;
        public static final int MARCH = 3;
        public static final int APRIL = 4;
        public static final int MAY = 5;
        public static final int JUNE = 6;
        public static final int JULY = 7;
        public static final int AUGUST = 8;
        public static final int SEPTEMBER = 9;
        public static final int OCTOBER = 10;
        public static final int NOVEMBER = 11;
        public static final int DECEMBER = 12;

        public static final int QUADRENNIAL = 4;
        public static final int CENTENNIAL = 100;
        public static final int QUATERCENTENNIAL = 400;

        public boolean isLeapYear(){
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
                if (month == JANUARY || MARCH || MAY || JULY || AUGUST || OCTOBER || DECEMBER) {
                    if (day <= MONTH_WITH_31DAYS) {
                        return true;
                    }

                } else if (month == APRIL || JUNE || SEPTEMBER || NOVEMBER) {
                    if (day <= MONTH_WITH_30DAYS) {
                        return true;
                    }

                } else if (month == FEBRUARY) {
                    if (isLeapYear && day <= IS_LEAP_YEAR_29DAYS) {
                        return true;
                    } else if (day <= NOT_LEAP_YEAR_28DAYS){
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
    public boolean equals (Object obj) {
        if ()
    }


}