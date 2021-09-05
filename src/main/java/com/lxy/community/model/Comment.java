package com.lxy.community.model;

import java.io.Serializable;

/**
 * (Comment)实体类
 *
 * @author makejava
 * @since 2021-08-14 23:12:53
 */
public class Comment implements Serializable {
    private static final long serialVersionUID = 326865504486340512L;

    private Long likeCount;

    private Long gmtModified;

    private Long gmtCreate;
    /**
     * 评论人id
     */
    private Integer commentator;

    //private String description;
    /**
     * 父类型
     */
    private Integer type;

    private Integer parentId;

    private Long id;

    private String content;


    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Integer getCommentator() {
        return commentator;
    }

    public void setCommentator(Integer commentator) {
        this.commentator = commentator;
    }

    //public String getDescription() {
        //return description;
    //}

    //public void setDescription(String description) {
        //this.description = description;
    //}

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent(){return content;}

}
