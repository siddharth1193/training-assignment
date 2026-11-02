
package com.hcl.leavemanagement;

public class InsufficientLeaveException extends Exception {

    private static final long serialVersionUID = 1L;

    public InsufficientLeaveException(String message) {
        super(message);
    }
}

