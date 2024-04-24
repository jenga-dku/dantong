package org.jenga.dantong.post.model.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.post.model.service.PostService;
import org.jenga.dantong.post.model.dto.PostIdInfoRequestDto;
import org.jenga.dantong.post.model.dto.PostResponseDto;
import org.jenga.dantong.post.model.dto.PostSaveRequestDto;
import org.jenga.dantong.post.model.dto.PostUpdateRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/board")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public String post(@RequestBody PostSaveRequestDto postSaveRequestDto) throws Exception{

        postService.savePost(postSaveRequestDto);

        return "redirect:list";
    }

    @GetMapping("/{postId}/edit")
    public ResponseEntity<PostResponseDto> goToUpdate(@ModelAttribute PostIdInfoRequestDto postInfo){
//        Post post = postRepository.findByPostId(postInfo.getPostId());
        PostResponseDto post = postService.findPost(postInfo.getPostId());

        return ResponseEntity.ok(post);
    }

    @PostMapping("/{postId}/edit")
    public String update(@ModelAttribute PostIdInfoRequestDto postInfo, @RequestBody PostUpdateRequestDto post) throws Exception{
        post.setPostId(postInfo.getPostId());
        postService.updatePost(post);

        return "redirect:list";
    }

    @GetMapping("/list")
    public ResponseEntity<List<PostResponseDto>> list(){
//        List<Post> posts = postService.showAllPost();
        List<PostResponseDto> posts = postService.showAllPost();

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> view(@ModelAttribute PostIdInfoRequestDto postId){
//        Post post = postRepository.findByPostId(postId.getPostId());
        PostResponseDto post = postService.findPost(postId.getPostId());

        return ResponseEntity.ok(post);
    }

    @GetMapping("/{postId}/delete")
    public String delete(@ModelAttribute PostIdInfoRequestDto postId) throws Exception{
        postService.deletePost(postId.getPostId());

        return "redirect:list";
    }
}
