package com.hr.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hr.entity.ExpenseClaim;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Anish on 03-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 03-03-2023
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpenseClaimDetailDTO {
    private Long id;
    private Date date;
    private String description;
    private String type;
    private BigDecimal total;
    private ExpenseClaimDTO expenseClaimDTO;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public ExpenseClaimDTO getExpenseClaimDTO() {
        return expenseClaimDTO;
    }

    public void setExpenseClaimDTO(ExpenseClaimDTO expenseClaimDTO) {
        this.expenseClaimDTO = expenseClaimDTO;
    }
}
