package com.brau.employeemanagerservice.domain.service.Impl;

import com.brau.employeemanagerservice.application.web.dto.EmployeInfoRemunerationDTO;
import com.brau.employeemanagerservice.domain.entities.Employee;
import com.brau.employeemanagerservice.domain.enums.RemunerationRange;
import com.brau.employeemanagerservice.domain.exceptions.InvalidCpfException;
import com.brau.employeemanagerservice.domain.service.RemunerationService;
import com.brau.employeemanagerservice.resources.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.brau.employeemanagerservice.domain.constants.GlobalConstants.ERROR_UPDATING_EMPLOYEE_SALARY;
import static com.brau.employeemanagerservice.domain.constants.GlobalConstants.INVALID_CPF;

@Service
public class RemunerationServiceImpl implements RemunerationService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public RemunerationServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public EmployeInfoRemunerationDTO updateRemuneration(String cpf) throws InvalidCpfException {
        try {
            Employee employee = employeeRepository.findByCpf(cpf);
            BigDecimal oldRemuneration = employee.getRemuneration();
            RemunerationRange remunerationRange = RemunerationRange.getPorSalario(oldRemuneration);
            assert remunerationRange != null;
            BigDecimal percentual = BigDecimal.valueOf(remunerationRange.getPercentual());
            BigDecimal newRemuneration = oldRemuneration.add(oldRemuneration.multiply(percentual).setScale(2, RoundingMode.HALF_EVEN));
            employee.setRemuneration(newRemuneration);
            employeeRepository.save(employee);
            return new EmployeInfoRemunerationDTO(
                    employee.getCpf(),
                    newRemuneration.subtract(oldRemuneration).setScale(2, RoundingMode.HALF_EVEN),
                    newRemuneration.setScale(2, RoundingMode.HALF_EVEN),
                    percentual.multiply(BigDecimal.valueOf(100)).intValue()
            );
        } catch (NullPointerException e) {
            throw new InvalidCpfException(INVALID_CPF);
        } catch (Exception e) {
            throw new InvalidCpfException(ERROR_UPDATING_EMPLOYEE_SALARY);
        }
    }
}
