package com.zzf.learn.community.service;

import com.zzf.learn.community.dto.QuestionPageDTO;
import com.zzf.learn.community.dto.QuestionDTO;
import com.zzf.learn.community.exception.CustomizeErrorCode;
import com.zzf.learn.community.exception.CustomizeException;
import com.zzf.learn.community.mapper.QuestionMapper;
import com.zzf.learn.community.mapper.UsersMapper;
import com.zzf.learn.community.model.Question;
import com.zzf.learn.community.model.Users;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService
{

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private UsersMapper userMapper;

    public QuestionPageDTO list(Integer page, Integer size) {
        QuestionPageDTO questionPageDTO = new QuestionPageDTO();
        Integer totalCount = questionMapper.counts();
        Integer total_page;
        //求真实页数
        if (totalCount % size == 0){
            total_page = totalCount / size;
        }else {
            total_page = totalCount / size + 1;
        }
        if (page > total_page){
            page = total_page;
        }
        if (page < 1){
            page = 1;
        }
        questionPageDTO.setPage(total_page, page);
        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.questionList(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions){
            Users user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        questionPageDTO.setQuestions(questionDTOList);
        return questionPageDTO;
    }

    public QuestionPageDTO list(Long user_id, Integer page, Integer size) {
        QuestionPageDTO questionPageDTO = new QuestionPageDTO();
        Integer totalCount = questionMapper.count(user_id);
        Integer total_page;
        //求真实页数
        if (totalCount % size == 0){
            total_page = totalCount / size;
        }else {
            total_page = totalCount / size + 1;
        }
        if (page > total_page){
            page = total_page;
        }
        if (page < 1){
            page = 1;
        }

        questionPageDTO.setPage(total_page, page);
        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.selectByUserId(user_id, offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions){
            Users user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        questionPageDTO.setQuestions(questionDTOList);
        return questionPageDTO;
    }

    public QuestionDTO getById(Long id){
        Question question = questionMapper.selectById(id);
        if (question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NO_FIND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        Users user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question){
        if (question.getId() == null){
            questionMapper.insertQuestion(question);
        }else{
            question.setModifiedTime(question.getCreateTime());
            int updated = questionMapper.updateQuestion(question);
            if (updated != 1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NO_FIND);
            }
        }
    }

    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionMapper.incView(question);
    }
}
