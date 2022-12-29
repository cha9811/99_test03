package com.sparta.posting.dto;

import com.sparta.posting.entity.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostingDto {
    private String username;            //작성자
    private String title;               //제목
    private String contents;            //글내용
    private LocalDateTime modifiedAt;   //수정시점

    public PostingDto(Posts post) {
        this.username = post.getUsername();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.modifiedAt = post.getModifiedAt();
    }
}
