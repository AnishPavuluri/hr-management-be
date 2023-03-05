package com.hr.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Anish on 03-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 03-03-2023
 */
@Entity
@Table(name = "leave", schema="hrapp")
public class Leave {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "from_date")
    @Temporal(TemporalType.DATE)
    private Date from;
    @Column(name = "to_date")
    @Temporal(TemporalType.DATE)
    private Date to;
    @Column(name = "no_of_days")
    private int numberOfDays;
    @Column(name = "note")
    private String note;
    @ManyToOne
    @JoinColumn(name = "leave_type_id")
    private LeaveType leaveType;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
