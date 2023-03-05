package com.hr.service;

import com.hr.dto.ExpenseClaimDTO;
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
public interface ExpenseClaimService {

    List<ExpenseClaimDTO> listExpenseClaim(Long empId);

    ExpenseClaimDTO createExpenseClaim(ExpenseClaimDTO expenseClaimDTO);

    Map<String, BigDecimal> totalExpenseClaimByExpenseType();

}
