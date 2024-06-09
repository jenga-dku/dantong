package org.jenga.dantong.post.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.global.auth.jwt.AppAuthentication;
import org.jenga.dantong.global.base.UserAuth;
import org.jenga.dantong.post.model.dto.PostCreateRequest;
import org.jenga.dantong.post.model.dto.PostPreviewResponse;
import org.jenga.dantong.post.model.dto.PostResponse;
import org.jenga.dantong.post.model.dto.PostUpdateRequest;
import org.jenga.dantong.post.model.entity.Category;
import org.jenga.dantong.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/post")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @UserAuth
    @SecurityRequirement(name = "JWT Token")
    public void post(@ModelAttribute @Validated PostCreateRequest postSaveRequest,
                     AppAuthentication auth) {

        postService.savePost(postSaveRequest, auth.getUserId());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> findPost(@PathVariable(name = "postId") Long postId) {
        PostResponse post = postService.findPost(postId);

        return ResponseEntity.ok(post);
    }

    @PatchMapping("/edit")
    @UserAuth
    public void edit(@RequestBody @Validated PostUpdateRequest post, AppAuthentication auth) {

        postService.updatePost(post, auth.getUserId());
    }

    @GetMapping("/list")
    public ResponseEntity<Page<PostPreviewResponse>> list(
            @RequestParam(required = false, name = "category") Category category,
            Pageable pageable) {
        Page<PostPreviewResponse> posts;
        posts = postService.showAllPost(pageable);
        if (category != null) {
            posts = postService.showByCategory(category, pageable);
        }

        return ResponseEntity.ok(posts);
    }


    @DeleteMapping("/{postId}")
    public void delete(@PathVariable("postId") Long postId) throws Exception {
        postService.deletePost(postId);
    }
}