package com.myblog.controller;

import com.myblog.payload.PostDetailsDto;
import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;
import com.myblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //r
//http://localhost:8080/api/posts
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDto> CreatePost(@Valid @RequestBody PostDto postDto) {
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/posts?pageNo=0&pageSize=10&sortDir
    @GetMapping
    public PostResponse getallPost(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo
            , @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
            , @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy
            , @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        return postService.getallPost(pageNo, pageSize, sortBy, sortDir);

    }
    //http://localhost:8080/api/posts/id

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id) {
        PostDto dto = postService.getPostById(id);
        return ResponseEntity.ok(dto);
    }




    //http://localhost:8080/api/posts/id
    //Update Features We Developed Here
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("id") long id) {
        PostDto dto = postService.updatePost(postDto, id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //http://localhost:8080/api/posts/id
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletePostById(@PathVariable("id") long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post Entity Deleted", HttpStatus.OK);
    }


    @GetMapping("/{postId}/details")
    //http://localhost:8080/api/posts/3/details
    public ResponseEntity<PostDetailsDto> getPostDetails(@PathVariable Long postId) {
        PostDetailsDto postDetailsDto = postService.getPostDetailsById(postId);
        return new ResponseEntity<>(postDetailsDto, HttpStatus.OK);
    }

}
