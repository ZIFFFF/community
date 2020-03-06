package com.zzf.learn.community.service;

import com.zzf.learn.community.dto.NotificationDTO;
import com.zzf.learn.community.dto.PaginationDTO;
import com.zzf.learn.community.dto.QuestionDTO;
import com.zzf.learn.community.enums.NotificationStatusEnum;
import com.zzf.learn.community.enums.NotificationTypeEnum;
import com.zzf.learn.community.exception.CustomizeErrorCode;
import com.zzf.learn.community.exception.CustomizeException;
import com.zzf.learn.community.mapper.NotificationMapper;
import com.zzf.learn.community.model.Notification;
import com.zzf.learn.community.model.Question;
import com.zzf.learn.community.model.Users;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = notificationMapper.count(userId);
        Integer total_page;
        //求真实页数
        if (totalCount % size == 0) {
            total_page = totalCount / size;
        } else {
            total_page = totalCount / size + 1;
        }
        if (page > total_page) {
            page = total_page;
        }
        if (page < 1) {
            page = 1;
        }

        paginationDTO.setPage(total_page, page);
        Integer offset = size * (page - 1);

        List<Notification> notifications = notificationMapper.selectByUserId(userId, offset, size);
        if (notifications.size() == 0) {
            return paginationDTO;
        }
        List<NotificationDTO> notificationDTOS = new ArrayList<>();

        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }
        paginationDTO.setData(notificationDTOS);
        return paginationDTO;
    }

    public Long unreadCount(Long userId) {
        return notificationMapper.unreadCount(userId);
    }

    public NotificationDTO read(Long id, Users user) {
        Notification notification = notificationMapper.selectById(id);
        if (notification == null) {
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if (!Objects.equals(notification.getReceiver(), user.getId())) {
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }

        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateById(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }
}
