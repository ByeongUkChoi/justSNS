package com.byeongukchoi.justSNS.post.domain;

import com.byeongukchoi.justSNS.post.dto.PostDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // jpa에서 findById시 default constructor가 없다고 나옴    getOne의 경우 public, protected or package-private 필요
@AllArgsConstructor(access = AccessLevel.PRIVATE)   // noArgsConstructor가 있기 때문에 builder를 사용하기 위해 필요
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    private long authorId;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;

    public void update(PostDto.Update updatePostDto) {
        if(updatePostDto.hasContent()) {
            content = updatePostDto.getContent();
        }
    }
}
