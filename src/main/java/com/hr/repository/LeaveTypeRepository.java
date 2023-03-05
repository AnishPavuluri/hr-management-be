package com.hr.repository;

import com.hr.entity.Department;
import com.hr.entity.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Anish on 03-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 03-03-2023
 */
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {
}
