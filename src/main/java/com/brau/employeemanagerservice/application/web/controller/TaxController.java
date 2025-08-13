package com.brau.employeemanagerservice.application.web.controller;

import com.brau.employeemanagerservice.application.web.dto.TaxResponseDto;
import com.brau.employeemanagerservice.domain.exceptions.TaxCalculateException;
import com.brau.employeemanagerservice.domain.service.TaxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tax")
@Tag(name = "Imposto", description = "API para cálculo do imposto de renda sobre a remuneração dos funcionários")
public class TaxController {

    @Autowired
    TaxService taxService;

    @Operation(summary = "Calcula o imposto para um funcionário pelo CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imposto calculado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaxResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "CPF inválido ou erro no cálculo do imposto",
                    content = @Content)
    })
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
