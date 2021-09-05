package com.lxy.community.mapper;


import com.lxy.community.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liuxinyang
 */
@Mapper
public interface CommentMapper {

    @Insert("insert into community.comment(parent_id,commentator,type,gmt_create,gmt_modified,like_count,content) values (#{parentId},#{commentator},#{type},#{gmtCreate},#{gmtModified},#{likeCount},#{content})")
    void insert(Comment comment);
}
