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

    public void setPagination(Integer totalCount, Integer size, Integer page) {

        this.page = page;
        if (totalCount %size ==0){
            this.totalPage = totalCount /size;
        }else{
            this.totalPage = totalCount /size+1;
        }

        if (page <1){
            page=1;
        }
        if (page> this.totalPage){
            page = this.totalPage;
        }

        pages.add(page);
        for(int i=1;i<=3;i++){
            if (page-i>0){
                pages.add(0,page-i);
            }
            if (page+i <= this.totalPage){
                pages.add(page+i);
            }
        }
        if (page == 1){
            showPrevious = false;
        }else{
            showPrevious = true;
        }
        if (page == this.totalPage){
            showNext = false;
        }else{
            showNext = true;
        }

        if (pages.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }
        if (pages.contains(this.totalPage)){
            showEndPage = false;
        }else {
            showEndPage = true;
        }

    }
}
