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
@Table(name = "expense_claim_detail", schema="hrapp")
public class ExpenseClaimDetail {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "expClaimDetailSeq", sequenceName = "expense_claim_detail_seq", schema = "hrapp", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expClaimDetailSeq")
    private Long id;
    @Column(name = "date")
    private Date date;
    @Column(name = "description")
    private String description;
    @Column(name = "type")
    private String type;
    @Column(name = "total")
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "expense_claim_id")
    private ExpenseClaim expenseClaim;

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

    public ExpenseClaim getExpenseClaim() {
        return expenseClaim;
    }

    public void setExpenseClaim(ExpenseClaim expenseClaim) {
        this.expenseClaim = expenseClaim;
    }
}
