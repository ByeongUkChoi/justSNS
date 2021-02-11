package com.byeongukchoi.justSNS.comment.domain;

import com.byeongukchoi.justSNS.post.domain.Post;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String content;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
