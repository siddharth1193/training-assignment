
package com.hcl.leavemanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LeaveManagementSystem {

    private List<Employee> employees = new ArrayList<>();
    private Set<String> employeeEmails = new HashSet<>();
    private Map<String, Employee> employeeMap = new HashMap<>();

  
    private List<LeaveRequest> leaveRequests = new ArrayList<>();

   
    private int nextRequestNumber = 1001;

   
    public void addEmployee(Employee employee) {

        if (employee == null) {
            System.out.println("Error: Employee cannot be null.");
            return;
        }

        String id = employee.getEmployeeId();
        String email = employee.getEmail();

        if (id == null || id.trim().isEmpty()) {
            System.out.println("Error: Employee ID is required.");
            return;
        }

        if (email == null || email.trim().isEmpty()) {
            System.out.println("Error: Email is required.");
            return;
        }

        if (employeeMap.containsKey(id)) {
            System.out.println("Error: Employee ID already exists.");
            return;
        }

        if (employeeEmails.contains(email.toLowerCase())) {
            System.out.println("Error: Email already exists.");
            return;
        }

        if (employee.getAvailableLeaveBalance() < 0) {
            System.out.println(
                "Error: Leave balance cannot be negative."
            );
            return;
        }

        employees.add(employee);
        employeeEmails.add(email.toLowerCase());
        employeeMap.put(id, employee);

        System.out.println("Employee added successfully.");
    }

    // Display employees using Iterator
    public void displayAllEmployees() {

        Iterator<Employee> iterator = employees.iterator();

        if (!iterator.hasNext()) {
            System.out.println("No employees found.");
            return;
        }

        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            employee.displayDetails();
            System.out.println("--------------------");
        }
    }

    // Search employee by ID
    public Employee findEmployeeById(String employeeId) {
        return employeeMap.get(employeeId);
    }

    // Search employee or throw custom exception
    public Employee findEmployeeByIdOrThrow(String employeeId)
            throws EmployeeNotFoundException {

        Employee employee = employeeMap.get(employeeId);

        if (employee == null) {
            throw new EmployeeNotFoundException(
                "Employee " + employeeId + " does not exist."
            );
        }

        return employee;
    }

    // Apply for leave
    public LeaveRequest applyLeave(
            String employeeId,
            LeaveType leaveType,
            int numberOfDays,
            String reason)
            throws EmployeeNotFoundException,
                   InsufficientLeaveException {

        if (numberOfDays <= 0) {
            throw new IllegalArgumentException(
                "Leave days must be greater than 0."
            );
        }

        if (leaveType == null) {
            throw new IllegalArgumentException(
                "Leave type is required."
            );
        }

        if (reason == null || reason.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Leave reason is required."
            );
        }

        Employee employee = findEmployeeByIdOrThrow(employeeId);

        int balance = employee.getAvailableLeaveBalance();

        if (numberOfDays > balance) {
            throw new InsufficientLeaveException(
                "Insufficient leave balance. Available: "
                + balance + ", Requested: " + numberOfDays
            );
        }

        String requestId = "LR" + nextRequestNumber++;

        LeaveRequest request = new LeaveRequest(
            requestId,
            employeeId,
            leaveType,
            numberOfDays,
            reason
        );

        leaveRequests.add(request);

        System.out.println("Leave request created successfully.");
        System.out.println("Request ID: " + requestId);
        System.out.println("Status: " + request.getStatus());

        return request;
    }

    // Find leave request by ID
    private LeaveRequest findLeaveRequest(String requestId) {

        for (LeaveRequest request : leaveRequests) {
            if (request.getRequestId().equals(requestId)) {
                return request;
            }
        }

        throw new IllegalArgumentException(
            "Leave request " + requestId + " does not exist."
        );
    }

    // Approve leave and deduct balance
    public void approveLeave(String requestId)
            throws EmployeeNotFoundException {

        LeaveRequest request = findLeaveRequest(requestId);

        if (request.getStatus() != LeaveStatus.PENDING) {
            System.out.println(
                "Only pending requests can be approved."
            );
            return;
        }

        Employee employee = findEmployeeByIdOrThrow(
            request.getEmployeeId()
        );

        int previousBalance =
            employee.getAvailableLeaveBalance();

        int days = request.getNumberOfDays();

        if (days <= 0) {
            System.out.println("Invalid leave days.");
            return;
        }

        if (days > previousBalance) {
            System.out.println(
                "Cannot approve: insufficient leave balance."
            );
            return;
        }

        employee.setAvailableLeaveBalance(
            previousBalance - days
        );

        request.setStatus(LeaveStatus.APPROVED);

        System.out.println("Leave Request Approved.");
        System.out.println(
            "Employee ID : " + employee.getEmployeeId()
        );
        System.out.println(
            "Previous Balance : " + previousBalance
        );
        System.out.println("Leave Used : " + days);
        System.out.println(
            "Remaining Leave : "
            + employee.getAvailableLeaveBalance()
        );
    }

    // Reject leave without deducting balance
    public void rejectLeave(String requestId) {

        LeaveRequest request = findLeaveRequest(requestId);

        if (request.getStatus() != LeaveStatus.PENDING) {
            System.out.println(
                "Only pending requests can be rejected."
            );
            return;
        }

        request.setStatus(LeaveStatus.REJECTED);

        System.out.println("Leave Request Rejected.");
        System.out.println(
            "Employee leave balance remains unchanged."
        );
    }

    // Display all leave requests
    public void displayAllLeaveRequests() {

        if (leaveRequests.isEmpty()) {
            System.out.println("No leave requests found.");
            return;
        }

        for (LeaveRequest request : leaveRequests) {
            request.displayDetails();
            System.out.println("--------------------");
        }
    }

    // Display selected employee's leave balance to be updated
    public void displayLeaveBalance(String employeeId)
            throws EmployeeNotFoundException {

        Employee employee = findEmployeeByIdOrThrow(employeeId);

        System.out.println(
            "Employee ID : " + employee.getEmployeeId()
        );
        System.out.println(
            "Available Leave Balance : "
            + employee.getAvailableLeaveBalance()
        );
    }
}
