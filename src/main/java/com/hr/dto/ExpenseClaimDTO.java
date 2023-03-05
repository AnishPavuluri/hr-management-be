package com.hr.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hr.entity.Employee;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Anish on 03-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 03-03-2023
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpenseClaimDTO {
    private Long id;
    private Date date;
    private String description;
    private BigDecimal total;
    private String status;
    private EmployeeDTO employeeDTO;
    private List<ExpenseClaimDetailDTO> expenseClaimDetailDTOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public List<ExpenseClaimDetailDTO> getExpenseClaimDetailDTOList() {
        return expenseClaimDetailDTOList;
    }

    public void setExpenseClaimDetailDTOList(List<ExpenseClaimDetailDTO> expenseClaimDetailDTOList) {
        this.expenseClaimDetailDTOList = expenseClaimDetailDTOList;
    }
}
