package com.brau.employeemanagerservice.application.web.dto;

import com.brau.employeemanagerservice.domain.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EmployeeResponseDto {
    private String name;
    private String cpf;
    private LocalDateTime dateOfBirth;
    private String phone;
    private String address;
    private BigDecimal remuneration;

    public EmployeeResponseDto(Employee employee) {
        this.name = employee.getName();
        this.cpf = employee.getCpf();
        this.dateOfBirth = employee.getDateOfBirth();
        this.phone = employee.getPhone();
        this.address = employee.getAddress();
        this.remuneration = employee.getRemuneration().setScale(2, RoundingMode.HALF_EVEN);
    }

}
