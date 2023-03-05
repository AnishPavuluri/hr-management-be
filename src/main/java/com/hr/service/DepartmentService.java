package com.hr.service;

import com.hr.dto.DepartmentDTO;

import java.util.List;

/**
 * Created by Anish on 02-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 02-03-2023
 */
public interface DepartmentService {

    List<DepartmentDTO> listAllDepartments();
    DepartmentDTO createDepartment(DepartmentDTO employeeDTO);

    DepartmentDTO updateDepartment(DepartmentDTO employeeDTO);
}
