package com.lxy.community.model;


import com.lxy.community.util.RedisUtil;
import lombok.Data;

@Data
public class Question {
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
