package top.xingao98.community.dto;

import lombok.Data;

/**
 * Created by xinGao 2020/3/10
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
