package com.myblog.service;

import com.myblog.payload.CommentDto;
import com.myblog.payload.PostDetailsDto;
import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface PostService {
        PostDto createPost(PostDto postDto);


    PostResponse getallPost(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

   void deletePostById(long id);





    List<CommentDto> getCommentsForPost(Long postId);

    PostDetailsDto getPostDetailsById(Long postId);
}

