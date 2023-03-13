package com.hr.entity;

import javax.persistence.*;

/**
 * Created by Anish on 02-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 02-03-2023
 */
@Entity
@Table(name = "department", schema="hrapp")
public class Department {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "depSeq", sequenceName = "department_seq", schema = "hrapp", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "depSeq")
    private Long id;
    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
