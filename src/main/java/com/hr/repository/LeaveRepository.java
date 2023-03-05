package com.hr.repository;

import com.hr.entity.Leave;
import com.hr.entity.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by Anish on 03-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 03-03-2023
 */
public interface LeaveRepository extends JpaRepository<Leave, Long> {
    List<Leave> findLeaveByFromToEmpId(Date from, Date to, Long empId);

    @Query("SELECT l.leaveType.name, sum(l.numberOfDays) FROM Leave AS l GROUP BY l.leaveType.name")
    List<Object[]> totalLeavesByType();
}
