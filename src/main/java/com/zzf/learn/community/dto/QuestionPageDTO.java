package com.zzf.learn.community.dto;

import lombok.Data;
import com.zzf.learn.community.dto.QuestionDTO;
import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionPageDTO {

    private List<QuestionDTO> questions;

    private Integer total_page;

    private boolean previous;

    private boolean first;

    private boolean next;

    private boolean end;

    private Integer page;

    private List<Integer> pages = new ArrayList<>();

    public void setPage(Integer total_page, Integer page){

        this.total_page = total_page;
        this.page = page;

        pages.add(page);
        for (int i = 1; i <= 3; i++){
            if (page - i > 0){
                pages.add(0, page - i);
            }
            if (page + i <= total_page){
                pages.add(page + i);
            }
        }

        //上一页
        if (page == 1){
            previous = false;
        }else {
            previous = true;
        }

        //下一页
        if (page == total_page){
            next = false;
        }else {
            next = true;
        }

        //第一页
        if (pages.contains(1)){
            first = false;
        }else {
            first = true;
        }

        //最后一页
        if (pages.contains(total_page)){
            end = false;
        }else {
            end = true;
        }
    }
}
