package com.zzf.learn.community.controller;

import com.zzf.learn.community.dto.QuestionPageDTO;
import com.zzf.learn.community.model.Users;
import com.zzf.learn.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size,
                          @PathVariable(name = "action") String action, Model model,
                          HttpServletRequest request){
        if ("my_question".equals(action)){
            model.addAttribute("section", "my_question");
            model.addAttribute("sectionName", "我的问题");
        }else if("my_response".equals(action)){
            model.addAttribute("section", "my_response");
            model.addAttribute("sectionName", "我的回复");
        }
        Users user = (Users)request.getSession().getAttribute("user");
        if (user == null){
            model.addAttribute("error", "用户未登录！");
            return "profile";
        }
        QuestionPageDTO myQuestionPage = questionService.list(user.getId(), page, size);
        model.addAttribute("myQuestionPage", myQuestionPage);
        return "profile";
    }

}
