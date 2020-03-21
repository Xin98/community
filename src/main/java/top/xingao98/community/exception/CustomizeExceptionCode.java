package top.xingao98.community.exception;

/**
 * Created by xinGao 2020/3/20
 */

public enum CustomizeExceptionCode implements IExceptionCode {

    QUESTION_NOT_FOUND("您的问题不存在了，要不重试一下？"),

    SERVER_UNKNOW_ERROR("服务器太热了，要不稍后再来？"),

    PATH_NOT_EXSIT("您访问的路径不存在，要不检测一下再试试？"),

    ACCESS_FORBIDDEN("您的操作好像有误，要不换个姿势？")
    ;
    private String message;
    CustomizeExceptionCode(String message) {
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
