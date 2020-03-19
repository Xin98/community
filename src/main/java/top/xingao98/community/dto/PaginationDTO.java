package top.xingao98.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinGao 2020/3/15
 */
@Data
public class PaginationDTO {

    //问题列表
    private List<QuestionDTO> questions;

    //分页总数
    private Integer totalPages;

    private Boolean hasPreviousPage;
    private Boolean hasGo2FirstPage;
    private Boolean hasNextPage;
    private Boolean hasGo2EndPage;

    private List<Integer> pages;
    private Integer currentPage;

    //当前页面，向前向后展示的最大分页数
    private Integer pageDisplayNum = 3;

    //计算分页属性
    public void setPagination(Integer page, Integer size, Integer totalCount) {

        //设置页面总数
        totalPages = (totalCount % size == 0) ? totalCount / size : totalCount / size + 1;
        //判断页面是否越界
        page = (page < 1) ? 1 : (page > totalPages) ? totalPages : page;
        //设置当前页面
        currentPage = page;

        //添加向前向后等按钮
        hasPreviousPage = (page <= 1) ? false : true;
        hasNextPage = (page >= totalPages) ? false : true;
        hasGo2FirstPage = (page <= pageDisplayNum + 1) ? false : true;
        hasGo2EndPage = (page >= totalPages - pageDisplayNum) ? false : true;

        if(totalPages == 0) return;

        //添加pages list
        pages = new ArrayList<>();
        pages.add(page);
        for (int i = 1; i <= pageDisplayNum; i++) {
            if (page - i >= 1) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPages) {
                pages.add(page + i);
            }
        }
    }
}
