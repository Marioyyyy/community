package com.lxy.community.service;


import com.lxy.community.dto.QuestionDTO;
import com.lxy.community.dto.ViewCountDTO;
import com.lxy.community.model.Question;
import com.lxy.community.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewService {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    QuestionService questionService;

    public void transViewCountFromRedis2DB() {
        List<ViewCountDTO> list = redisUtil.getViewCountFromRedis();
        for (ViewCountDTO dto : list) {
            Question question = questionService.findById(Integer.parseInt(dto.getId().substring(0,1)));

            //点赞数量属于无关紧要的操作，出错无需抛异常
            if (question != null){
                //redisUtil.set(dto.getId(),0);

                //Long viewCount = question.getViewCount() + dto.getView();
                Long viewCount =  dto.getView();

                question.setViewCount(viewCount);
                // update
                questionService.update(question);
            }
        }
    }
}


