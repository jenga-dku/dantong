package org.jenga.dantong.post.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.global.auth.jwt.AppAuthentication;
import org.jenga.dantong.global.base.UserAuth;
import org.jenga.dantong.post.model.dto.PostCreateRequest;
import org.jenga.dantong.post.model.dto.PostResponse;
import org.jenga.dantong.post.model.dto.PostUpdateRequest;
import org.jenga.dantong.post.model.entity.Category;
import org.jenga.dantong.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/post")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping()
    @UserAuth
    @SecurityRequirement(name = "JWT Token")
    public void post(@RequestBody PostCreateRequest postSaveRequest, AppAuthentication auth)
            throws Exception {

        postService.savePost(postSaveRequest, auth.getUserId());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> findPost(@PathVariable("postId") int postId) {

        PostResponse post = postService.findPost(postId);

        return ResponseEntity.ok(post);
    }

    @PatchMapping()
    @UserAuth
    @SecurityRequirement(name = "JWT Token")
    public void edit(@RequestBody PostUpdateRequest post, AppAuthentication auth) {

        postService.updatePost(post, auth.getUserId());
    }

    @GetMapping("/list")
    public ResponseEntity<Page<PostResponse>> list(
            @RequestParam(required = false) Category category) {


        Page<PostResponse> posts;
        posts = postService.showAllPost();
        if (category != null) {
            posts = postService.showByCategory(category);
        }

        return ResponseEntity.ok(posts);
    }


    @DeleteMapping("/{postId}")
    public void delete(@PathVariable("postId") int postId) throws Exception {
        postService.deletePost(postId);
    }
}
