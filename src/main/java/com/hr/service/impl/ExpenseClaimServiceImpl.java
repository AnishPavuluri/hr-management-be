package com.hr.service.impl;

import com.hr.controller.LeaveController;
import com.hr.dto.ExpenseClaimDTO;
import com.hr.dto.ExpenseClaimDetailDTO;
import com.hr.dto.LeaveTypeDTO;
import com.hr.entity.*;
import com.hr.repository.EmployeeRepository;
import com.hr.repository.ExpenseClaimDetailRepository;
import com.hr.repository.ExpenseClaimRepository;
import com.hr.service.EmployeeService;
import com.hr.service.ExpenseClaimService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Anish on 03-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 03-03-2023
 */
@Service
public class ExpenseClaimServiceImpl implements ExpenseClaimService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseClaimServiceImpl.class);
    @Autowired
    private ExpenseClaimRepository expenseClaimRepository;
    @Autowired
    private ExpenseClaimDetailRepository expenseClaimDetailRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;


    @Override
    public List<ExpenseClaimDTO> listExpenseClaim(Long empId) {
        List<ExpenseClaim> expenseClaimList =   new ArrayList<>();
        if (null != empId) {
            //expenseClaimList = expenseClaimRepository.findByExpenseClaim_Employee_Id(empId);
            expenseClaimList = expenseClaimRepository.findByEmployeeId(empId);
        } else {
            expenseClaimList = expenseClaimRepository.findAll();
        }
        LOGGER.info("employeeList.size(): "+expenseClaimList.size());
        List<ExpenseClaimDTO> employeeDTOList = expenseClaimList.stream().map(expenseClaim -> {
            ExpenseClaimDTO expenseClaimDTO = new ExpenseClaimDTO();
            BeanUtils.copyProperties(expenseClaim, expenseClaimDTO);
            List<ExpenseClaimDetail> expenseClaimDetails = expenseClaim.getExpenseClaimDetails();
            if (null != expenseClaimDetails) {
                List<ExpenseClaimDetailDTO> expenseClaimDetailDTOList = expenseClaimDetails.stream().map(expenseClaimDetail -> {
                    ExpenseClaimDetailDTO expenseClaimDetailDTO = new ExpenseClaimDetailDTO();
                    BeanUtils.copyProperties(expenseClaimDetail, expenseClaimDetailDTO);
                    return expenseClaimDetailDTO;
                }).collect(Collectors.toList());
                expenseClaimDTO.setExpenseClaimDetailDTOList(expenseClaimDetailDTOList);
            }
            Employee employee = expenseClaim.getEmployee();
            if (null != employee) {
                expenseClaimDTO.setEmployeeDTO(employeeService.findEmployeeById(employee.getId()));
            }
            return expenseClaimDTO;
        }).collect(Collectors.toList());
        return employeeDTOList;
    }

    @Override
    public ExpenseClaimDTO createExpenseClaim(ExpenseClaimDTO expenseClaimDTO) {
        ExpenseClaim expenseClaim = new ExpenseClaim();
        BeanUtils.copyProperties(expenseClaimDTO, expenseClaim);
        BigDecimal expenseClaimTotal = expenseClaimDTO.getExpenseClaimDetailDTOList().stream()
                .map(x -> x.getTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        expenseClaim.setTotal(expenseClaimTotal);
        if (null != expenseClaimDTO.getEmployeeDTO()) {
            expenseClaim.setEmployee(employeeRepository.findById(expenseClaimDTO.getEmployeeDTO().getId()).orElse(null));
        }
        expenseClaim = expenseClaimRepository.save(expenseClaim);
        final Long expenseClaimId = expenseClaim.getId();
        if (null != expenseClaimDTO.getExpenseClaimDetailDTOList()) {
            expenseClaimDTO.getExpenseClaimDetailDTOList().forEach(expenseClaimDetailDTO -> {
                ExpenseClaimDetail expenseClaimDetail = new ExpenseClaimDetail();
                BeanUtils.copyProperties(expenseClaimDetailDTO, expenseClaimDetail);
                expenseClaimDetail.setExpenseClaim(expenseClaimRepository.findById(expenseClaimId).orElse(null));
                expenseClaimDetail = expenseClaimDetailRepository.save(expenseClaimDetail);
                expenseClaimDetailDTO.setId(expenseClaimDetail.getId());
            });
        }
        expenseClaimDTO.setId(expenseClaim.getId());
        return expenseClaimDTO;
    }

    @Override
    public Map<String, BigDecimal> totalExpenseClaimByExpenseType() {
        Map<String, BigDecimal> map = new LinkedHashMap<>();
        List<Object[]> expByTypeList = expenseClaimDetailRepository.totalExpenseClaimByType();
        expByTypeList.forEach(objArr -> {
            map.put((String) objArr[0], (BigDecimal) objArr[1]);
        });
        return map;
    }


}
