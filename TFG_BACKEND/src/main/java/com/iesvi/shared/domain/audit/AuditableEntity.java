package com.iesvi.shared.domain.audit;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Calendar;

//Revisar este articulo:  https://danielme.com/2014/11/02/persistencia-en-bd-con-spring-data-jpa-iii-auditoria/

@Data
@EntityListeners({ AuditingEntityListener.class })
@MappedSuperclass
public class AuditableEntity {

    @Column(nullable = false, updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar auditCreatedDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar auditLastModifiedDate;

    @Column(nullable = false, updatable = false)
    @CreatedBy
    private String auditCreateBy;

    @LastModifiedBy
    private String auditlLastModifiedBy;

}