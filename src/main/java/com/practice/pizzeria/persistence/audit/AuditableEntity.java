package com.practice.pizzeria.persistence.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
public class AuditableEntity {

    @Column(name = "create_date")
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "modificate_date")
    @LastModifiedDate
    private LocalDateTime modificaDate;

    @Column(name = "create_by")
    @CreatedBy
    private String createBy;

    @Column(name = "modified_by")
    @LastModifiedDate
    private String modifyBy;

}

