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
}
