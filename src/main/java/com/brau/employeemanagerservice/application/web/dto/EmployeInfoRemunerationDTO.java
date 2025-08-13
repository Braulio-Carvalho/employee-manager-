package com.brau.employeemanagerservice.application.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeInfoRemunerationDTO {
    private String cpf;
    private BigDecimal readjustmentValueObtained;
    private BigDecimal newRemuneration;
    private int percentageOfReadjustmentObtained;
}

