package com.byeongukchoi.justSNS.post.domain;

import com.byeongukchoi.justSNS.comment.domain.Comment;
import com.byeongukchoi.justSNS.post.dto.PostDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // jpa에서 findById시 default constructor가 없다고 나옴    getOne의 경우 public, protected or package-private 필요
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @OrderBy("id DESC")
    @OrderColumn(name="id")
    private List<Comment> comments;

    @CreatedBy
    private long createdAuthorId;

    @LastModifiedBy
    private long modifiedAuthorId;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;

    public Post(String content, long authorId) {
        this.content = content;
    }

    public void update(PostDto.Update updatePostDto) {
        if(updatePostDto.hasContent()) {
            content = updatePostDto.getContent();
        }
    }
}
