package com.brau.employeemanagerservice.domain.exceptions;

import java.util.ArrayList;
import java.util.List;

public class TaxCalculateException extends Exception {
    private List<String> errorMessages;

    public TaxCalculateException(String message) {
        super(message);
        this.errorMessages = new ArrayList<>();
    }
}