package com.byeongukchoi.justSNS.comment.domain;

import com.byeongukchoi.justSNS.post.domain.Post;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    private Post post;

    private String content;
}
