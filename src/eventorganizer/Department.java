package eventorganizer;

/**
 * Constants for departments
 * @author Frances Cortuna
 */
public enum Department {
    CS("Computer Science"),
    EE("Electrical Engineering"),
    ITI("Information Technology and Informatics"),
    MATH("Mathematics"),
    BAIT("Business Analytics and Information Technology");

    private String fullName;

    /**
     * Department constants with its full name as the property
     * @param fullName Full name of the department
     */
    Department(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets full name of the department
     * @return Full name of the department
     */
    public String getFullName() {
        return fullName;
    }
}