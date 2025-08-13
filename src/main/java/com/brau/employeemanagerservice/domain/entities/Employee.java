package com.brau.employeemanagerservice.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String name;
    @Column
    private String cpf;
    @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth;
    @Column
    private String phone;
    @Column
    private String address;
    @Column
    private BigDecimal remuneration;

    public Employee(String name, String cpf, LocalDateTime dateOfBirth, String phone, String address, BigDecimal remuneration) {
        this.name = name;
        this.cpf = cpf;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.address = address;
        this.remuneration = remuneration;
    }
}
