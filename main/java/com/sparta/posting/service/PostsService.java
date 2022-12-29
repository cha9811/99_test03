package com.sparta.posting.service;

import com.sparta.posting.dto.PostingDto;
import com.sparta.posting.dto.PostsCreateDto;
import com.sparta.posting.dto.PostsRequestDto;
import com.sparta.posting.entity.Posts;
import com.sparta.posting.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;  //DB와 연결하기 위함 (인터페이스) PostsRepository호출

    @Transactional
    public PostsCreateDto create(PostsRequestDto requestDto) {  //게시물 만들기
        Posts posts = new Posts(requestDto);    //post값을 받아와서
        postsRepository.save(posts);            //저장하고
        return new PostsCreateDto(posts);   //PostsCreateDto에 넘긴다
    }

    @Transactional
    public List<PostingDto> getPosts() {   //게시물 전체 조회하기
        List<Posts> posts = postsRepository.findAllByOrderByModifiedAtDesc();
        ArrayList<PostingDto> posting = new ArrayList<>();
        for(Posts post: posts) {
            posting.add(new PostingDto(post));
        }
        return posting;
    }

    @Transactional
    public List<PostingDto> getOneTitlePosts(String title) {    //게시물 제목으로 조회하기
        List<Posts> posts = postsRepository.findPostsByTitleIsOrderByModifiedAtDesc(title);
        ArrayList<PostingDto> posting = new ArrayList<>();
        for(Posts post: posts) {
            posting.add(new PostingDto(post));
        }
        return posting;
    }

    @Transactional
    public List<PostingDto> getOneUsernamePosts(String username) {  //유저이름으로 조회하기
        List<Posts> posts = postsRepository.findPostsByUsernameIsOrderByModifiedAtDesc(username);
        ArrayList<PostingDto> posting = new ArrayList<>();
        for(Posts post: posts) {
            posting.add(new PostingDto(post));
        }
        return posting;
    }

    @Transactional             //id로 구분, password검사, id = -1 || password = "" 인경우 오류문출력
    public Map<String, String> update(Long id, String password, PostsRequestDto requestDto) {   //게시물 수정하기
        Map<String, String> map = new HashMap<>();
        if(id == -1 || "".equals(password)) {
            map.put("message", "아이디와 비밀번호가 필요합니다");
            return map;
        }
        Posts posts = postsRepository.findById(id).orElseThrow(     //id를 찾되 예외처리시 오류문 출력
                ( ) -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(!posts.getPassword().equals(password)) { //비밀번호 점검문됨
            map.put("message", "비밀번호가 다릅니다.");
            return map;
        }
        posts.update(requestDto);
        map.put("message", "수정 성공!");
        return map;
    }

    @Transactional
    public Map<String, String> deletePosts(Long id, String password) {  //게시물 삭제하기
        Map<String, String> map = new HashMap<>();
        Posts posting = postsRepository.findById(id).orElseThrow(
                ( ) -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(!posting.getPassword().equals(password)) {   //posting에서 받아온 password가 deletePosts의 파라미터와 같는지 비교
            map.put("message", "비밀번호가 다릅니다.");    // 다를경우 출력
            return map;
        }
        postsRepository.deleteById(id);                //게시글 삭제
        map.put("message", "삭제 성공!");               //
        return map;
    }
}
