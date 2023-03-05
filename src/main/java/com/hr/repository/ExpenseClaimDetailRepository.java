package com.hr.repository;

import com.hr.entity.ExpenseClaimDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Anish on 03-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 03-03-2023
 */
public interface ExpenseClaimDetailRepository extends JpaRepository<ExpenseClaimDetail, Long> {

    @Query("SELECT ecd.type, sum(ecd.total) FROM ExpenseClaimDetail AS ecd GROUP BY ecd.type")
    List<Object[]> totalExpenseClaimByType();

}
