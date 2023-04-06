package com.myblog.payload;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class ErrorDetails {
    private Date timeStamp;///4 march
    private  String message;///id not found
    private String details;//api/post.


    }

