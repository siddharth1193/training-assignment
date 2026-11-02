
package com.hcl.leavemanagement;

public class LeaveRequest {

    private String requestId;
    private String employeeId;
    private LeaveType leaveType;
    private int numberOfDays;
    private String reason;
    private LeaveStatus status;

    // No-argument constructor
    public LeaveRequest() {
    }

    // Parameterized constructor
    public LeaveRequest(String requestId,
                        String employeeId,
                        LeaveType leaveType,
                        int numberOfDays,
                        String reason) {

        this.requestId = requestId;
        this.employeeId = employeeId;
        this.leaveType = leaveType;
        this.numberOfDays = numberOfDays;
        this.reason = reason;
        this.status = LeaveStatus.PENDING;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }

    public void displayDetails() {
        System.out.println("Request ID : " + requestId);
        System.out.println("Employee ID : " + employeeId);
        System.out.println("Leave Type : " + leaveType);
        System.out.println("Days : " + numberOfDays);
        System.out.println("Reason : " + reason);
        System.out.println("Status : " + status);
    }
}
