package com.byeongukchoi.justSNS.post.dto;

import com.byeongukchoi.justSNS.post.domain.Post;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

public class PostDto {

    @Getter
    public static class Create {
        @NotBlank
        @Size(max = 255)
        private String content;
    }
    @Getter
    public static class Update {
        @Size(max = 255)
        private String content;

        public boolean hasContent() {
            return content != null;
        }
    }

    @Getter
    public static class Response {

        private long id;
        private String content;
        private long createdAuthorId;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;

        public Response(Post post) {
            this.id = post.getId();
            this.content = post.getContent();
            this.createdAuthorId = post.getCreatedAuthorId();
            this.createdAt = post.getCreatedAt();
            this.updatedAt = post.getUpdatedAt();
        }
    }
}
