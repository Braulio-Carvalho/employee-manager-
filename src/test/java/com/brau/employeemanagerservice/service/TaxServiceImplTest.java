package com.brau.employeemanagerservice.service;

import com.brau.employeemanagerservice.application.web.dto.TaxResponseDto;
import com.brau.employeemanagerservice.domain.entities.Employee;
import com.brau.employeemanagerservice.domain.exceptions.TaxCalculateException;
import com.brau.employeemanagerservice.domain.service.Impl.TaxServiceImpl;
import com.brau.employeemanagerservice.resources.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaxServiceImplTest {

    @InjectMocks
    private TaxServiceImpl taxService;

    @Mock
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @Before
    public void setUp() {
        employee = new Employee();
        employee.setRemuneration(new BigDecimal(5000));
        employee.setCpf("111.111.111-11");
    }

    @Test
    public void testCalculateTax() throws TaxCalculateException {
        when(employeeRepository.findByCpf("111.111.111-11")).thenReturn(employee);
        BigDecimal tax;
        tax = taxService.calculateTax("111.111.111-11").getValue();
        assertEquals(new BigDecimal("420.00"), tax);
    }

    @Test
    public void calculateTaxWhenEmployeeIsNotFoundShouldThrowException() {
        when(employeeRepository.findByCpf("12345678900"))
                .thenReturn(null);

        assertThrows(TaxCalculateException.class, () -> taxService.calculateTax("12345678900"));
    }

    @Test
    public void calculateTaxWhenEmployeeIsFoundShouldReturnCorrectResult() throws TaxCalculateException {
        Employee employee = new Employee();
        employee.setCpf("12345678900");
        employee.setRemuneration(new BigDecimal("3000.00"));

        when(employeeRepository.findByCpf("12345678900"))
                .thenReturn(employee);

        TaxResponseDto result = taxService.calculateTax("12345678900");

                assertEquals("12345678900", result.getCpf());
        assertEquals("Imposto no valor de R$ 80.00", result.getMessage());
        assertEquals(new BigDecimal("80.00"), result.getValue());
    }

    @Test
    public void calculateIncomeTaxWhenSalaryIsLowerThanLimitShouldReturnZero() {
        BigDecimal salary = new BigDecimal("1500.00");

        BigDecimal result = taxService.calculateIncomeTax(salary);

        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void calculateIncomeTaxWhenSalaryIsHigherThanLimitShouldReturnCorrectResult() {
        BigDecimal salary = new BigDecimal("3000.00");

        BigDecimal result = taxService.calculateIncomeTax(salary);

        assertEquals(new BigDecimal("80.00"), result);
    }
}

