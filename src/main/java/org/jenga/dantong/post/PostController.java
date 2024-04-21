package org.jenga.dantong.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.post.model.entity.*;
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
    public String post(@RequestBody PostSaveDto postSaveDto) throws Exception{

        postService.savePost(postSaveDto);

        return "redirect:list";
    }

    @GetMapping("/{postId}/edit")
    public ResponseEntity<PostResponseDto> goToUpdate(@ModelAttribute PostIdInfoDto postInfo){
//        Post post = postRepository.findByPostId(postInfo.getPostId());
        PostResponseDto post = postService.findPost(postInfo.getPostId());

        return ResponseEntity.ok(post);
    }

    @PostMapping("/{postId}/edit")
    public String update(@ModelAttribute PostIdInfoDto postInfo, @RequestBody PostUpdateDto post) throws Exception{
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
    public ResponseEntity<PostResponseDto> view(@ModelAttribute PostIdInfoDto postId){
//        Post post = postRepository.findByPostId(postId.getPostId());
        PostResponseDto post = postService.findPost(postId.getPostId());

        return ResponseEntity.ok(post);
    }

    @GetMapping("/{postId}/delete")
    public String delete(@ModelAttribute PostIdInfoDto postId) throws Exception{
        postService.deletePost(postId.getPostId());

        return "redirect:list";
    }
}
