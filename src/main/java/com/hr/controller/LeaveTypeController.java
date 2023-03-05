package com.hr.controller;

import com.hr.dto.DepartmentDTO;
import com.hr.dto.HRAppRestCode;
import com.hr.dto.HRAppRestResponse;
import com.hr.dto.LeaveTypeDTO;
import com.hr.service.LeaveTypeService;
import com.hr.util.HRAppRestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Anish on 03-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 03-03-2023
 */
@RestController
@RequestMapping("/leaveType")
public class LeaveTypeController {

    @Autowired
    private LeaveTypeService leaveTypeService;

    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseEntity<HRAppRestResponse> listLeaveTypes() {
        HRAppRestResponse hrAppRestResponse = HRAppRestUtils.createResponse(HRAppRestCode.SUCCESS, leaveTypeService.listAllLeaveTypes(), "OK");
        return new ResponseEntity<>(hrAppRestResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity<HRAppRestResponse> createLeaveType(@RequestBody LeaveTypeDTO leaveTypeDTO) {
        leaveTypeDTO = leaveTypeService.createLeaveType(leaveTypeDTO);
        HRAppRestResponse hrAppRestResponse = HRAppRestUtils.createResponse(HRAppRestCode.SUCCESS, leaveTypeDTO, "employee created");
        return new ResponseEntity<>(hrAppRestResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<HRAppRestResponse> updateLeaveType(@RequestBody LeaveTypeDTO leaveTypeDTO) {
        leaveTypeDTO = leaveTypeService.updateLeaveType(leaveTypeDTO);
        HRAppRestResponse hrAppRestResponse = HRAppRestUtils.createResponse(HRAppRestCode.SUCCESS, leaveTypeDTO, "employee updated");
        return new ResponseEntity<>(hrAppRestResponse, HttpStatus.OK);
    }

}
