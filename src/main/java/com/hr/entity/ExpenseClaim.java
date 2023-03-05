package com.hr.entity;

import javax.persistence.*;
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
@Entity
@Table(name = "expense_claim", schema="hrapp")
public class ExpenseClaim {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "date")
    private Date date;
    @Column(name = "description")
    private String description;
    @Column(name = "total")
    private BigDecimal total;
    @Column(name = "status")
    private String status;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy="expenseClaim")
    private List<ExpenseClaimDetail> expenseClaimDetails;

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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<ExpenseClaimDetail> getExpenseClaimDetails() {
        return expenseClaimDetails;
    }

    public void setExpenseClaimDetails(List<ExpenseClaimDetail> expenseClaimDetails) {
        this.expenseClaimDetails = expenseClaimDetails;
    }
}
