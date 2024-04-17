package org.jenga.dantong.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.post.model.entity.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/board")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;

    @PostMapping("/post")
    public String post(@RequestBody Post post) throws Exception{
        postService.savePost(post);

        return "Save Post Success";
    }

    @GetMapping("/{postId}/edit")
    public ResponseEntity<Post> goToUpdate(@PathVariable int postId){
        Post post = postRepository.findByPostId(postId);

        return ResponseEntity.ok(post);
    }

    @PostMapping("/{postId}/edit")
    public String update(@RequestBody Post post) throws Exception{
        postService.savePost(post);

        String returnMessage = "%s".formatted(post.getPostId()) + " Update Complete!";

        return "redirect:list";
    }

    @GetMapping("/list")
    public ResponseEntity<List<Post>> list(){
        List<Post> posts = postService.showPost();

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> view(@PathVariable int postId){
        Post post = postRepository.findByPostId(postId);

        return ResponseEntity.ok(post);
    }

    @GetMapping("/{postId}/delete")
    public String delete(@PathVariable int postId) throws Exception{
        postService.deletePost(postId);

        String returnMessage = "%s".formatted(postId) + " Delete Complete!";

        return returnMessage;
    }
}
