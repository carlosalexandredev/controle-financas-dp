package com.example.demo.model.fortune.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@ToString
@MappedSuperclass
@EqualsAndHashCode(of = {"id"})
public abstract class BasicEntity extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", length = 64)
    protected Long id;

    @Column(name = "DESATIVADA")
    protected Boolean desativada = false;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    protected Instant createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    protected Instant modifiedAt;
}
