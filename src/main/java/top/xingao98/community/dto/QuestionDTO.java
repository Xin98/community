package top.xingao98.community.dto;

import lombok.Data;
import top.xingao98.community.model.User;

/**
 * Created by xinGao 2020/3/14
 */
@Data
public class QuestionDTO {
    private Long id;
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
    private User user;
}
