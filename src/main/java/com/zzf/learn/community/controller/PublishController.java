package com.zzf.learn.community.controller;

import com.zzf.learn.community.dto.QuestionDTO;
import com.zzf.learn.community.model.Question;
import com.zzf.learn.community.model.Users;
import com.zzf.learn.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String QuestionPublish(@RequestParam(value = "title", required = false) String title,
                                  @RequestParam(value = "description", required = false) String description,
                                  @RequestParam(value = "tag", required = false) String tag,
                                  @RequestParam(value = "id") Long id,
                                  HttpServletRequest request,
                                  Model model){
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if (title == null || title == ""){
            model.addAttribute("error", "问题标题为空！");
            return "publish";
        }
        if (description == null || description == ""){
            model.addAttribute("error", "问题补充为空！");
            return "publish";
        }
        if (tag == null || tag == ""){
            model.addAttribute("error", "问题标签为空！");
            return "publish";
        }

        Users user = (Users)request.getSession().getAttribute("user");
        if (user == null){
            model.addAttribute("error", "用户未登录！");
            return "index";
        }

        Question question = new Question();
        question.setId(id);
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setCreateTime(System.currentTimeMillis());
        question.setModifiedTime(question.getCreateTime());
        questionService.createOrUpdate(question);
        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public String editQuestion(@PathVariable(name = "id") Long id, Model model){
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", id);
        return "publish";
    }
}
