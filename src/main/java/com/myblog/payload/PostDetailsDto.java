package com.myblog.payload;

import lombok.Data;

import java.util.List;

@Data
public class PostDetailsDto {
    private Long id;
    private String title;
    private String description;
    private String content;
    private List<CommentDto> comments;

    // constructors, getters and setters
}