package com.hr.controller;

import com.hr.dto.EmployeeDTO;
import com.hr.dto.HRAppRestCode;
import com.hr.dto.HRAppRestResponse;
import com.hr.service.EmployeeService;
import com.hr.util.HRAppRestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Anish on 02-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 02-03-2023
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseEntity<HRAppRestResponse> listEmployees() {
        HRAppRestResponse hrAppRestResponse = HRAppRestUtils.createResponse(HRAppRestCode.SUCCESS, employeeService.listAllEmployees(), "OK");
        return new ResponseEntity<>(hrAppRestResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity<HRAppRestResponse> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeDTO = employeeService.createEmployee(employeeDTO);
        HRAppRestResponse hrAppRestResponse = HRAppRestUtils.createResponse(HRAppRestCode.SUCCESS, employeeDTO, "employee created");
        return new ResponseEntity<>(hrAppRestResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<HRAppRestResponse> updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeDTO = employeeService.updateEmployee(employeeDTO);
        HRAppRestResponse hrAppRestResponse = HRAppRestUtils.createResponse(HRAppRestCode.SUCCESS, employeeDTO, "employee updated");
        return new ResponseEntity<>(hrAppRestResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<HRAppRestResponse> deleteEmployee(@RequestParam(name = "empId") Long empId) {
        employeeService.deleteEmployeeById(empId);
        HRAppRestResponse hrAppRestResponse = HRAppRestUtils.createResponse(HRAppRestCode.SUCCESS, "Success", "employee deleted");
        return new ResponseEntity<>(hrAppRestResponse, HttpStatus.OK);
    }

}
