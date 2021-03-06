package com.lxy.community.controller;


import com.lxy.community.mapper.QuestionMapper;
import com.lxy.community.mapper.UserMapper;
import com.lxy.community.model.Question;
import com.lxy.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){ return "publish";}


    @PostMapping("/publish")
    public String doPublish(
            @RequestParam( "title") String title,
            @RequestParam( "description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model
    ){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            model.addAttribute("error","用户未登陆");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setCreator(user.getId());
        questionMapper.insert(question);

        return "redirect:/";
    }



}
