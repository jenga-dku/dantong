package org.jenga.dantong.user.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jenga.dantong.friend.model.entity.Friend;
import org.jenga.dantong.global.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    private Major major;
    @Column(unique = true)
    private String phoneNumber;
    @NotNull
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "friend")
    private List<Friend> friendList = new ArrayList<>();

    public void edit(String name, Major major, String phoneNumber) {
        this.name = name;
        this.major = major;
        this.phoneNumber = phoneNumber;
    }
}
