package com.hr.service.impl;

import com.hr.controller.LeaveController;
import com.hr.dto.DepartmentDTO;
import com.hr.dto.LeaveTypeDTO;
import com.hr.entity.Department;
import com.hr.entity.LeaveType;
import com.hr.repository.LeaveTypeRepository;
import com.hr.service.LeaveTypeService;
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
public class LeaveTypeServiceImpl implements LeaveTypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeaveTypeServiceImpl.class);
    @Autowired
    private LeaveTypeRepository leaveTypeRepository;

    @Override
    public List<LeaveTypeDTO> listAllLeaveTypes() {
        List<LeaveType> departmentList = leaveTypeRepository.findAll();
        LOGGER.info("departmentList.size(): "+departmentList.size());
        List<LeaveTypeDTO> leaveTypeDTOS = departmentList.stream().map(department1 -> {
            LeaveTypeDTO departmentDTO = new LeaveTypeDTO();
            BeanUtils.copyProperties(department1, departmentDTO);
            return departmentDTO;
        }).collect(Collectors.toList());
        return leaveTypeDTOS;
    }

    @Override
    public LeaveTypeDTO createLeaveType(LeaveTypeDTO leaveTypeDTO) {
        LeaveType leaveType = new LeaveType();
        BeanUtils.copyProperties(leaveTypeDTO, leaveType);
        leaveType = leaveTypeRepository.save(leaveType);
        leaveTypeDTO.setId(leaveType.getId());
        return leaveTypeDTO;
    }

    @Override
    public LeaveTypeDTO updateLeaveType(LeaveTypeDTO departmentDTO) {
        LeaveType leaveType = leaveTypeRepository.findById(departmentDTO.getId()).orElse(null);
        if (leaveType == null) {
            return createLeaveType(departmentDTO);
        } else {
            BeanUtils.copyProperties(departmentDTO, leaveType);
            leaveTypeRepository.save(leaveType);
        }
        return departmentDTO;
    }
}
