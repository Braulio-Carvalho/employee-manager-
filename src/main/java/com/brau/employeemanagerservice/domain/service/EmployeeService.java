package com.brau.employeemanagerservice.domain.service;

import com.brau.employeemanagerservice.application.web.dto.EmployeeRequestDTO;
import com.brau.employeemanagerservice.application.web.dto.EmployeeResponseDto;
import com.brau.employeemanagerservice.domain.exceptions.EmployeeAlreadyExistsException;
import com.brau.employeemanagerservice.domain.exceptions.InvalidCpfException;

import java.util.List;


public interface EmployeeService {

    void register(EmployeeRequestDTO employee) throws EmployeeAlreadyExistsException, InvalidCpfException;

    List<EmployeeResponseDto> listEmployees();

    EmployeeResponseDto findByCpf(String cpf) throws InvalidCpfException;
}
