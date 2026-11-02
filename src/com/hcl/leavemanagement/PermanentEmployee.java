
package com.hcl.leavemanagement;

public class PermanentEmployee extends Employee {

    public PermanentEmployee() {
        super();
    }

    public PermanentEmployee(String employeeId,
                            String employeeName,
                            String department,
                            String email,
                            int availableLeaveBalance) {

        super(employeeId, employeeName, department,
              email, availableLeaveBalance);
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Employee Type : Permanent");
    }
}

