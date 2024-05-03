package org.jenga.dantong.user.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jenga.dantong.global.base.BaseEntity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    @NotNull
    private String name;
    @NotNull
    @Column(unique = true)
    private String studentId;
    private Major major;
    @Column(unique = true)
    private String phoneNumber;
    @NotNull
    private String password;
    private UserRole userRole;
    @Enumerated(EnumType.STRING)
    private Status status;
}
