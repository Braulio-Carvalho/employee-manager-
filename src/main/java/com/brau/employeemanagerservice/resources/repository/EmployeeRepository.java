package com.brau.employeemanagerservice.resources.repository;

import com.brau.employeemanagerservice.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Employee findByCpf(String cpf);

    Employee findByCpfOrPhone(String cpf, String phone);
}
