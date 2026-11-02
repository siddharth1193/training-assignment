
package com.hcl.leavemanagement;

public class LeaveWorkflowTest {

    public static void main(String[] args) {

        LeaveManagementSystem system =
                new LeaveManagementSystem();

        // Step 1: Register an employee
        Employee employee = new PermanentEmployee(
                "EMP101",
                "Rahul Sharma",
                "IT",
                "rahul@company.com",
                18
        );

        system.addEmployee(employee);

        try {
            // Step 2: Apply for 3 days of casual leave
            System.out.println("\n===== TEST 1: APPLY LEAVE =====");

            LeaveRequest firstRequest = system.applyLeave(
                    "EMP101",
                    LeaveType.CASUAL,
                    3,
                    "Family function"
            );

            System.out.println("Request created successfully.");
            firstRequest.displayDetails();

            // Step 3: Approve the first request
            System.out.println("\n===== TEST 2: APPROVE LEAVE =====");

            system.approveLeave(firstRequest.getRequestId());

            System.out.println(
                    "Balance after approval: "
                    + employee.getAvailableLeaveBalance()
            );

            // Step 4: Apply for 2 days of sick leave
            System.out.println("\n===== TEST 3: APPLY SECOND REQUEST =====");

            LeaveRequest secondRequest = system.applyLeave(
                    "EMP101",
                    LeaveType.SICK,
                    2,
                    "Medical appointment"
            );

            System.out.println(
                    "Second request ID: "
                    + secondRequest.getRequestId()
            );

            // Step 5: Reject the second request
            System.out.println("\n===== TEST 4: REJECT LEAVE =====");

            system.rejectLeave(secondRequest.getRequestId());

            System.out.println(
                    "Balance after rejection: "
                    + employee.getAvailableLeaveBalance()
            );

            // Step 6: Display all requests
            System.out.println("\n===== ALL LEAVE REQUESTS =====");

            system.displayAllLeaveRequests();

            // Step 7: Check the final balance
            int finalBalance = employee.getAvailableLeaveBalance();

            System.out.println("\n===== FINAL RESULT =====");
            System.out.println("Expected balance: 15");
            System.out.println("Actual balance: " + finalBalance);

            if (finalBalance == 15
                    && firstRequest.getStatus() == LeaveStatus.APPROVED
                    && secondRequest.getStatus() == LeaveStatus.REJECTED) {

                System.out.println("BASIC WORKFLOW TEST PASSED.");

            } else {
                System.out.println("BASIC WORKFLOW TEST FAILED.");
            }

        } catch (EmployeeNotFoundException
                | InsufficientLeaveException e) {

            System.out.println("Business error: " + e.getMessage());

        } catch (IllegalArgumentException e) {

            System.out.println("Invalid input: " + e.getMessage());
        }
    }
}
