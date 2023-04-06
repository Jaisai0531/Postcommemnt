package com.myblog.payload;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class PostDto {

    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotBlank(message = "Content cannot be blank")
    private String content;
    private List<CommentDto> comments;
}
