package com.brau.employeemanagerservice.domain.service.Impl;

import com.brau.employeemanagerservice.application.web.dto.TaxResponseDto;
import com.brau.employeemanagerservice.domain.constants.GlobalConstants;
import com.brau.employeemanagerservice.domain.entities.Employee;
import com.brau.employeemanagerservice.domain.exceptions.TaxCalculateException;
import com.brau.employeemanagerservice.domain.service.TaxService;
import com.brau.employeemanagerservice.resources.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class TaxServiceImpl implements TaxService {

    private static final BigDecimal LIMITE_ISENCAO = new BigDecimal("2000.00");
    private static final BigDecimal LIMITE_ALIQUOTA = new BigDecimal("4000.00");
    private static final BigDecimal ALIQUOTA_1 = new BigDecimal("0.08");
    private static final BigDecimal ALIQUOTA_2 = new BigDecimal("0.18");

    @Autowired
    EmployeeRepository employeeRepository;

    public TaxResponseDto calculateTax(String cpf) throws TaxCalculateException {
        try {
            Employee employee = employeeRepository.findByCpf(cpf);
            BigDecimal remuneration = employee.getRemuneration();
            BigDecimal incomeTax = calculateIncomeTax(remuneration);
            String message;

            if (incomeTax.compareTo(BigDecimal.ZERO) == 0) {
                message = "Isento";
            } else {
                message = "Imposto no valor de R$ " + incomeTax.setScale(2, RoundingMode.HALF_UP);
            }
            return new TaxResponseDto(cpf, message, incomeTax);
        } catch (Exception e) {
            throw new TaxCalculateException(GlobalConstants.THERE_WAS_A_PROBLEM_CALCULATING_THE_TAX);
        }
    }

    public BigDecimal calculateIncomeTax(BigDecimal salary) {
        if (salary.compareTo(LIMITE_ISENCAO) <= 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal tax = BigDecimal.ZERO;
        BigDecimal faixa2 = salary.subtract(LIMITE_ALIQUOTA);

        if (salary.compareTo(LIMITE_ISENCAO) > 0) {
            BigDecimal baseFaixa1 = salary.min(LIMITE_ALIQUOTA).subtract(LIMITE_ISENCAO);
            tax = tax.add(baseFaixa1.multiply(ALIQUOTA_1));
        }

        if (faixa2.compareTo(BigDecimal.ZERO) > 0) {
            tax = tax.add(faixa2.multiply(ALIQUOTA_2));
        }

        return tax.setScale(2, RoundingMode.HALF_UP);
    }

}

