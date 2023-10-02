package eventorganizer;

import java.util.StringTokenizer;

/**
 * Contact class for an Event object, with the department and email of contact.
 * @author Frances Cortuna
 */
public class Contact {
    private Department department;
    private String email;

    /**
     * Constructor for Contact object with department and email as instance variables.
     * @param department Department
     * @param email Contact email
     */
    public Contact(Department department, String email) {
        this.department = department;
        this.email = email;
    }

    
    /** 
     * Checks if department and email are valid
     * @return boolean
     */
    public boolean isValid() {
        StringTokenizer tokenizer = new StringTokenizer(email, "@");
        while(tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if(token.equals("rutgers.edu")) {
                for(Department departmentEnum : Department.values()) {
                    String contactDepartment = department.getFullName();
                    if(contactDepartment.equals(departmentEnum.getFullName())) { 
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Gets department of Contact object
     * @return Department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Gets email of Contact object
     * @return email of contact
     */
    public String getEmail() {
        return email;
    }
}
