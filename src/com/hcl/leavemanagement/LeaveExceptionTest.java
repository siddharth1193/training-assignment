
package com.hcl.leavemanagement;

public class LeaveExceptionTest {

    public static void main(String[] args) {

        LeaveManagementSystem system =
                new LeaveManagementSystem();

        Employee employee = new PermanentEmployee(
                "EMP101",
                "Rahul Sharma",
                "IT",
                "rahul@company.com",
                2
        );

        system.addEmployee(employee);

        // Test 1: Employee does not exist
        System.out.println("\nTEST 1: UNKNOWN EMPLOYEE");

        try {
            system.applyLeave(
                    "EMP999",
                    LeaveType.CASUAL,
                    1,
                    "Personal work"
            );
        } catch (EmployeeNotFoundException e) {
            System.out.println("PASS: " + e.getMessage());
        } catch (InsufficientLeaveException e) {
            System.out.println("UNEXPECTED: " + e.getMessage());
        }

        // Test 2: Insufficient leave balance
        System.out.println("\nTEST 2: INSUFFICIENT BALANCE");

        try {
            system.applyLeave(
                    "EMP101",
                    LeaveType.CASUAL,
                    5,
                    "Family function"
            );
        } catch (InsufficientLeaveException e) {
            System.out.println("PASS: " + e.getMessage());
        } catch (EmployeeNotFoundException e) {
            System.out.println("UNEXPECTED: " + e.getMessage());
        }

        // Test 3: Zero leave days
        System.out.println("\nTEST 3: ZERO DAYS");

        try {
            system.applyLeave(
                    "EMP101",
                    LeaveType.SICK,
                    0,
                    "Medical appointment"
            );
            System.out.println("FAIL: Zero days were accepted.");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: " + e.getMessage());
        } catch (EmployeeNotFoundException
                | InsufficientLeaveException e) {
            System.out.println("UNEXPECTED: " + e.getMessage());
        }

        // Test 4: Negative leave days
        System.out.println("\nTEST 4: NEGATIVE DAYS");

        try {
            system.applyLeave(
                    "EMP101",
                    LeaveType.SICK,
                    -2,
                    "Invalid request"
            );
            System.out.println("FAIL: Negative days were accepted.");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: " + e.getMessage());
        } catch (EmployeeNotFoundException
                | InsufficientLeaveException e) {
            System.out.println("UNEXPECTED: " + e.getMessage());
        }

        // Test 5: Duplicate approval
        System.out.println("\nTEST 5: DUPLICATE APPROVAL");

        try {
            LeaveRequest request = system.applyLeave(
                    "EMP101",
                    LeaveType.CASUAL,
                    1,
                    "Personal work"
            );

            system.approveLeave(request.getRequestId());

            int balanceAfterFirstApproval =
                    employee.getAvailableLeaveBalance();

            system.approveLeave(request.getRequestId());

            int balanceAfterSecondApproval =
                    employee.getAvailableLeaveBalance();

            if (balanceAfterFirstApproval == 1
                    && balanceAfterSecondApproval == 1) {
                System.out.println(
                    "PASS: Leave was deducted only once."
                );
            } else {
                System.out.println(
                    "FAIL: Balance changed unexpectedly."
                );
            }

        } catch (EmployeeNotFoundException
                | InsufficientLeaveException e) {
            System.out.println("UNEXPECTED: " + e.getMessage());
        }
    }
}

