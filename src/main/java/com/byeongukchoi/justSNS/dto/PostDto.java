package com.byeongukchoi.justSNS.dto;

import com.byeongukchoi.justSNS.model.Post;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

public class PostDto {

    @Getter
    public static class Create {
        @NotBlank
        @Size(max = 255)
        private String subject;
        @NotBlank
        @Size(max = 255)
        private String content;
    }
    @Getter
    public static class Update {
        @Size(max = 255)
        private String subject;
        @Size(max = 255)
        private String content;

        public boolean hasSubject() {
            return subject != null;
        }
        public boolean hasContent() {
            return content != null;
        }
    }

    @Getter
    public static class Response {

        private long id;
        private String subject;
        private String content;
        private long authorId;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;

        public Response(Post post) {
            this.id = post.getId();
            this.subject = post.getSubject();
            this.content = post.getContent();
            this.authorId = post.getAuthorId();
            this.createdAt = post.getCreatedAt();
            this.updatedAt = post.getUpdatedAt();
        }
    }
}
