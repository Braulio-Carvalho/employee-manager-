package com.brau.employeemanagerservice.service;

import com.brau.employeemanagerservice.application.web.dto.EmployeInfoRemunerationDTO;
import com.brau.employeemanagerservice.domain.entities.Employee;
import com.brau.employeemanagerservice.domain.exceptions.InvalidCpfException;
import com.brau.employeemanagerservice.domain.service.Impl.RemunerationServiceImpl;
import com.brau.employeemanagerservice.resources.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RunWith(MockitoJUnitRunner.class)
public class RemunerationServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private RemunerationServiceImpl remunerationService;

    @Test
    public void testCalculateRemunerationSuccess() throws InvalidCpfException {
        String cpf = "12345678910";
        BigDecimal remunerationExpected = BigDecimal.valueOf(560.00).setScale(2, RoundingMode.HALF_UP);

        BigDecimal currentRemuneration = BigDecimal.valueOf(500.00);
        Employee employee = new Employee();
        employee.setCpf(cpf);
        employee.setRemuneration(currentRemuneration);
        Mockito.when(employeeRepository.findByCpf(cpf)).thenReturn(employee);

        EmployeInfoRemunerationDTO result = remunerationService.updateRemuneration(cpf);

        Assert.assertEquals(remunerationExpected, result.getNewRemuneration());
    }

    @Test
    public void testCalculateRemunerationSuccessWhenIs10Percent() throws InvalidCpfException {
        String cpf = "12345678910";
        BigDecimal currentRemuneration = BigDecimal.valueOf(800.01);
        Employee employee = new Employee();
        employee.setCpf(cpf);
        employee.setRemuneration(currentRemuneration);
        Mockito.when(employeeRepository.findByCpf(cpf)).thenReturn(employee);

        EmployeInfoRemunerationDTO result = remunerationService.updateRemuneration(cpf);

        Assert.assertEquals(BigDecimal.valueOf(880.01), result.getNewRemuneration());
    }

    @Test(expected = InvalidCpfException.class)
    public void testUpdateRemunerationThrowsInvalidCpfException() throws InvalidCpfException {
        String invalidCpf = "123456789";
        remunerationService.updateRemuneration(invalidCpf);
    }

}
