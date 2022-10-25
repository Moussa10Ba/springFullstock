package com.moussa.gestionstock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {

    @GeneratedValue
    @Id
    private Integer id;

    @Column(name = "lasModifiedDate")
    @JsonIgnore
    private Instant lastModifiedDate;

    @Column(name = "creationDate")
    @JsonIgnore
    private Instant creationDate; 
}
