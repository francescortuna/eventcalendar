package eventorganizer;

/**
 * @author Jia Wern Chong
 *
 */

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public Date(String date) {

    }

    public Date() {

    }

    public Date(Date date) {

    }

    public int compareTo(Date date) {

    }

    public static Date today () {
        return new Date();
    }

    public boolean isValid() //check if the date is a valid calendar date

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
    public boolean equals (Object obj) {

    }


}