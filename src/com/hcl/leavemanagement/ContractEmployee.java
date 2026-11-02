
package com.hcl.leavemanagement;

public class ContractEmployee extends Employee {

    public ContractEmployee() {
        super();
    }

    public ContractEmployee(String employeeId,
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
        System.out.println("Employee Type : Contract");
    }
}

