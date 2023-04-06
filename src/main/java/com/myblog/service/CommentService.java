package com.myblog.service;

import com.myblog.entity.Comment;
import com.myblog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto CreateComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);


    CommentDto getCommentByCommentId(long postId, long commentId);

    CommentDto updateComment(CommentDto commentDto, long postId, long commentId);
}
