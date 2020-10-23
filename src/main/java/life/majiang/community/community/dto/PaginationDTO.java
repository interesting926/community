package life.majiang.community.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private Boolean showPrevious;    //上一页
    private Boolean showFirstPage;
    private Boolean showNext;
    private Boolean showEndPage;
    private Integer page;
    private Integer totalPage;
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalPage, Integer size, Integer page) {

        this.page = page;
        pages.add(page);
        for(int i=1;i<=3;i++){
            if (page-i>0){
                pages.add(0,page-i);
            }
            if (page+i <= totalPage){
                pages.add(page+i);
            }
        }
        if (page == 1){
            showPrevious = false;
        }else{
            showPrevious = true;
        }
        if (page == totalPage){
            showNext = false;
        }else{
            showNext = true;
        }

        if (pages.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }
        if (pages.contains(totalPage)){
            showEndPage = false;
        }else {
            showEndPage = true;
        }

    }
}
