package com.hr.service.impl;

import com.hr.controller.LeaveController;
import com.hr.dto.EmployeeDTO;
import com.hr.entity.Department;
import com.hr.entity.Employee;
import com.hr.repository.DepartmentRepository;
import com.hr.repository.EmployeeRepository;
import com.hr.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Anish on 02-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 02-03-2023
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public EmployeeDTO findEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (null != employee) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            BeanUtils.copyProperties(employee, employeeDTO);
            Department department = employee.getDepartment();
            if (null != department) {
                employeeDTO.setDepartmentName(department.getName());
            }
            return employeeDTO;
        }
        return null;
    }

    @Override
    public List<EmployeeDTO> listAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        LOGGER.info("employeeList.size(): "+employeeList.size());
        List<EmployeeDTO> employeeDTOList = employeeList.stream().map(employee -> {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            BeanUtils.copyProperties(employee, employeeDTO);
            Department department = employee.getDepartment();
            if (null != department) {
                employeeDTO.setDepartmentName(department.getName());
                employeeDTO.setDepartmentId(department.getId());
            }
            return employeeDTO;
        }).collect(Collectors.toList());
        return employeeDTOList;
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employee.setDepartment(departmentRepository.findById(employeeDTO.getDepartmentId()).orElse(null));
        employee = employeeRepository.save(employee);
        employeeDTO.setId(employee.getId());
        return employeeDTO;
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(employeeDTO.getId()).orElse(null);
        if (employee == null) {
            return createEmployee(employeeDTO);
        } else {
            BeanUtils.copyProperties(employeeDTO, employee);
            employee.setDepartment(departmentRepository.findById(employeeDTO.getDepartmentId()).orElse(null));
            employeeRepository.save(employee);
        }
        return employeeDTO;
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
