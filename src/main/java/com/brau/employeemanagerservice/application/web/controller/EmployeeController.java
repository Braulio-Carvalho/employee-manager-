package com.brau.employeemanagerservice.application.web.controller;

import com.brau.employeemanagerservice.application.web.dto.EmployeInfoRemunerationDTO;
import com.brau.employeemanagerservice.application.web.dto.EmployeeRequestDTO;
import com.brau.employeemanagerservice.application.web.dto.EmployeeResponseDto;
import com.brau.employeemanagerservice.domain.exceptions.EmployeeAlreadyExistsException;
import com.brau.employeemanagerservice.domain.exceptions.InvalidCpfException;
import com.brau.employeemanagerservice.domain.service.Impl.EmployeeServiceImpl;
import com.brau.employeemanagerservice.domain.service.RemunerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    RemunerationService remunerationService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody EmployeeRequestDTO employee) throws EmployeeAlreadyExistsException, InvalidCpfException {
        employeeServiceImpl.register(employee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/remuneration/{cpf}")
    public ResponseEntity<EmployeInfoRemunerationDTO> updeteEmployeeRemuneration(@PathVariable("cpf") String cpf) throws InvalidCpfException {
        EmployeInfoRemunerationDTO newRemuneration = null;
        try {
            newRemuneration = remunerationService.updateRemuneration(cpf);
        } catch (InvalidCpfException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(newRemuneration);
    }

    @GetMapping()
    public List<EmployeeResponseDto> listEmployees() {
        return employeeServiceImpl.listEmployees();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<EmployeeResponseDto> getEmployee(@PathVariable String cpf) throws InvalidCpfException {
        return ResponseEntity.ok(employeeServiceImpl.findByCpf(cpf));
    }
}
