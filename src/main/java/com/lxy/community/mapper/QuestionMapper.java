package com.lxy.community.mapper;

import com.lxy.community.dto.QuestionDTO;
import com.lxy.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author liuxinyang
 */
@Mapper
public interface QuestionMapper {

    @Insert("insert into community.question(title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void insert(Question question);

    @Select("select * from community.question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset,@Param(value="size") Integer size);

    @Select("select count(1) from community.question")
    Integer count();
    @Select("select * from community.question where id = #{id}")
    Question getById(@Param("id") Integer id);

    @Select("select * from community.question where creator = #{userId} limit #{offset},#{size}")
    List<Question>listById(@Param("userId") Integer userId, @Param(value = "offset") Integer offset,@Param(value="size") Integer size);


    @Select("select count(1) from question where creator = #{userId}")
    Integer countByUserId(@Param("userId") Integer userId);

    @Update(" update community.question set title=#{title}, description=#{description}, gmt_create=#{gmtCreate}, gmt_modified=#{gmtModified}," +
            "creator = #{creator}, comment_count = #{commentCount}, view_count=#{viewCount}, like_count = #{likeCount}, tag = #{tag} where  id=#{id}")
    int update(Question question);

    @Select("select community.question where title like '%${id}%'" )
    List<Question>searchByTitle(@Param("id") String id);







}
