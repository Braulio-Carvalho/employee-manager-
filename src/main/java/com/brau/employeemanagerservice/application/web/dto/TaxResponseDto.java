package com.brau.employeemanagerservice.application.web.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TaxResponseDto {
    private String cpf;
    private String message;
    private BigDecimal value;

    public TaxResponseDto(String cpf, String message, BigDecimal value) {
        this.cpf = cpf;
        this.message = message;
        this.value = value;
    }
}