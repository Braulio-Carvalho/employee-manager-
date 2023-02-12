package com.brau.employeemanagerservice.domain.exceptions;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAlreadyExistsException extends Exception {
    private List<String> errorMessages;

    public EmployeeAlreadyExistsException(String message) {
        super(message);
        this.errorMessages = new ArrayList<>();
    }
}