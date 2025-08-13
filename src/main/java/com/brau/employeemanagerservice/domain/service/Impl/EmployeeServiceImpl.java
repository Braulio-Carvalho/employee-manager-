package com.brau.employeemanagerservice.domain.service.Impl;

import com.brau.employeemanagerservice.application.web.dto.EmployeeRequestDTO;
import com.brau.employeemanagerservice.application.web.dto.EmployeeResponseDto;
import com.brau.employeemanagerservice.domain.entities.Employee;
import com.brau.employeemanagerservice.domain.exceptions.EmployeeAlreadyExistsException;
import com.brau.employeemanagerservice.domain.exceptions.EmployeeNotFoundException;
import com.brau.employeemanagerservice.domain.exceptions.InvalidCpfException;
import com.brau.employeemanagerservice.domain.service.EmployeeService;
import com.brau.employeemanagerservice.resources.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.brau.employeemanagerservice.domain.constants.GlobalConstants.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void register(EmployeeRequestDTO employee) throws EmployeeAlreadyExistsException, InvalidCpfException {
        if (employee == null) {
            throw new InvalidCpfException(EMPLOYEE_CANNOT_BE_NULL);
        }
        String cpf = validateCpf(employee.getCpf());
        checkExistingEmployee(cpf, employee.getPhone());
        employeeRepository.save(employee.toEntity());
    }

    @Override
    public List<EmployeeResponseDto> listEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDto findByCpf(String cpf) throws InvalidCpfException {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new InvalidCpfException(CPF_CANNOT_BE_NULL_OR_EMPTY);
        }

        Employee employee = employeeRepository.findByCpf(cpf);
        if (employee == null) {
            throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_FOR_CPF + cpf);
        }
        return new EmployeeResponseDto(employee);
    }

    private void checkExistingEmployee(String cpf, String phone) throws EmployeeAlreadyExistsException {
        List<String> errors = new ArrayList<>();
        Employee existingEmployee = employeeRepository.findByCpfOrPhone(cpf, phone);
        if (existingEmployee != null) {
            if (existingEmployee.getCpf().equals(cpf)) {
                errors.add(EMPLOYEE_WITH_CPF + cpf + ALREADY_EXISTS);
            }
            if (existingEmployee.getPhone().equals(phone)) {
                errors.add(EMPLOYEE_WITH_PHONE + phone + ALREADY_EXISTS);
            }
        }
        if (!errors.isEmpty()) {
            throw new EmployeeAlreadyExistsException(errors.toString());
        }
    }

    private String validateCpf(String cpfString) throws InvalidCpfException {
        if (!cpfString.matches(CPF_VALIDATION_REGEX)) {
            throw new InvalidCpfException(INVALID_CPF);
        }
        return cpfString;
    }


}
