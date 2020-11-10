package com.byeongukchoi.justSNS.model;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;
    private String name;
    private String email;
    @CreationTimestamp
    private LocalDateTime createdTimeAt;
    @UpdateTimestamp
    private LocalDateTime updateTimeAt;
}
