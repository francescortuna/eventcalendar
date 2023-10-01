package eventorganizer;

/**
 * @author Frances Cortuna
 *         Constants for departments
 */

public enum Department {
    CS("Computer Science"),
    EE("Electrical Engineering"),
    ITI("Information Technology and Informatics"),
    MATH("Mathematics"),
    BAIT("Business Analytics and Information Technology");

    private String fullName;

    /**
     * Create a Department object with its full name
     * 
     * @param fullName Full name of the department
     */
    Department(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 
     * @return Full name of the department
     */
    public String getFullName() {
        return fullName;
    }
}