package top.xingao98.community.exception;

/**
 * Created by xinGao 2020/3/19
 */

public class CustomizeException extends RuntimeException {
    private String message;
    public CustomizeException(IExceptionCode exceptionCode){
        this.message = exceptionCode.getMessage();
    }
    @Override
    public String getMessage() {
        return message;
    }
}
