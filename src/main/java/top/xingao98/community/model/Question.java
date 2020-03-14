package top.xingao98.community.model;

import lombok.Data;

/**
 * Created by xinGao 2020/3/13
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String detail;
    private String tag;
    private Integer viewCount;
    private Integer readCount;
    private Integer commentCount;
    private Integer likeCount;
    private Integer dislikeCount;
    private Integer stars;
    private Long gmtCreate;
    private Long gmtModified;
    private String createrId;
}
