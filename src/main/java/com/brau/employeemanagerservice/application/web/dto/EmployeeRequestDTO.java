package com.brau.employeemanagerservice.application.web.dto;

import com.brau.employeemanagerservice.domain.entities.Employee;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class EmployeeRequestDTO {
    private String name;
    private String cpf;
    private LocalDateTime dateOfBirth;
    private String phone;
    private String address;
    private BigDecimal remuneration;

    public Employee toEntity() {
        Employee employee = new Employee();

        employee.setName(this.name);
        employee.setCpf(this.cpf);
        employee.setDateOfBirth(this.dateOfBirth);
        employee.setPhone(this.phone);
        employee.setAddress(this.address);
        employee.setRemuneration(this.remuneration);

        return employee;
    }
}
