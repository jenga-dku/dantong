package org.jenga.dantong.post.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.post.model.dto.PostIdInfoRequest;
import org.jenga.dantong.post.model.dto.PostResponse;
import org.jenga.dantong.post.model.dto.PostSaveRequest;
import org.jenga.dantong.post.model.dto.PostUpdateRequest;
import org.jenga.dantong.post.model.entity.Category;
import org.jenga.dantong.post.service.PostService;
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
    public String post(@RequestBody PostSaveRequest postSaveRequest) throws Exception {

        postService.savePost(postSaveRequest);

        return "redirect:list";
    }

    @GetMapping("/{postId}/edit")
    public ResponseEntity<PostResponse> goToUpdate(@ModelAttribute PostIdInfoRequest postInfo) {

        PostResponse post = postService.findPost(postInfo.getPostId());

        return ResponseEntity.ok(post);
    }

    @PostMapping("/{postId}/edit")
    public String update(@ModelAttribute PostIdInfoRequest postInfo, @RequestBody PostUpdateRequest post) throws Exception {

        postService.updatePost(postInfo.getPostId(), post);

        return "redirect:list";
    }

    @GetMapping("/list")
    public ResponseEntity<List<PostResponse>> list(@RequestParam(required = false) Category category) {

        List<PostResponse> posts;

        posts = postService.showAllPost();

        if (category != null && !category.equals("")) {
            posts = postService.showByCategory(category);
        }

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> view(@ModelAttribute PostIdInfoRequest postId) {

        PostResponse post = postService.findPost(postId.getPostId());

        return ResponseEntity.ok(post);
    }

    @GetMapping("/{postId}/delete")
    public String delete(@ModelAttribute PostIdInfoRequest postId) throws Exception {
        postService.deletePost(postId.getPostId());

        return "redirect:list";
    }
}
