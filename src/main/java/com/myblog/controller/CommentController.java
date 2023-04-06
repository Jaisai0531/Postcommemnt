package com.myblog.controller;


import com.myblog.payload.CommentDto;
import com.myblog.service.CommentService;
import com.myblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    private PostService postService;


    //http://localhost:8080/api/posts/1/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable ("postId") long postId)
    {
        CommentDto dto = commentService.CreateComment(postId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity <List<CommentDto>> getCommentsByPostId(@PathVariable ("PostId") long postId ){
        List<CommentDto> commentsByPostId = commentService.getCommentsByPostId(postId);
        return new ResponseEntity<>(commentsByPostId,HttpStatus.OK);
    }
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentByCommentId(@PathVariable ("postId") long postId,
                                                            @PathVariable ("commentId") long commentId){

        CommentDto dto=commentService.getCommentByCommentId(postId,commentId);
        return  new ResponseEntity<>(dto,HttpStatus.OK);
    }
    //http://localhost:8080/api/posts/1/comments/update/2
    @PutMapping("/posts/{postId}/comments/update/{commentId}")
    public ResponseEntity<CommentDto> updateByCommentByCommentId(@Valid @RequestBody CommentDto commentDto,
                                                                 @PathVariable ("postId") long postId,
                                                                 @PathVariable("commentId") long commentId){

        CommentDto dto=commentService.updateComment(commentDto,postId,commentId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    //http://localhost:8080/api/1/comments
    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsForPost(@PathVariable Long postId) {
        List<CommentDto> comments = postService.getCommentsForPost(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}

