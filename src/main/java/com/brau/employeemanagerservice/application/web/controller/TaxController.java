package com.brau.employeemanagerservice.application.web.controller;

import com.brau.employeemanagerservice.application.web.dto.TaxResponseDto;
import com.brau.employeemanagerservice.domain.exceptions.TaxCalculateException;
import com.brau.employeemanagerservice.domain.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tax")
public class TaxController {

    @Autowired
    TaxService taxService;

    @GetMapping("/{cpf}")
    public ResponseEntity<TaxResponseDto> getTax(@PathVariable String cpf) {
        try {
            TaxResponseDto taxResponse = taxService.calculateTax(cpf);
            return ResponseEntity.ok().body(taxResponse);
        } catch (IllegalArgumentException | TaxCalculateException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

