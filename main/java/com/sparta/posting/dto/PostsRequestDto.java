package com.sparta.posting.dto;

import lombok.Getter;

@Getter
public class PostsRequestDto {
    private String username;    //작성자
    private String title;       //제목
    private String contents;    //내용
    private String password;    //비밀번호
}
