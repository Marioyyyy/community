package com.lxy.community.dto;


import com.lxy.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private String tag;
    private Integer creator;
    private Long commentCount;
    private Long viewCount;
    private Long likeCount;
    private User user;

}
