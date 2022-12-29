package com.sparta.posting.dto;

import com.sparta.posting.entity.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsCreateDto {
    private Long id;        //게시글 번호 PK
    private String username;    //작성자
    private String title;       //제목
    private String contents;    //내용
    private String password;    //비번
    private LocalDateTime createdAt;    //작성시간
    private LocalDateTime modifiedAt;   //수정시간

    public PostsCreateDto(Posts post) {     //PostsCreateDto 호출시 Posts post 형태로 받고 (엔티티의 Posts)
        this.id = post.getId();
        this.username = post.getUsername();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.password = post.getPassword();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
