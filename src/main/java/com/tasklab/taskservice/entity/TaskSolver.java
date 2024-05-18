package com.tasklab.taskservice.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.UUID;

//@Entity
@Table(name = "task_solvers", uniqueConstraints = @UniqueConstraint(columnNames = {"solver_id", "task_id"}))
public class TaskSolver {

    @Column(name = "task_solver_id")
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "solver_id", nullable = false)
    private UUID solverId;

    @Column(name = "task_id", nullable = false)
    private UUID taskId;

    @Column(name = "started_at", nullable = false)
    @CreationTimestamp
    private UUID startedAt;
}
