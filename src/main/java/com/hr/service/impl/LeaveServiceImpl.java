package com.hr.service.impl;

import com.hr.controller.LeaveController;
import com.hr.dto.LeaveDTO;
import com.hr.dto.LeaveTypeDTO;
import com.hr.entity.Employee;
import com.hr.entity.Leave;
import com.hr.entity.LeaveType;
import com.hr.repository.EmployeeRepository;
import com.hr.repository.LeaveRepository;
import com.hr.repository.LeaveTypeRepository;
import com.hr.service.EmployeeService;
import com.hr.service.LeaveService;
import com.hr.util.HRAppRestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Anish on 02-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 02-03-2023
 */
@Service
public class LeaveServiceImpl implements LeaveService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LeaveServiceImpl.class);
    @Autowired
    private LeaveRepository leaveRepository;
    @Autowired
    private LeaveTypeRepository leaveTypeRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;


    @Override
    public List<LeaveDTO> listAllLeaves(Date form, Date to, Long empId) {

        List<Leave> leaveList = leaveRepository.findLeaveByFromToEmpId(form, to, empId);
        LOGGER.info("employeeList.size(): "+leaveList.size());
        List<LeaveDTO> employeeDTOList = leaveList.stream().map(leave -> {
            LeaveDTO leaveDTO = new LeaveDTO();
            BeanUtils.copyProperties(leave, leaveDTO);
            LeaveType leaveType = leave.getLeaveType();
            if (null != leaveType) {
                LeaveTypeDTO leaveTypeDTO = new LeaveTypeDTO();
                BeanUtils.copyProperties(leaveType, leaveTypeDTO);
                leaveDTO.setLeaveTypeDTO(leaveTypeDTO);
            }
            Employee employee = leave.getEmployee();
            if (null != employee) {
                leaveDTO.setEmployeeDTO(employeeService.findEmployeeById(employee.getId()));
            }
            return leaveDTO;
        }).collect(Collectors.toList());
        return employeeDTOList;
    }

    @Override
    public LeaveDTO createLeave(LeaveDTO leaveDTO) {
        Leave leave = new Leave();
        LOGGER.info("leaveDTO.getFrom(): "+leaveDTO.getFrom());
        LOGGER.info("leaveDTO.getTo(): "+leaveDTO.getTo());
        BeanUtils.copyProperties(leaveDTO, leave);
        LOGGER.info("leave.getFrom(): "+leave.getFrom());
        LOGGER.info("leave.getTo(): "+leave.getTo());
        if (null != leaveDTO.getLeaveTypeDTO()) {
            leave.setLeaveType(leaveTypeRepository.findById(leaveDTO.getLeaveTypeDTO().getId()).orElse(null));
        }
        if (null != leaveDTO.getEmployeeDTO()) {
            leave.setEmployee(employeeRepository.findById(leaveDTO.getEmployeeDTO().getId()).orElse(null));
        }
        leave = leaveRepository.save(leave);
        leaveDTO.setId(leave.getId());
        return leaveDTO;
    }

    @Override
    public LeaveDTO updateLeave(LeaveDTO leaveDTO) {
        Leave leave = leaveRepository.findById(leaveDTO.getId()).orElse(null);
        if (leave == null) {
            return createLeave(leaveDTO);
        } else {
            BeanUtils.copyProperties(leaveDTO, leave);
            if (null != leaveDTO.getLeaveTypeDTO()) {
                leave.setLeaveType(leaveTypeRepository.findById(leaveDTO.getLeaveTypeDTO().getId()).orElse(null));
            }
            leaveRepository.save(leave);
        }
        return leaveDTO;
    }

    @Override
    public Map<String, Long> totalLeavesByLeaveType() {
        Map<String, Long> map = new LinkedHashMap<>();
        List<Object[]> expByTypeList = leaveRepository.totalLeavesByType();
        expByTypeList.forEach(objArr -> {
            map.put((String) objArr[0], (Long) objArr[1]);
        });
        return map;
    }

    @Override
    public void deleteLeaveById(Long leaveId) {
        leaveRepository.deleteById(leaveId);
    }
}
