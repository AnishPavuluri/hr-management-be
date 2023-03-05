package com.hr.controller;

import com.hr.dto.DepartmentDTO;
import com.hr.dto.HRAppRestCode;
import com.hr.dto.HRAppRestResponse;
import com.hr.service.DepartmentService;
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
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseEntity<HRAppRestResponse> listDepartments() {
        HRAppRestResponse hrAppRestResponse = HRAppRestUtils.createResponse(HRAppRestCode.SUCCESS, departmentService.listAllDepartments(), "OK");
        return new ResponseEntity<>(hrAppRestResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity<HRAppRestResponse> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        departmentDTO = departmentService.createDepartment(departmentDTO);
        HRAppRestResponse hrAppRestResponse = HRAppRestUtils.createResponse(HRAppRestCode.SUCCESS, departmentDTO, "employee created");
        return new ResponseEntity<>(hrAppRestResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<HRAppRestResponse> updateDepartment(@RequestBody DepartmentDTO departmentDTO) {
        departmentDTO = departmentService.updateDepartment(departmentDTO);
        HRAppRestResponse hrAppRestResponse = HRAppRestUtils.createResponse(HRAppRestCode.SUCCESS, departmentDTO, "employee updated");
        return new ResponseEntity<>(hrAppRestResponse, HttpStatus.OK);
    }

}
