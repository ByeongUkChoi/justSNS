package com.byeongukchoi.justSNS.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue
    private long id;
    private String subject;
    private String content;
    @CreationTimestamp
    private LocalDateTime createdTimeAt;
    @UpdateTimestamp
    private LocalDateTime updateTimeAt;
}
