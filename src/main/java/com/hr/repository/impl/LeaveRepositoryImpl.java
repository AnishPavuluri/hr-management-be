package com.hr.repository.impl;

import com.hr.entity.Employee;
import com.hr.entity.Leave;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Anish on 03-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 03-03-2023
 */
public class LeaveRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Leave> findLeaveByFromToEmpId(Date from, Date to, Long empId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Leave> criteriaQuery = criteriaBuilder.createQuery(Leave.class);

        Root<Leave> leaveRoot = criteriaQuery.from(Leave.class);
        List<Predicate> predicates = new ArrayList<>();

        if (from != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(leaveRoot.get("from"), from));
        }
        if (to != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(leaveRoot.get("to"),  to));
        }
        if (empId != null) {
            Join<Leave, Employee> join = leaveRoot.join("employee");
            predicates.add(criteriaBuilder.equal(join.get("id"), empId));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
