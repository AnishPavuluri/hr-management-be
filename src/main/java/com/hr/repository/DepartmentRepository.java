package com.hr.repository;

import com.hr.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Anish on 02-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 02-03-2023
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
