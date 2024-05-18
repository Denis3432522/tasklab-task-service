package com.tasklab.taskservice.test;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Persistable;

import java.util.UUID;

//@Entity
@Table(name = "cars")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@ToString
public class Car implements Persistable<UUID> {

    @Id
    @GeneratedValue
    UUID id;

    @Column
    String name;

    @Column
    String surname;

    @Version
    Integer version;

    @Override
    public boolean isNew() {
        return true;
    }
}
