package com.brau.employeemanagerservice.domain.service;

import com.brau.employeemanagerservice.application.web.dto.EmployeeRequestDTO;
import com.brau.employeemanagerservice.application.web.dto.EmployeeResponseDto;
import com.brau.employeemanagerservice.domain.exceptions.EmployeeAlreadyExistsException;
import com.brau.employeemanagerservice.domain.exceptions.InvalidCpfException;


public interface EmployeeService {

    void register(EmployeeRequestDTO employee) throws EmployeeAlreadyExistsException, InvalidCpfException;

    EmployeeResponseDto findByCpf(String cpf) throws InvalidCpfException;
}
