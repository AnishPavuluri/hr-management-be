package com.hr.service;

import com.hr.dto.LeaveDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Anish on 03-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 03-03-2023
 */
public interface LeaveService {

    List<LeaveDTO> listAllLeaves(Date form, Date to, Long empId);
    LeaveDTO createLeave(LeaveDTO employeeDTO);

    LeaveDTO updateLeave(LeaveDTO employeeDTO);

    Map<String, Long> totalLeavesByLeaveType();

    void deleteLeaveById(Long leaveId);
}
