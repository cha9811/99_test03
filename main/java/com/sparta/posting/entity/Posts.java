package com.sparta.posting.entity;

import com.sparta.posting.dto.PostsRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Posts extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;        //자동1씩 증가

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private String password;

    public Posts(PostsRequestDto requestDto) {      //Posts 함수 선언 (DTO의 값을 받아와서 이것을 호출한 부분에 넣는다) - 게시글 작성용
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }


    public void update(PostsRequestDto requestDto) {    //update 함수 선언 (DTO의 값을 받아와서 이것을 호출한 부분에 넣는다) - 게시글 수정용
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }


}
