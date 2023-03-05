package com.hr.service;

import com.hr.dto.LeaveTypeDTO;

import java.util.List;

/**
 * Created by Anish on 02-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 02-03-2023
 */
public interface LeaveTypeService {

    List<LeaveTypeDTO> listAllLeaveTypes();

    LeaveTypeDTO createLeaveType(LeaveTypeDTO leaveTypeDTO);

    LeaveTypeDTO updateLeaveType(LeaveTypeDTO leaveTypeDTO);
}
