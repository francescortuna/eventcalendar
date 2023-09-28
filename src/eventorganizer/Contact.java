package eventorganizer;

import java.util.StringTokenizer;

/**
 * @author Frances Cortuna
 */

public class Contact {
    private Department department;
    private String email;

    /**
     * @param department Department
     * @param email Contact email
     */
    Contact(Department department, String email) {
        this.department = department;
        this.email = email;
    }

    
    /** 
     * Checks if department and email are valid
     * @return boolean
     */
    public boolean isValid() {
        StringTokenizer tokenizer = new StringTokenizer(email, "@"); // Creates new StringTokenizer object to separate email into tokens
        while(tokenizer.hasMoreTokens()) { // Iterates through tokens
            String token = tokenizer.nextToken();
            if(token.equals("rutgers.edu")) { // Checks if a token equals rutgers.edu domain
                for(Department departmentEnum : Department.values()) { // If email is valid, then checks if department name is valid by iterating through Department values
                    String contactDepartment = department.getFullName();
                    if(contactDepartment.equals(departmentEnum.getFullName())) { 
                        return true; // If department name is also valid then return true as both fields are valid
                    }
                }
            }
        }
        return false; // If at least one field is incorrect, return false
    }
}
