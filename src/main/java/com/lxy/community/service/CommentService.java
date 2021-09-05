package com.lxy.community.service;

import com.lxy.community.mapper.CommentMapper;
import com.lxy.community.mapper.QuestionMapper;
import com.lxy.community.model.Comment;
import com.lxy.community.model.Question;
import com.lxy.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CommentService {
    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    CommentMapper commentMapper;



    public void insert(Comment comment, User user){
        //Question question = questionMapper.getById(comment.getParentId());
        commentMapper.insert(comment);



    }

}
