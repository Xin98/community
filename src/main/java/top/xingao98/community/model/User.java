package top.xingao98.community.model;

import lombok.Data;

/**
 * Created by xinGao 2020/3/12
 */
@Data
public class User {
    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
