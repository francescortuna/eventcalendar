package eventorganizer;

/**
 * @author Jia Wern Chong
 */
public enum Month {
    JANUARY(1),
    FEBRUARY(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AUGUST(8),
    SEPTEMBER(9),
    OCTOBER(10),
    NOVEMBER(11),
    DECEMBER(12);

    private int monthValue;

    /**
     * Create a Month object with the month value
     * @param monthValue
     */
    Month(int monthValue){
        this.monthValue = monthValue;
    }

    /**
     * @return Month value of the month
     */
    public int getMonthValue(){
        return monthValue;
    }
}