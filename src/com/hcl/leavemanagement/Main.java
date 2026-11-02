/*package com.hcl.leavemanagement;

public class Main {

    public static void main(String[] args) {

        Employee e1 = new PermanentEmployee(
            "EMP101",
            "Siddharth Kumar",
            "IT",
            "siddharth@capgemini.com",
            18
        );

        Employee e2 = new ContractEmployee(
            "EMP102",
            "Viraj",
            "HR",
            "viraj@hcl.com",
            10
        );

        e1.displayDetails();

        System.out.println();

        e2.displayDetails();
    }
}

package com.hcl.leavemanagement;

public class Main {

    public static void main(String[] args) {

        LeaveRequest request = new LeaveRequest(
            "LR1001",
            "EMP101",
            LeaveType.CASUAL,
            3,
            "Family function"
        );

        request.displayDetails();
    }
}*

package com.hcl.leavemanagement;

public class Main {

    public static void main(String[] args) {

        LeaveManagementSystem system =
                new LeaveManagementSystem();

        Employee e1 = new PermanentEmployee(
                "EMP101",
                "Rahul Sharma",
                "IT",
                "rahul@company.com",
                18
        );

        Employee e2 = new ContractEmployee(
                "EMP102",
                "Priya Singh",
                "HR",
                "priya@company.com",
                10
        );

        system.addEmployee(e1);
        system.addEmployee(e2);

        System.out.println("\nAll Employees:");
        system.displayAllEmployees();

        System.out.println("\nSearch Result:");
        Employee found = system.findEmployeeById("EMP101");

        if (found != null) {
            found.displayDetails();
        } else {
            System.out.println("Employee not found.");
        }
    }
}*/

package com.hcl.leavemanagement;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final LeaveManagementSystem system =
            new LeaveManagementSystem();

    public static void main(String[] args) {

        boolean running = true;

        while (running) {
            displayMenu();

            int choice = readInt("Enter your choice: ");

            try {
                switch (choice) {

                    case 1:
                        addEmployee();
                        break;

                    case 2:
                        system.displayAllEmployees();
                        break;

                    case 3:
                        searchEmployee();
                        break;

                    case 4:
                        applyLeave();
                        break;

                    case 5:
                        approveLeave();
                        break;

                    case 6:
                        rejectLeave();
                        break;

                    case 7:
                        system.displayAllLeaveRequests();
                        break;

                    case 8:
                        displayLeaveBalance();
                        break;

                    case 9:
                        running = false;
                        System.out.println("Exiting application.");
                        break;

                    default:
                        System.out.println(
                            "Invalid choice. Enter a number from 1 to 9."
                        );
                }

            } catch (EmployeeNotFoundException
                    | InsufficientLeaveException e) {

                System.out.println("Error: " + e.getMessage());

            } catch (IllegalArgumentException e) {

                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println("==================================");
        System.out.println(" EMPLOYEE LEAVE MANAGEMENT SYSTEM");
        System.out.println("==================================");
        System.out.println("1. Add Employee");
        System.out.println("2. Display All Employees");
        System.out.println("3. Search Employee");
        System.out.println("4. Apply Leave");
        System.out.println("5. Approve Leave");
        System.out.println("6. Reject Leave");
        System.out.println("7. Display All Leave Requests");
        System.out.println("8. Display Employee Leave Balance");
        System.out.println("9. Exit");
    }

    private static int readInt(String prompt) {

        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static String readText(String prompt) {

        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static void addEmployee() {

        System.out.println("\n--- Add Employee ---");

        String id = readText("Enter Employee ID: ");
        String name = readText("Enter Employee Name: ");
        String department = readText("Enter Department: ");
        String email = readText("Enter Email: ");
        int balance = readInt("Enter Leave Balance: ");

        if (balance < 0) {
            System.out.println(
                "Error: Leave balance cannot be negative."
            );
            return;
        }

        System.out.println("Employee Type:");
        System.out.println("1. Permanent");
        System.out.println("2. Contract");

        int type = readInt("Select employee type: ");

        Employee employee;

        switch (type) {

            case 1:
                employee = new PermanentEmployee(
                    id, name, department, email, balance
                );
                break;

            case 2:
                employee = new ContractEmployee(
                    id, name, department, email, balance
                );
                break;

            default:
                System.out.println("Invalid employee type.");
                return;
        }

        system.addEmployee(employee);
    }

    private static void searchEmployee() {

        System.out.println("\n--- Search Employee ---");

        String id = readText("Enter Employee ID: ");

        Employee employee = system.findEmployeeById(id);

        if (employee == null) {
            System.out.println(
                "Error: Employee " + id + " does not exist."
            );
            return;
        }

        employee.displayDetails();
    }

    private static void applyLeave()
            throws EmployeeNotFoundException,
                   InsufficientLeaveException {

        System.out.println("\n--- Apply Leave ---");

        String employeeId = readText("Enter Employee ID: ");

        System.out.println("Leave Type:");
        System.out.println("1. CASUAL");
        System.out.println("2. SICK");
        System.out.println("3. EARNED");

        int type = readInt("Select leave type: ");

        LeaveType leaveType;

        switch (type) {
            case 1:
                leaveType = LeaveType.CASUAL;
                break;
            case 2:
                leaveType = LeaveType.SICK;
                break;
            case 3:
                leaveType = LeaveType.EARNED;
                break;
            default:
                System.out.println("Invalid leave type.");
                return;
        }

        int days = readInt("Enter Number of Days: ");
        String reason = readText("Enter Reason: ");

        LeaveRequest request = system.applyLeave(
            employeeId, leaveType, days, reason
        );

        System.out.println(
            "Request created with ID: " + request.getRequestId()
        );
    }

    private static void approveLeave()
            throws EmployeeNotFoundException {

        System.out.println("\n--- Approve Leave ---");

        String requestId = readText("Enter Request ID: ");

        system.approveLeave(requestId);
    }

    private static void rejectLeave() {

        System.out.println("\n--- Reject Leave ---");

        String requestId = readText("Enter Request ID: ");

        system.rejectLeave(requestId);
    }

    private static void displayLeaveBalance()
            throws EmployeeNotFoundException {

        System.out.println("\n--- Employee Leave Balance ---");

        String employeeId = readText("Enter Employee ID: ");

        system.displayLeaveBalance(employeeId);
    }
}





