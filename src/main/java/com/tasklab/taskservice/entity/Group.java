package com.tasklab.taskservice.entity;

import com.tasklab.taskservice.enumeration.GroupJoinAccess;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.Fetch;

import java.util.UUID;

@Entity
@Table(name = "groups")
@Check(constraints = "maximum_size < 200")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Group {

    @Column(name = "group_id")
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "maximum_size", nullable = false)
    private int maximumSize;

    @Column(name = "join_access", nullable = false)
    @Enumerated(EnumType.STRING)
    private GroupJoinAccess joinAccess;

    @Column(name = "solved_task_count", nullable = false)
    private int solvedTaskCount;

    @Column(name = "failed_task_count", nullable = false)
    private int failedTaskCount;

    @Column(name = "size", nullable = false)
    private int size;
}
