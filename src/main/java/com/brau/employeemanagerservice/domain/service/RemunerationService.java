package com.brau.employeemanagerservice.domain.service;

import com.brau.employeemanagerservice.application.web.dto.EmployeInfoRemunerationDTO;
import com.brau.employeemanagerservice.domain.exceptions.InvalidCpfException;

public interface RemunerationService {

    EmployeInfoRemunerationDTO updateRemuneration(String cpf) throws InvalidCpfException;
}
