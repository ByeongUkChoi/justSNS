package com.byeongukchoi.justSNS.model;

import com.byeongukchoi.justSNS.dto.PostDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)  // jpa에서 findById시 default constructor가 없다고 나옴    getOne의 경우 public, protected or package-private 필요
@AllArgsConstructor(access = AccessLevel.PRIVATE)   // noArgsConstructor가 있기 때문에 builder를 사용하기 위해 필요
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue
    private long id;
    private String subject;
    private String content;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public long getAuthorId() {
        return user.getId();
    }

    public void update(PostDto.Update updatePostDto) {
        if(updatePostDto.hasSubject()) {
            subject = updatePostDto.getSubject();
        }
        if(updatePostDto.hasContent()) {
            subject = updatePostDto.getContent();
        }
    }
}
