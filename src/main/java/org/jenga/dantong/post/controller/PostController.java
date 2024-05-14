package org.jenga.dantong.post.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.global.auth.jwt.AppAuthentication;
import org.jenga.dantong.global.base.UserAuth;
import org.jenga.dantong.post.model.dto.PostCreateRequest;
import org.jenga.dantong.post.model.dto.PostIdInfoRequest;
import org.jenga.dantong.post.model.dto.PostResponse;
import org.jenga.dantong.post.model.dto.PostUpdateRequest;
import org.jenga.dantong.post.model.entity.Category;
import org.jenga.dantong.post.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/post")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping()
    @UserAuth
    public void post(@RequestBody PostCreateRequest postSaveRequest, AppAuthentication auth)
        throws Exception {

        postService.savePost(postSaveRequest, auth);
    }

    @GetMapping()
    public ResponseEntity<PostResponse> findPost(@ModelAttribute PostIdInfoRequest postInfo) {

        PostResponse post = postService.findPost(postInfo.getPostId());

        return ResponseEntity.ok(post);
    }

    @PatchMapping("/edit")
    @UserAuth
    public void edit(@RequestBody PostUpdateRequest post, AppAuthentication auth) {

        postService.updatePost(post, auth);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PostResponse>> list(
        @RequestParam(required = false) Category category) {

        List<PostResponse> posts;
        posts = postService.showAllPost();
        if (category != null) {
            posts = postService.showByCategory(category);
        }

        return ResponseEntity.ok(posts);
    }

    @DeleteMapping("/delete")
    public void delete(@ModelAttribute PostIdInfoRequest postId) throws Exception {
        postService.deletePost(postId.getPostId());
    }
}
