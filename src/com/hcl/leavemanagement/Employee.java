
package com.hcl.leavemanagement;

public class Employee {

    private String employeeId;
    private String employeeName;
    private String department;
    private String email;
    private int availableLeaveBalance;

    // Default constructor
    public Employee() {
    }

    // Parameterized constructor
    public Employee(String employeeId, String employeeName,
                    String department, String email,
                    int availableLeaveBalance) {

        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.department = department;
        this.email = email;
        this.availableLeaveBalance = availableLeaveBalance;
    }

    // Getters and setters
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAvailableLeaveBalance() {
        return availableLeaveBalance;
    }

    public void setAvailableLeaveBalance(int availableLeaveBalance) {
        this.availableLeaveBalance = availableLeaveBalance;
    }

    // Display employee details
    public void displayDetails() {
        System.out.println("Employee ID : " + employeeId);
        System.out.println("Employee Name : " + employeeName);
        System.out.println("Department : " + department);
        System.out.println("Email : " + email);
        System.out.println("Leave Balance : " + availableLeaveBalance);
    }
}

