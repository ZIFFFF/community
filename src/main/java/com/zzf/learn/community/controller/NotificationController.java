package com.zzf.learn.community.controller;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zzf.learn.community.dto.NotificationDTO;
import com.zzf.learn.community.enums.NotificationTypeEnum;
import com.zzf.learn.community.model.Users;
import com.zzf.learn.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String notification(@PathVariable("id") Long id,
                               HttpServletRequest request) {
        Users user = (Users) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        NotificationDTO notificationDTO = notificationService.read(id, user);
        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType()
                || NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType()) {
            return "redirect:/question/" + notificationDTO.getOuterid();
        } else {
            return "redirect:/";
        }
    }
}
