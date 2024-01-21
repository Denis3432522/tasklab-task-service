package com.tasklab.taskservice.entity;

import com.tasklab.taskservice.enumeration.GroupRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "group_user_details", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "group_id"}))
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GroupUserDetails {

    @Column(name = "group_user_details_id")
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private GroupRole role;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
}
