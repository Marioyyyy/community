package com.lxy.community.service;


import com.lxy.community.dto.PaginationDTO;
import com.lxy.community.dto.QuestionDTO;
import com.lxy.community.mapper.QuestionMapper;
import com.lxy.community.mapper.UserMapper;
import com.lxy.community.model.Question;
import com.lxy.community.model.User;
import com.lxy.community.util.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    RedisUtil redisUtil;


    public PaginationDTO<QuestionDTO> getList(Integer page, Integer size) {

        Integer offset = size *(page-1);
        List<Question> questions = questionMapper.list(offset,size);
        ArrayList<QuestionDTO> questionDTOS = new ArrayList<>();

        PaginationDTO<QuestionDTO> paginationDTO = new PaginationDTO<>();
        for (Question question:questions) {
            Object value = redisUtil.get(question.getId() + "." + "viewCount");
            if(value!=null){
                question.setViewCount(Long.valueOf(value.toString()));
            }
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }

        Integer totalPage;
        paginationDTO.setData(questionDTOS);
        Integer totalCount = questionMapper.count();

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage,page);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {

        Question question = questionMapper.getById(id);

        Object value = redisUtil.get(question.getId() + "." + "viewCount");
        if(value!=null){
            question.setViewCount(Long.valueOf(value.toString()));
        }

        User user= userMapper.findById(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public PaginationDTO<QuestionDTO> getListByUser(Integer userId,Integer page, Integer size) {

        Integer offset = size *(page-1);

        List<Question> questions = questionMapper.listById(userId,offset,size);
        ArrayList<QuestionDTO> questionDTOS = new ArrayList<>();

        // get user object
        User user = userMapper.findById(userId);

        PaginationDTO<QuestionDTO> paginationDTO = new PaginationDTO<>();
        for (Question question:questions) {

            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }

        Integer totalPage;
        paginationDTO.setData(questionDTOS);
        Integer totalCount = questionMapper.countByUserId(userId);

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage,page);
        return paginationDTO;
    }


    public Question findById(Integer id) {

        Question byId = questionMapper.getById(id);

        //QuestionDTO questionDTO = new QuestionDTO();

        //BeanUtils.copyProperties(byId,questionDTO);

        //questionDTO.setUser(byId.getUser());


        return byId;

    }

    public void update(Question question){
        questionMapper.update(question);
    }

}
