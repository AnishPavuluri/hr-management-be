package com.hr.controller;

import com.hr.dto.EmployeeDTO;
import com.hr.dto.HRAppRestCode;
import com.hr.dto.HRAppRestResponse;
import com.hr.dto.LeaveDTO;
import com.hr.service.LeaveService;
import com.hr.util.HRAppRestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Period;
import java.util.Date;

/**
 * Created by Anish on 03-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 03-03-2023
 */
@RestController
@RequestMapping("/leave")
public class LeaveController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LeaveController.class);


    @Autowired
    private LeaveService leaveService;

    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseEntity<HRAppRestResponse> listLeaves(@RequestParam(name = "from", required = false)
                                                               @DateTimeFormat(pattern = "dd-MM-yyyy") Date from,
        @RequestParam(name = "to", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date to,
        @RequestParam(name = "empId", required = false) Long empId
    ) {
        HRAppRestResponse hrAppRestResponse = HRAppRestUtils.createResponse(HRAppRestCode.SUCCESS,
                leaveService.listAllLeaves(from, to, empId), "OK");
        return new ResponseEntity<>(hrAppRestResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity<HRAppRestResponse> createLeave(@RequestBody LeaveDTO leaveDTO) {
        HRAppRestResponse hrAppRestResponse;
        Period period = Period.between(HRAppRestUtils.convertToLocalDate(leaveDTO.getFrom()), HRAppRestUtils.convertToLocalDate(leaveDTO.getTo()));
        long daysDiff = Math.abs(period.getDays());
        LOGGER.info("daysDiff-->"+daysDiff);
        leaveDTO.setNumberOfDays((int) (daysDiff+1));
        LOGGER.info("request daysDiff-->"+leaveDTO.getNumberOfDays());
        /*if ((daysDiff+1) != leaveDTO.getNumberOfDays()) {
            hrAppRestResponse = HRAppRestUtils.createResponse(HRAppRestCode.FAILED, leaveDTO, "employee created");
            return new ResponseEntity<>(hrAppRestResponse, HttpStatus.BAD_REQUEST);
        }*/
        leaveDTO = leaveService.createLeave(leaveDTO);
        hrAppRestResponse = HRAppRestUtils.createResponse(HRAppRestCode.SUCCESS, leaveDTO, "employee created");
        return new ResponseEntity<>(hrAppRestResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<HRAppRestResponse> updateLeave(@RequestBody LeaveDTO leaveDTO) {
        leaveDTO = leaveService.updateLeave(leaveDTO);
        HRAppRestResponse hrAppRestResponse = HRAppRestUtils.createResponse(HRAppRestCode.SUCCESS, leaveDTO, "employee updated");
        return new ResponseEntity<>(hrAppRestResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/byType")
    @ResponseBody
    public ResponseEntity<HRAppRestResponse> leavesByLeaveType() {
        HRAppRestResponse hrAppRestResponse = HRAppRestUtils.createResponse(HRAppRestCode.SUCCESS,
                leaveService.totalLeavesByLeaveType(), "OK");
        return new ResponseEntity<>(hrAppRestResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<HRAppRestResponse> deleteByLeaveId(@RequestParam(name = "leaveId") Long leaveId) {
        leaveService.deleteLeaveById(leaveId);
        HRAppRestResponse hrAppRestResponse = HRAppRestUtils.createResponse(HRAppRestCode.SUCCESS,
                "SUCCESS", "OK");
        return new ResponseEntity<>(hrAppRestResponse, HttpStatus.OK);
    }
}
