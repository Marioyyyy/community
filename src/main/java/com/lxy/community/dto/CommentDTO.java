package com.lxy.community.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Integer ParentId;
    private String content;
    private Integer type;

}
