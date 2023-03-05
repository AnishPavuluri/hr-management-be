package com.hr.service.impl;

import com.hr.controller.LeaveController;
import com.hr.dto.DepartmentDTO;
import com.hr.entity.Department;
import com.hr.repository.DepartmentRepository;
import com.hr.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Anish on 03-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 03-03-2023
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentDTO> listAllDepartments() {
        List<Department> departmentList = departmentRepository.findAll();
        LOGGER.info("departmentList.size(): "+departmentList.size());
        List<DepartmentDTO> departmentDTOList = departmentList.stream().map(department1 -> {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            BeanUtils.copyProperties(department1, departmentDTO);
            return departmentDTO;
        }).collect(Collectors.toList());
        return departmentDTOList;
    }

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        BeanUtils.copyProperties(departmentDTO, department);
        department = departmentRepository.save(department);
        departmentDTO.setId(department.getId());
        return departmentDTO;
    }

    @Override
    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(departmentDTO.getId()).orElse(null);
        if (department == null) {
            return createDepartment(departmentDTO);
        } else {
            BeanUtils.copyProperties(departmentDTO, department);
            departmentRepository.save(department);
        }
        return departmentDTO;
    }
}
