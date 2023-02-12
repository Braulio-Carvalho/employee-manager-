package com.brau.employeemanagerservice.domain.exceptions;

public class EmployeeInformationException extends Exception {
    private String message;

    public EmployeeInformationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
