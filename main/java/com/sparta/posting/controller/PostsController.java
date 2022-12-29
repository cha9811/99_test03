package com.sparta.posting.controller;

import com.sparta.posting.dto.PostingDto;
import com.sparta.posting.dto.PostsCreateDto;
import com.sparta.posting.dto.PostsRequestDto;
import com.sparta.posting.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")       //전체 url은 posts를 거치기에 선언
public class PostsController {

    private final PostsService postsService; //DB와 연결하기 위함 (인터페이스) PostsService호출

    @PostMapping("/create")  //게시글 추가하기
    public PostsCreateDto createPosts(@RequestBody PostsRequestDto requestDto) {    //data 받아오기, body에 붙임,PostsRequestDto requestDto(변수명)
        return postsService.create(requestDto);                                     //create호출, 파라미터는 위의 requestDto
    }

    @GetMapping("/get")   //게시물 전체 가져오기
    public List<PostingDto> getPosts() {
        return postsService.getPosts();
    }

    @GetMapping("/get/title/{title}") //게시글 제목조회
    public List<PostingDto> postsTitleGet(@PathVariable String title) {
        return postsService.getOneTitlePosts(title);
    }
    //게시글 값이 여러개기에
    @GetMapping("/get/username/{username}")   //작성자 이름으로 조회하기
    public List<PostingDto> postsUsernameGet(@PathVariable String username) {
        return postsService.getOneUsernamePosts(username);
    }

    @PutMapping({"/update", "/update?id={id}&password={password}"})   //게시물 ID 로찾아서업데이트, 업데이트시 비밀번호가 다르면 수정안됨
    public Map<String, String> updatePosts(@RequestParam(value = "id", required = true, defaultValue = "-1") Long id, @RequestParam(value = "password", required = true, defaultValue = "") String password, @RequestBody PostsRequestDto requestDto) {
        return postsService.update(id, password, requestDto);               //@RequestParam - id password 추출 수정정보는 @ResponseBody 로 body 에서 받음
    }                                                                       //id, password에 default 값을 할당, 값이 없을경우 실패문구출력

    @DeleteMapping("/delete/{id}")   //게시물 id 로 삭제  //검사 하기 위해서 비밀번호를 받음
    public Map<String, String> deletePosts(@PathVariable Long id, @RequestBody PostsRequestDto requestDto) {
        return postsService.deletePosts(id, requestDto.getPassword());
    }   //메시지 JSON 형태반환을위해 Map사용
}
