package com.brau.employeemanagerservice.application.web.controller;

import com.brau.employeemanagerservice.application.web.dto.EmployeInfoRemunerationDTO;
import com.brau.employeemanagerservice.application.web.dto.EmployeeRequestDTO;
import com.brau.employeemanagerservice.application.web.dto.EmployeeResponseDto;
import com.brau.employeemanagerservice.domain.exceptions.EmployeeAlreadyExistsException;
import com.brau.employeemanagerservice.domain.exceptions.InvalidCpfException;
import com.brau.employeemanagerservice.domain.service.EmployeeService;
import com.brau.employeemanagerservice.domain.service.RemunerationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@Tag(name = "Funcionários", description = "API para gerenciamento de funcionários, incluindo cadastro, consulta e reajuste salarial")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    RemunerationService remunerationService;

    @Operation(summary = "Cadastra um novo funcionário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionário cadastrado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Funcionário já existe"),
            @ApiResponse(responseCode = "400", description = "CPF inválido")
    })
    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @Parameter(description = "Dados do funcionário para cadastro")
            @RequestBody EmployeeRequestDTO employee) throws EmployeeAlreadyExistsException, InvalidCpfException {
        employeeService.register(employee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Ajusta a remuneração de um funcionário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Remuneração atualizada"),
            @ApiResponse(responseCode = "400", description = "CPF inválido")
    })
    @PutMapping("/remuneration/{cpf}")
    public ResponseEntity<EmployeInfoRemunerationDTO> updateEmployeeRemuneration(
            @Parameter(description = "CPF do funcionário para atualizar remuneração", example = "000.000.000-00")
            @PathVariable("cpf") String cpf) throws InvalidCpfException {
        EmployeInfoRemunerationDTO newRemuneration = remunerationService.updateRemuneration(cpf);
        return ResponseEntity.ok(newRemuneration);
    }

    @Operation(summary = "Lista todos os funcionários")
    @ApiResponse(responseCode = "200", description = "Lista de funcionários retornada")
    @GetMapping()
    public List<EmployeeResponseDto> listEmployees() {
        return employeeService.listEmployees();
    }

    @Operation(summary = "Busca funcionário pelo CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário encontrado"),
            @ApiResponse(responseCode = "400", description = "CPF inválido"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
    })
    @GetMapping("/{cpf}")
    public ResponseEntity<EmployeeResponseDto> getEmployee(
            @Parameter(description = "CPF do funcionário a ser buscado", example = "000.000.000-00")
            @PathVariable String cpf) throws InvalidCpfException {
        return ResponseEntity.ok(employeeService.findByCpf(cpf));
    }
}