package com.lxy.community.controller;

import com.lxy.community.dto.PaginationDTO;
import com.lxy.community.mapper.UserMapper;
import com.lxy.community.service.QuestionService;
import com.lxy.community.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
public class IndexController {


    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionService questionService;

    @Autowired
    RedisUtil redisUtil;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(defaultValue = "1",name = "page") Integer page,
                        @RequestParam(defaultValue ="5", name = "size") Integer size)
    {
        PaginationDTO pagination = questionService.getList(page,size);
        model.addAttribute("pagination",pagination);

        return "index";
    }






}
