package com.myblog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostResponse {
    private List<PostDto> content;
    private int pageNo;
    private  int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
