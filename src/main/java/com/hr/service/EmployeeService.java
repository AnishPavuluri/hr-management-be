package com.hr.service;

import com.hr.dto.EmployeeDTO;

import java.util.List;

/**
 * Created by Anish on 02-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 02-03-2023
 */
public interface EmployeeService {

    EmployeeDTO findEmployeeById(Long id);
    List<EmployeeDTO> listAllEmployees();
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);

    void deleteEmployeeById(Long id);
}
