package com.zzf.learn.community.controller;

import com.zzf.learn.community.dto.PaginationDTO;
import com.zzf.learn.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(@RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size,
                        Model model){
        PaginationDTO questionPage = questionService.list(page, size);
        model.addAttribute("questionPage", questionPage);
        return "index";
    }
}
