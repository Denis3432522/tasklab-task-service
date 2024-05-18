package com.tasklab.taskservice.entity;

import com.tasklab.taskservice.enumeration.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "task_progresses")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TaskProgress {

    @Id
    private UUID id;

    @Column(name = "time_to_solve", nullable = false)
    private Duration timeToSolve;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "started_at")
    private ZonedDateTime startedAt;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    @MapsId
    private Task task;
}