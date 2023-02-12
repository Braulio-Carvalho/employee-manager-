package com.brau.employeemanagerservice.service;

import com.brau.employeemanagerservice.application.web.dto.EmployeInfoRemunerationDTO;
import com.brau.employeemanagerservice.application.web.dto.EmployeeRequestDTO;
import com.brau.employeemanagerservice.application.web.dto.EmployeeResponseDto;
import com.brau.employeemanagerservice.domain.entities.Employee;
import com.brau.employeemanagerservice.domain.exceptions.EmployeeAlreadyExistsException;
import com.brau.employeemanagerservice.domain.exceptions.InvalidCpfException;
import com.brau.employeemanagerservice.domain.service.Impl.EmployeeServiceImpl;
import com.brau.employeemanagerservice.resources.repository.EmployeeRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    private EmployeInfoRemunerationDTO employeInfoRemunerationDTO;
    private EmployeeRequestDTO employeeRequestDTO;


    @Test
    public void testRegister() throws InvalidCpfException, EmployeeAlreadyExistsException {
        // Dados de entrada
        EmployeeRequestDTO employeeRequestDTO = new EmployeeRequestDTO();
        employeeRequestDTO.setName("Fulano de Tal");
        employeeRequestDTO.setCpf("12345678901");
        employeeRequestDTO.setPhone("999999999");
        employeeRequestDTO.setAddress("Rua dos Testes, 123");
        employeeRequestDTO.setRemuneration(BigDecimal.valueOf(1000.00));

        // Mock da busca de CPF e telefone
        when(employeeRepository.findByCpfOrPhone(anyString(), anyString())).thenReturn(null);

        // Execução do método a ser testado
        employeeService.register(employeeRequestDTO);

        // Verificações
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    public void testListEmployees() {
        // Dados de entrada
        List<Employee> employeeList = new ArrayList<>();

        Employee employee1 = new Employee();
        employee1.setName("Fulano");
        employee1.setCpf("12345678901");
        employee1.setPhone("999999999");
        employee1.setAddress("Rua dos Testes");
        employee1.setRemuneration(BigDecimal.valueOf(1000.00));

        Employee employee2 = new Employee();
        employee2.setName("Ciclano");
        employee2.setCpf("98765432101");
        employee2.setPhone("888888888");
        employee2.setAddress("Rua das Verificações");
        employee2.setRemuneration(BigDecimal.valueOf(2000.00));


        employeeList.add(employee1);
        employeeList.add(employee2);



        // Mock da lista de funcionários
        when(employeeRepository.findAll()).thenReturn(employeeList);

        // Execução do método a ser testado
        List<EmployeeResponseDto> employeeResponseDtoList = employeeService.listEmployees();

        // Verificações
        assertEquals(2, employeeResponseDtoList.size());
        assertEquals("Fulano", employeeResponseDtoList.get(0).getName());
        assertEquals("12345678901", employeeResponseDtoList.get(0).getCpf());
        assertEquals("999999999", employeeResponseDtoList.get(0).getPhone());
        assertEquals("Rua dos Testes", employeeResponseDtoList.get(0).getAddress());
        assertEquals(BigDecimal.valueOf(1000.00).setScale(2, RoundingMode.HALF_UP), employeeResponseDtoList.get(0).getRemuneration());
        assertEquals("Ciclano", employeeResponseDtoList.get(1).getName());
        assertEquals("98765432101", employeeResponseDtoList.get(1).getCpf());
        assertEquals("888888888", employeeResponseDtoList.get(1).getPhone());
        assertEquals("Rua das Verificações", employeeResponseDtoList.get(1).getAddress());
        assertEquals(BigDecimal.valueOf(2000.00).setScale(2, RoundingMode.HALF_UP), employeeResponseDtoList.get(1).getRemuneration());
    }


}
