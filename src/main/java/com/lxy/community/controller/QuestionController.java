package com.lxy.community.controller;

import com.lxy.community.dto.QuestionDTO;
import com.lxy.community.service.QuestionService;
import com.lxy.community.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author liuxinyang
 */
@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    RedisUtil redisUtil;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id, Model model){
        QuestionDTO questionDTO = questionService.getById(id);

        // 加入缓存 redis 浏览量
        String key = id + "."+"viewCount";
        redisUtil.incrView(key);

        model.addAttribute("question",questionDTO);
        return "question";
    }

}

