package com.hr.controller;

import com.hr.dto.ExpenseClaimDTO;
import com.hr.dto.HRAppRestCode;
import com.hr.dto.HRAppRestResponse;
import com.hr.dto.LeaveDTO;
import com.hr.service.ExpenseClaimService;
import com.hr.util.HRAppRestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Anish on 03-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 03-03-2023
 */
@RestController
@RequestMapping("/expense")
public class ExpenseClaimController {

    @Autowired
    private ExpenseClaimService expenseClaimService;

    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseEntity<HRAppRestResponse> listExpenseClaims(
        @RequestParam(name = "empId", required = false) Long empId
    ) {
        HRAppRestResponse hrAppRestResponse = HRAppRestUtils.createResponse(HRAppRestCode.SUCCESS,
                expenseClaimService.listExpenseClaim(empId), "OK");
        return new ResponseEntity<>(hrAppRestResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity<HRAppRestResponse> createExpenseClaim(@RequestBody ExpenseClaimDTO expenseClaimDTO) {
        expenseClaimDTO.setStatus("Submitted");
        expenseClaimDTO = expenseClaimService.createExpenseClaim(expenseClaimDTO);
        HRAppRestResponse hrAppRestResponse = HRAppRestUtils.createResponse(HRAppRestCode.SUCCESS, expenseClaimDTO, "employee created");
        return new ResponseEntity<>(hrAppRestResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/claimByType")
    @ResponseBody
    public ResponseEntity<HRAppRestResponse> expenseClaimByExpenseType() {
        HRAppRestResponse hrAppRestResponse = HRAppRestUtils.createResponse(HRAppRestCode.SUCCESS,
                expenseClaimService.totalExpenseClaimByExpenseType(), "OK");
        return new ResponseEntity<>(hrAppRestResponse, HttpStatus.OK);
    }

}
