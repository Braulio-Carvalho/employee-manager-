package com.brau.employeemanagerservice.domain.service;

import com.brau.employeemanagerservice.application.web.dto.TaxResponseDto;
import com.brau.employeemanagerservice.domain.exceptions.TaxCalculateException;

public interface TaxService {
    TaxResponseDto calculateTax(String cpf) throws TaxCalculateException;
}
