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

import static com.brau.employeemanagerservice.domain.constants.GlobalConstants.IMPOSTO;
import static com.brau.employeemanagerservice.domain.constants.GlobalConstants.ISENTO;

@Service
public class TaxServiceImpl implements TaxService {

    private static final BigDecimal LIMITE_ISENCAO = new BigDecimal("2000.00");
    private static final BigDecimal ALIQUOTA_1 = new BigDecimal("0.08");
    private static final BigDecimal ALIQUOTA_2 = new BigDecimal("0.18");

    @Autowired
    EmployeeRepository employeeRepository;


    public TaxResponseDto calculateTax(String cpf) throws TaxCalculateException {
        try {
            Employee employee = employeeRepository.findByCpf(cpf);
            BigDecimal remuneration = employee.getRemuneration();

            BigDecimal incomeTax = calculateIncomeTax(remuneration);

            String message = incomeTax.compareTo(BigDecimal.ZERO) == 0 ? ISENTO : IMPOSTO;

            return new TaxResponseDto(cpf, message, incomeTax);
        } catch (Exception e) {
            throw new TaxCalculateException(GlobalConstants.THERE_WAS_A_PROBLEM_CALCULATING_THE_TAX);
        }
    }
    public BigDecimal calculateIncomeTax(BigDecimal salary) {
        BigDecimal taxableAmount = salary.subtract(LIMITE_ISENCAO);
        if (taxableAmount.compareTo(BigDecimal.ZERO) < 0) {
            taxableAmount = BigDecimal.ZERO;
        }
        BigDecimal taxAmount1 = taxableAmount.compareTo(BigDecimal.ZERO) > 0
                ? taxableAmount.multiply(ALIQUOTA_1).setScale(2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;
        BigDecimal taxAmount2 = taxableAmount.subtract(LIMITE_ISENCAO).compareTo(BigDecimal.ZERO) > 0
                ? taxableAmount.subtract(LIMITE_ISENCAO).multiply(ALIQUOTA_2).setScale(2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;
        return taxAmount1.add(taxAmount2);
    }

}

